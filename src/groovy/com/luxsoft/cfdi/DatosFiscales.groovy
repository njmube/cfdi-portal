package com.luxsoft.cfdi

import grails.validation.Validateable
import groovy.transform.ToString
import com.luxsoft.luxor.Direccion


@ToString(includeNames=true,includePackage=false)
@Validateable(nullable=true)
class DatosFiscales implements Serializable{
	
	String rfc
	Direccion direccion
	String email


	
    static constraints = {
		
    }
	
	
}