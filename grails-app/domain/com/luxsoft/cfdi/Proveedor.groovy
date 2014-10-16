package com.luxsoft.cfdi

import groovy.transform.ToString
//import groovy.transform.EqualsAndHashCode
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import com.luxsoft.luxor.Direccion

@ToString(excludes='id,version,dateCreated,lastUpdated',includeNames=true,includePackage=false)
class Proveedor {

	String nombre
    
	String rfc
	
	String cfdiEmail
	Direccion direccion
	
	String email1
	String email2
	String telefono1
	String telefono2
	String telefono3
	String fax

	Date dateCreated
	Date lastUpdated

    static constraints = {
    	cfdiEmail nullable:true,email:true
        email1 nullable:true,email:true
        email2 nullable:true,email:true
    	telefono1 nullable:true
    	telefono2 nullable:true
        telefono3 nullable:true
    	fax nullable:true
    }

    static embedded = ['direccion']

    def beforeUpdate() {
    	//capitalizarNombre()
    }

    def beforeInsert() {
    	//capitalizarNombre()
    }

    private capitalizarNombre(){
    	nombre=nombre.toUpperCase()
    }

    static Proveedor fromCfdi(Comprobante c){
        def proveedor =new Proveedor()
        proveedor.nombre=c.getEmisor().getNombre()
        proveedor.rfc=c.getEmisor().rfc
        def d=c.emisor.domicilioFiscal
        proveedor.direccion=CFDIUtils.toDireccion(c.emisor.domicilioFiscal)
        return proveedor
    }


}
