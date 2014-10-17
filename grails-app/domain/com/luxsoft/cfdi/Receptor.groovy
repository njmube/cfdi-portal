package com.luxsoft.cfdi

import groovy.transform.ToString
//import groovy.transform.EqualsAndHashCode
//import mx.gob.sat.cfd.x3.ComprobanteDocument
//import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import com.luxsoft.luxor.Direccion

@ToString(excludes='id,version,dateCreated,lastUpdated',includeNames=true,includePackage=false)
class Receptor {

	String nombre
	String rfc
	String cfdiEmail

	Direccion direccion

	Date dateCreated
	Date lastUpdated

    static constraints = {
    }

    static embedded = ['direccion']

    def beforeUpdate() {
    	capitalizarNombre()
    }

    def beforeInsert() {
    	capitalizarNombre()
    }

    private capitalizarNombre(){
    	nombre=nombre.toUpperCase()
    }
}
