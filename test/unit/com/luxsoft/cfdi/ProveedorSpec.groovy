package com.luxsoft.cfdi

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Proveedor)
@Build(Proveedor)
class ProveedorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Alta de proveedor"() {
    	given:'Un proveedor nuevo'
    	def proveedor=Proveedor.build(nombre:'MYPROVEEDOR')

    	when:'Salvamos la instancia'
    	proveedor.save()
    	then: 'El proveedor persiste exitosamente en la baseo de datos'
    	proveedor.errors.errorCount==0
    	proveedor.id
    	Proveedor.get(proveedor.id).nombre=='MYPROVEEDOR'
        println proveedor
    }

    void "Modificacion de un proveedor"(){
        given:'Un proveedor existente'
        def proveedor=Proveedor.build(nombre:'TESTPROV',rfc:'CARR700318434')

        when:'Modificamos una propiedad'
        def found=Proveedor.findByNombre('TESTPROV')
        found.rfc='CARR700317777'
        found.save flush:true

        then:'La modificaion persite en la base de datos'
        Proveedor.get(found.id).rfc=='CARR700317777'

    }

    void "Alta de proveedor a partir de CFDI"(){
        given: 'Un Comprobante CFDI en archivo'
        def xmlFile=new File("test/integration/resources/xml/ALE950315J30_B66820.xml")
        assert xmlFile.exists()
        and: 'Un Comprobante a partir del archivo'
        def document=ComprobanteDocument.Factory.parse(xmlFile)
        
        when:'Generamos un proveedor mediante el comprobante'
        def proveedor=Proveedor.fromCfdi(document.getComprobante())
        
        then: 'El proveedor generado es valido'
        proveedor.validate()
        !proveedor.hasErrors()
    }
}
