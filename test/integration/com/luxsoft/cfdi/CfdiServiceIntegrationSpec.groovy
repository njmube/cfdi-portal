package com.luxsoft.cfdi

import grails.test.spock.IntegrationSpec

class CfdiServiceIntegrationSpec extends IntegrationSpec {

	def cfdiService

    def setup() {
    }

    def cleanup() {
    }

     void "Cargar un Cfdi "() {
    	given:'Un archivo xml existente'
    	def file=new File("test/integration/resources/xml/ALE950315J30_B66820.xml")

    	when:'cargamos el cfdi'
    	def cfdi=cfdiService.cargarComprobante(file.getBytes(),file.getName(),'100','COMPRAS','ADMIN')

    	then:' El cfdi es salvado exitosamente'
    	cfdi.id
    	cfdi.errors.errorCount==0
    	cfdi.xml
    }
}
