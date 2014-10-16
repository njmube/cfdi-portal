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

    // void "Alta de un proveedor al dar de alta un Cfdi"() {
    // 	given:'Un CFDI nuevo'
    // 	def cfdi=Cfdi.buildWithoutSave()
    // 	cfdi.cargarXml(cfdi.xml)
    // 	assert !Proveedor.count(),'No debe haber proveedores dados de alta'

    // 	when:'Salvamos el cfdi'
    // 	cfdi.save flush:true

    // 	then:'El emisor es salvado como proveedor'
    // 	Proveedor.count()
    // 	Proveedor.findByRfc(cfdi.getComprobante().emisor.rfc)

    // }
}
