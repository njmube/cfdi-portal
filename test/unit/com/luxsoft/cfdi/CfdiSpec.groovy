package com.luxsoft.cfdi

import grails.test.mixin.TestFor
import spock.lang.Specification
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Cfdi)
class CfdiSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Cargar un comprobante desde un archivo xml"() {
    	given:'Un comprobante en archivo'
    	def dir=new File("test/integration")
    	assert dir.exists()
    	def xmlFile=new File("test/integration/resources/xml/ALE950315J30_B66820.xml")
    	println xmlFile.path
    	assert xmlFile.exists()
    	and:'Una instancia de Cfdi'
    	def cfdi=new Cfdi()

    	when:'Cargamos el xml'
    	cfdi.cargarXml(xmlFile.getBytes())

    	then:'El Cfdi se carga existosamente'
    	cfdi.xml
    	cfdi.getComprobante()
    	cfdi.getTimbreFiscal()
    	cfdi.uuid==cfdi.getTimbreFiscal().uuid
    	println cfdi.uuid

    }

    
}
