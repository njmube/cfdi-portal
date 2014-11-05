package com.luxsoft.cfdi



import spock.lang.*

import grails.buildtestdata.mixin.Build
/**
 *
 */
 @Build(Cfdi)
class CfdiIntegrationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Salvar un  Cfdi "() {
    	given:'Un CFDI nuevo'
    	def cfdi=Cfdi.buildWithoutSave()
    	cfdi.cargarXml(cfdi.xml)

    	when:'Salvamos el cfdi'
    	cfdi.save flush:true

    	then:'El cfdi persiste exitosamente'
        cfdi.id
        cfdi.errors.errorCount==0
        cfdi.lastUpdated
        println cfdi


    }
}
