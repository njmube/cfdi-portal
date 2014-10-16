package com.luxsoft.cfdi

import grails.transaction.Transactional
import grails.events.Listener

@Transactional
class ProveedorService {

    def salvar(Proveedor proveedor) {
    	proveedor.validate()
    	if(proveedor.hasErrors()){
    		throw new ProveedorException(proveedor:proveedor,message:'Errores de validacion al salvar proveedor')
    	}
    	proveedor.save failOnError:true
    	return proveedor

    }

    @Listener(namespace='gorm')
    def afterInsert(Cfdi cfdi){
        log.debug "Evaluando cfdi nuevo"
        Proveedor.withNewSession{
            def proveedor=Proveedor.findByNombre(cfdi.emisor)
            log.debug "Buscando proveedor: "+cfdi.emisor
            if(!proveedor){
                proveedor=Proveedor.fromCfdi(cfdi.getComprobante())
                proveedor.save flush:true
                log.debug "Proveedor $proveedor dado de alta"
            }
        }
        
    }
    


}

class ProveedorException extends RuntimeException{
	String message
	Proveedor proveedor
}
