package com.luxsoft.cfdi

import org.springframework.security.access.annotation.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class HomeController {

    def index() {
    	
    	if(grailsApplication.config.grails.plugin.springsecurity.active == true){
			if(!isLoggedIn()){
				redirect (controller:'login')
			}
		}
    }
}
