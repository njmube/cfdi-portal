package com.luxsoft.sec

class Usuario {

	transient springSecurityService

	String apellidoPaterno
	String apellidoMaterno
	String nombres
	String nombre
	
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		apellidoPaterno()
		apellidoMaterno()
		nombre()
		enabled()
		accountExpired()
		accountLocked()
		passwordExpired()
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UsuarioRole.findAllByUsuario(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
		capitalizarNombre()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
			
		}
		if (isDirty('apellidoPaterno') || isDirty('apellidoMaterno') || isDirty('nombres')) {
			encodePassword()
			
		}
		
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
	
	private capitalizarNombre(){
		apellidoPaterno=apellidoPaterno.toUpperCase()
		apellidoMaterno=apellidoMaterno.toUpperCase()
		nombres=nombres.toUpperCase()
		nombre="$nombres $apellidoPaterno $apellidoMaterno"
	}
}
