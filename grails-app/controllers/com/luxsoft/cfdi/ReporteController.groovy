package com.luxsoft.cfdi

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable

import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.apache.commons.lang.WordUtils

@Secured(['ROLE_ADMIN','ROLE_OPERADOR'])
class ReporteController {

	def jasperService

    def comprobantesPorEmisor(PorEmisorCommand command){
		if(request.method=='GET'){
			return [reportCommand:new PorEmisorCommand()]
		}
		command.validate()
		if(command.hasErrors()){
			log.info 'Errores de validacion al ejecurar reporte'
			render view:'ausentismoPorDia',model:[reportCommand:command]
			return
		}
		def repParams=[:]
		repParams['FECHA']=command.fecha
		
		repParams.reportName=params.reportName?:'FaltaNombre Del Reporte'
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
	Date fechaInicial=new Date()-30
	Date fechaFinal=new Date()
	String referencia

	static constraints={
		
	}
}
