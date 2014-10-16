package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*

import org.apache.commons.lang.exception.ExceptionUtils
import org.springframework.web.multipart.MultipartFile

import grails.transaction.Transactional

@Transactional(readOnly = true)
class CfdiController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "GET"]

    def cfdiService

    def index(Integer max) {
		
        params.max = Math.min(max ?: 10, 100)
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

    def uploadCfdi(){
        def xml=request.getFile('xml')
        if (xml.empty) {
            flash.message = 'CFDI incorrecto (archivo vacío)'
            redirect action:'index'
            return
        }
        log.info 'Cargando CFDI con archivo: '+xml
        try {
            Cfdi cfdi=cfdiService.cargarComprobante(xml)
			flash.message="CFDI importado "+cfdi.toString()
			redirect action:'index',params:[id:cfdi.id]
        }
        catch(CfdiException e) {
			flash.message=e.message
			redirect action:'index'
        }
        

    }

    def batchUpload(){
        def count=0
        request.getFiles("xmls[]").each { xml ->
            if (xml) {
                try {
                    Cfdi cfdi=cfdiService.cargarComprobante(xml)
                    log.info(xml.originalFilename)
                    count++
                    //flash.message="CFDI importado "+cfdi.toString()
                    //redirect action:'index',params:[id:cfdi.id]
                }
                catch(CfdiException e) {
                    flash.message=e.message
                    redirect action:'index'
                }
                
            }
        }
        flash.message= "Archivos cargados $count "
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

    def validarEnElSat(Long id){
		println 'Validar en el sat'
        flash.message="Validacion de"
        redirect action:'index'
    }

    def verAcuse(Cfdi cfdi){
        def acuse=cfdiService.validarEnElSat(cfdi)
        def xml=cfdiService.toXml(acuse)
        render(text: xml, contentType: "text/xml", encoding: "UTF-8")
    }
}
