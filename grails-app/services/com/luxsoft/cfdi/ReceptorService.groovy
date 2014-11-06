package com.luxsoft.cfdi

import grails.transaction.Transactional
import grails.events.Listener

@Transactional
class ReceptorService {

    //@Listener(namespace='gorm')
    def afterInsert(Cfdi cfdi){
        
        Receptor.withNewSession{
            def receptor=Receptor.findByRfc(cfdi.receptorRfc)
            
            if(!receptor){

            	log.info "Nuevo receptor detectado: "+cfdi.receptor
                receptor=Receptor.fromCfdi(cfdi.getComprobante())
                if(receptor.validate()){
                    receptor.save()
                    log.debug "Receptor registrado: "+receptor
                }
            }
        }
        
    }
}
