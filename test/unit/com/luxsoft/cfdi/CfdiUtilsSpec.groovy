package com.luxsoft.cfdi

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Build(Cfdi)
class CfdiUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Validar un comprobante"() {
    	given: 'Un archivo xml'
    	def data=new File("test/integration/resources/xml/ALE950315J30_B66820.xml").getBytes()
    	ByteArrayInputStream is=new ByteArrayInputStream(data)
    	def comprobanteDocument=ComprobanteDocument.Factory.parse(is)
    }

    void "Convertir un DomicilioFiscal en Direccion"(){
        
        given: 'Un Cfdi existente'
        def cfdi=Cfdi.buildWithoutSave()
        
        when:'Convertimos el DomicilioFiscal fiscal en Direccion'
        def direccion=CFDIUtils.toDireccion(cfdi.getComprobante().getEmisor().getDomicilioFiscal())

        then:'La direccion generada es valida'
        direccion.validate()
        !direccion.hasErrors()
        println direccion


    }
}
