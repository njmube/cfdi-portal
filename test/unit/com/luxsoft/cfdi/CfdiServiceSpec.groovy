package com.luxsoft.cfdi

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CfdiService)
@Mock(Cfdi)
class CfdiServiceSpec extends Specification {

	com.luxsoft.cfdi.IConsultaCFDIService consultaService=Mock()

    def setup() {
    	service.consultaService=consultaService
    }

    def cleanup() {
    }

    void "Cargar un Cfdi "() {
    	given:'Un archivo xml existente'
    	def file=new File("test/integration/resources/xml/ALE950315J30_B66820.xml")

    	when:'cargamos el cfdi'
    	def cfdi=service.cargarComprobante(file.getBytes(),file.getName(),'100','COMPRAS','ADMIN')

    	then:' El cfdi es salvado exitosamente'
    	cfdi.id
    	cfdi.errors.errorCount==0
    	cfdi.xml
    }
}
