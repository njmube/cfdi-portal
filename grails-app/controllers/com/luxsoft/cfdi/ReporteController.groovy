package com.luxsoft.cfdi

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable

import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.apache.commons.lang.WordUtils


@Secured(["hasAnyRole('ROLE_ADMIN','ROLE_OPERADOR')"])
class ReporteController {

	def jasperService

    def comprobantesPorEmisor(PorEmisorCommand command){
		if(request.method=='GET'){
			return [reportCommand:new PorEmisorCommand()]
		}
		command.validate()
		if(command.hasErrors()){
			log.info 'Errores de validacion al ejecurar reporte'
			render view:'comprobantesPorEmisor',model:[reportCommand:command]
			return
		}
		def repParams=[:]

		repParams['EMISOR_RFC']=command.proveedor.rfc
		if(command.nombre=='%'){
			repParams['EMISOR_RFC']='%'
		}
		repParams['REFERENCIA']=command.referencia?:'%'
		
		repParams.reportName=params.reportName
		ByteArrayOutputStream  pdfStream=runReport(repParams)
		render(file: pdfStream.toByteArray(), contentType: 'application/pdf'
			,fileName:repParams.reportName)
	}

	private runReport(Map repParams){
		log.info 'Ejecutando reporte  '+repParams
		def nombre=WordUtils.capitalize(repParams.reportName)
		def reportDef=new JasperReportDef(
			name:nombre
			,fileFormat:JasperExportFormat.PDF_FORMAT
			,parameters:repParams
			)
		ByteArrayOutputStream  pdfStream=jasperService.generateReport(reportDef)
		return pdfStream
		
	}
	
	private File findFile(String name){
		return grailsApplication.mainContext.getResource("/reports/$name").file
	}
}


@Validateable
class PorEmisorCommand{

	Proveedor proveedor
	String nombre
	Date fechaInicial=new Date()-30
	Date fechaFinal=new Date()
	String referencia

	static constraints={
		fechaInicial nullable:true
		fechaFinal nullable:true
		referencia nullable:true
		nombre nullable:true
	}
}
