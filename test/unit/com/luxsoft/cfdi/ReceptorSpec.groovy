package com.luxsoft.cfdi


import spock.lang.Specification
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Receptor)
@Build(Receptor)
class ReceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Alta de recetor"() {
    	given:'Un receptor nuevo'
    	def receptor=Receptor.buildWithoutSave(nombre:'PAPEL')

    	when:'Salvamos el receptor'
    	receptor.save flush:true

    	then:'El receptor persiste exitosamente'
    	receptor.errors.errorCount==0
    	receptor.id
    	Receptor.get(receptor.id).nombre=='PAPEL'

    }
}
