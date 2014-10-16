package com.luxsoft.cfdi

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ProveedorService)
@Build([Proveedor])
class ProveedorServiceSpec extends Specification {

	def proveedor

    def setup() {
    	proveedor=Proveedor.buildWithoutSave(nombre:'Proveedor 1')
    }

    def cleanup() {
    }

    void "Salvar un proveedor"() {
    	given:'Un proveedor nuevo'
    	def proveedor=Proveedor.buildWithoutSave(nombre:'Proveedor 1')
    	when:'Salvamos '
    	proveedor=service.salvar(proveedor)
    	then:'El proveedor es persistido en la base de datos'
    	proveedor.errors.errorCount==0
    	proveedor.id

    }

    void "Tratar de salvar un proveedor con errores de validacion"(){
    	given:'Un proveedor nuevo con propiedad invalida'
    	proveedor.rfc=null
    	when:'Salvamos'
    	proveedor=service.salvar(proveedor)
    	then:"Se genera una exception"
    	def e=thrown(ProveedorException)
    	e.proveedor

    }
}
