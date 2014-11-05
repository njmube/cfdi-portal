class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(controller:'home',view:"index")
		"/"(controller:"home")
        "500"(view:'/error')
	}
}
