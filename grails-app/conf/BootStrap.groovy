import com.luxsoft.sec.Role
import com.luxsoft.sec.Usuario
import com.luxsoft.sec.UsuarioRole

class BootStrap {

    def init = { servletContext ->

    	def adminRole=Role.findOrSaveWhere(authority:'ROLE_ADMIN')
		def userRole=Role.findOrSaveWhere(authority:'ROLE_USER')

		
		def admin=Usuario.findByUsername('admin')
		if(!admin){
			admin=new Usuario(username:'admin'
				,password:'admin'
				,apellidoPaterno:'admin'
				,apellidoMaterno:'admin'
				,nombres:'admin'
				,nombre:' ADMIN ADMIN').save(flush:true)
			UsuarioRole.create(admin,userRole,true)
			UsuarioRole.create(admin,adminRole,true)
		}

		def adrian=Usuario.findByUsername('asanchez')
		if(!adrian){
			adrian=new Usuario(username:'asanchez'
				,password:'adrian'
				,apellidoPaterno:'SANCHEZ'
				,apellidoMaterno:'PANIAGUA'
				,nombres:'ADRIAN'
				,nombre:' ADRIAN SANCHEZ PANIAGUA').save(flush:true)
			UsuarioRole.create(adrian,userRole,true)
			UsuarioRole.create(adrian,adminRole,true)
		}


		def raquel=Usuario.findByUsername('rarroyo')
		if(!raquel){
			raquel=new Usuario(username:'rarroyo'
				,password:'rarroyo'
				,apellidoPaterno:'ARROYO'
				,apellidoMaterno:'GARCIA'
				,nombres:'RAQUEL'
				,nombre:' RAQUEL ARROYO GARCIA').save(flush:true)
			UsuarioRole.create(adrian,userRole,true)
			UsuarioRole.create(adrian,adminRole,true)
		}

		
		
		//java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider.BouncyCastleProvider())

    }

    def destroy = {
    }
}
