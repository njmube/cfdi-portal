package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*

import org.apache.commons.lang.exception.ExceptionUtils
import org.springframework.web.multipart.MultipartFile
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import groovy.transform.ToString

import org.eclipse.jdt.core.CorrectionEngine;
import org.grails.databinding.BindingFormat
import grails.validation.Validateable

import groovy.sql.Sql
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

@Transactional(readOnly = false)
@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_OPERADOR')"])
class CfdiController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def jasperService

    def cfdiService
	def dataSource_importacion
	def dataSource_importacionPaper

    
    def index(Integer max) {
        params.max = Math.min(max ?: 100, 5000)
		params.sort=params.sort?:'dateCreated'
		params.order='desc'
        respond Cfdi.list(params), model:[cfdiInstanceCount: Cfdi.count()]
    }

    def show(Cfdi cfdiInstance) {
        respond cfdiInstance
    }

    def create() {
        respond new Cfdi(params)
    }

    @Transactional
    def save(Cfdi cfdiInstance) {
        if (cfdiInstance == null) {
            notFound()
            return
        }

        if (cfdiInstance.hasErrors()) {
            respond cfdiInstance.errors, view:'create'
            return
        }

        cfdiInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cfdi.label', default: 'Cfdi'), cfdiInstance.id])
                redirect cfdiInstance
            }
            '*' { respond cfdiInstance, [status: CREATED] }
        }
    }

    def edit(Cfdi cfdiInstance) {
        respond cfdiInstance
    }

    

    @Transactional
    def delete(Cfdi cfdiInstance) {

        if (cfdiInstance == null) {
            notFound()
            return
        }

        cfdiInstance.delete flush:true
        flash.message="Cfdi eliminado: "+cfdiInstance.uuid
		redirect view:'index'
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cfdi.label', default: 'Cfdi'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	@Transactional
    def uploadCfdi(){
        def xml=request.getFile('xml')
		def referencia=params.referencia
		def grupo=params.grupo
		def user=getAuthenticatedUser().username
        if (xml.empty) {
            flash.message = 'CFDI incorrecto (archivo vacío)'
            redirect action:'index'
            return
        }
		try {
            log.info 'Cargando CFDI con archivo: '+xml
            Cfdi cfdi=cfdiService.cargarComprobante(xml.getBytes(),xml.getOriginalFilename(),referencia,grupo,user)
            flash.message="CFDI importado "+cfdi.toString()
            redirect action:'show',params:[id:cfdi.id]
        }
        catch(CfdiException e) {
			flash.message=e.message
			println 'Error cargando CFDI: '+e.message
			redirect action:'index'
        }
        

    }
	
	@Transactional
    def batchUpload(){
        def correctos=0
        def errores=0
        def duplicados=0
		def referencia=params.referencia
        def grupo=params.grupo
        def user=getAuthenticatedUser().username
		def proveedorRfc 
        request.getFiles("xmls[]").each { xml ->
            if (xml) {
                try {
                    Cfdi cfdi=cfdiService.cargarComprobante(xml.getBytes(),xml.getOriginalFilename(),referencia,grupo,user)
                    log.info(xml.originalFilename)
                    correctos++
					proveedorRfc=cfdi.emisorRfc
                }
                catch(CfdiException e) {
                    errores++
                }
                
            }
        }
        /*
		def reporte =params.boolean('reporte')
		if(reporte){
			def pdfStream=reporteDeCargaBatch(proveedorRfc)
            render(file: pdfStream.toByteArray(), contentType: 'application/pdf'
            ,fileName:'CargaBatch_'+proveedorRfc)
			return
		}*/
		
        flash.message= "Archivos cargados correctamente: $correctos  con errores: $errores  duplicados: $duplicados"
        redirect action:'index'
    }

    def descargarXml(Cfdi cfdi){
        log.info 'Descargando archivo xml: '+cfdi
		//log.info 'Index action....'
        //def file=new 
        //render(contentType: "text/xml", encoding: "UTF-8",file)
        
		response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=${cfdi.uuid}")
        response.outputStream << cfdi.xml
        return
        
    }

    def mostrarXml(Cfdi cfdi){
        render(text: cfdi.comprobanteDocument.xmlText(), contentType: "text/xml", encoding: "UTF-8")
    }

    

    def verAcuse(Cfdi cfdi){
        //def acuse=cfdiService.validarEnElSat(cfdi)
        if(!cfdi.acuse){
            try {
                cfdiService.validarEnElSat(cfdi)
                flash.message="Validación en el SAT exitosa"
                redirect action:'show',params:[id:cfdi.id]
            }
            catch(Exception e) {
                flash.message="Error de comunicacion con el SAT: "+e.getMessage()
                redirect action:'index'
                return
            }
        }
        def acuse=cfdiService.toAcuse(cfdi.acuse)
        def xml=cfdiService.toXml(acuse)
        render(text: xml, contentType: "text/xml", encoding: "UTF-8")
    }

    def search( CfdiSeach example){
		example.emisor=example.emisor?:'%'
		example.referencia=example.referencia?:'%'
        example.folio=example.folio?:'%'
		example.fechaInicial=example.fechaInicial?:new Date()-10
		example.fechaFinal=example.fechaFinal?:new Date()
		example.uuid=example.uuid?:'%'
		//println 'Buscando cfdis con: '+example
		/*
        def query=Cfdi.where{
			emisor=~example.emisor && referencia=~example.referencia && folio=~example.folio && (fecha>=example.fechaInicial && fecha<=example.fechaFinal) && uuid=~example.uuid}
        def list=query.list(max:60,sort:'dateCreated')
        render view:'index',model:[cfdiInstanceList:list,cfdiInstanceCount:query.count()]

        */
		params.max = example.max ?: 50
		params.sort=params.sort?:'dateCreated'
		params.order='desc'
		
		def args=[example.emisor
			,example.referencia
			,example.folio
			,example.fechaInicial
			,example.fechaFinal]
		def hql="from Cfdi c where lower(c.emisor) like ?  and c.referencia like ? and c.folio like ? "+ 
			" and date(c.fecha) between ? and ? "
		if(example.total>0.0){
			args.add(example.total)
			hql+=" and c.total=?"
		}
		def list=Cfdi.findAll(hql,args,params)
		render view:'index',model:[cfdiInstanceList:list,cfdiInstanceCount:list.size()]

    }
	
	def importarXmlImpap(YearMesCommand command){

		def user=getAuthenticatedUser().username
		def grupo='COMPRAS'
		def correctos=0
		def errores=0
		def duplicados=0
	
		command.validate()
		
		if(command.hasErrors()){
			log.info 'Errores de validacion al ejecutar el command'
			command.errors.each{
				//println it
			}
			redirect action:'index'
			return
		}

		def db=new Sql(dataSource_importacion)
		def res=db.eachRow("select uuid,xml,xml_name from cfdi where year(fecha)=? and  month(fecha)=?",[command.year,command.mes]) { row ->
			
			byte[] xml=row.xml
			try {
				log.info 'Cargando CFDI con archivo: '+row.uuid
				Cfdi cfdi=cfdiService.cargarComprobante(row.xml,row.xml_name,command.referencia,grupo,user)
				
				correctos++
			} catch (Exception e) {
			    e.printStackTrace()
				errores++
			}

		}	 

		flash.message= "Archivos cargados correctamente: $correctos  con errores: $errores  duplicados: $duplicados"
		redirect action:'index'

	}
	
	
	def importarXmlPaper(YearMesCommand command){
		
				def user=getAuthenticatedUser().username
				def grupo='COMPRAS'
				def correctos=0
				def errores=0
				def duplicados=0
			
				command.validate()
				
				if(command.hasErrors()){
					log.info 'Errores de validacion al ejecutar el command'
					command.errors.each{
						//println it
					}
					redirect action:'index'
					return
				}
		
				def db=new Sql(dataSource_importacionPaper)
				def res=db.eachRow("select uuid,xml,xml_name from cfdi where year(fecha)=? and  month(fecha)=?",[command.year,command.mes]) { row ->
					
					byte[] xml=row.xml
					try {
						log.info 'Cargando CFDI con archivo: '+row.uuid
						Cfdi cfdi=cfdiService.cargarComprobante(row.xml,row.xml_name,command.referencia,grupo,user)
						
						correctos++
					} catch (Exception e) {
						e.printStackTrace()
						errores++
					}
		
				}
		
				flash.message= "Archivos cargados correctamente: $correctos  con errores: $errores  duplicados: $duplicados"
				redirect action:'index'
		
			}
	

    private reporteDeCargaBatch(String emisorRfc){
        def repParams=[:]
        repParams['EMISOR_RFC']=emisorRfc
        log.info 'Ejecutando reporte  '+repParams
        def reportDef=new JasperReportDef(
            name:'ComprobantesPorEmisor'
            ,fileFormat:JasperExportFormat.PDF_FORMAT
            ,parameters:repParams
            )
        ByteArrayOutputStream  pdfStream=jasperService.generateReport(reportDef)
        return pdfStream
        
    }

}

@Validateable
class YearMesCommand{
	Integer year
	Integer mes
	String referencia 
	
	static constraints = {
		year inList:2014..2018
		mes inList:1..12
		referencia nullable:true
	}
}


@ToString(includeNames=true,includePackage=false)
@Validateable
class CfdiSeach{
    String emisor
    String referencia
	String folio
	String uuid
	Long max
	BigDecimal total
	
	@BindingFormat('dd/MM/yyyy')
	Date fechaInicial
	
	@BindingFormat('dd/MM/yyyy')
	Date fechaFinal

}
