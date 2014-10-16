package com.luxsoft.luxor

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class DireccionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Validar una Direccion"() {
    	when: 'Una  direccion  es invalida'
    	def direccion=new Direccion(
    		calle:'Paseo del Potrero'
    		,numeroExterior:'109'
    		,colonia:'Pedregal del Gigante'
    		,municipio:'Leon'
    		,estado:'Guanajuato'
    		,pais:'MEXICO'
    		,codigoPostal:'37296')

    	then:
    	direccion.validate()
    	!direccion.hasErrors()
    	direccion.errors.errorCount==0

    	when:'La Direccion no contiene numero exterior'
    	direccion.numeroExterior=null
    	then:
    	!direccion.validate()
    	direccion.hasErrors()
    	direccion.errors.errorCount==1
    	direccion.errors['numeroExterior'].code=='nullable'


    }
}
