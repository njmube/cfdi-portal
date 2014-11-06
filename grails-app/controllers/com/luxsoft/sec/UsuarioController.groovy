package com.luxsoft.sec



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.Validateable

@Transactional(readOnly = true)
@Secured(["hasAnyRole('ROLE_ADMIN')"])
class UsuarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Usuario.list(params), model:[usuarioInstanceCount: Usuario.count()]
    }

    def show(Usuario usuarioInstance) {
        respond usuarioInstance
    }

    def create() {
        // respond new UsuarioCommand(params)
       
        [usuarioInstance:new UsuarioCommand()]
    }

    @Transactional
    def save(UsuarioCommand command) {
        if (command == null) {
            notFound()
            return
        }
        
        if (command.hasErrors()) {
            respond command.errors, view:'create'
            return
        }

        def usuarioInstance=command.toUsuario()

        usuarioInstance.save failOnError:true
        println 'Usuario registrado: '+usuarioInstance.id?:'ERROR'
        command.roles.each{
            Role r=Role.get(it)
            UsuarioRole.create(usuarioInstance,r,false)
            println 'Asignando Rol: '+r
        }


        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect usuarioInstance
            }
            '*' { respond usuarioInstance, [status: CREATED] }
        }
    }

    def edit(Usuario usuarioInstance) {
        respond usuarioInstance
    }

    @Transactional
    def update(Usuario usuarioInstance) {
        if (usuarioInstance == null) {
            notFound()
            return
        }

        if (usuarioInstance.hasErrors()) {
            respond usuarioInstance.errors, view:'edit'
            return
        }

        usuarioInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect usuarioInstance
            }
            '*'{ respond usuarioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Usuario usuarioInstance) {

        if (usuarioInstance == null) {
            notFound()
            return
        }

        usuarioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

@Validateable
class UsuarioCommand{

    String apellidoPaterno
    String apellidoMaterno
    String nombres
    String username
    String password
    String confirmPassword
    boolean enabled = true

    List roles=[]

    static constraints = {
        importFrom Usuario

        password blank: false, nullable: false
        confirmPassword blank: false, nullable: false
    }



    Usuario toUsuario(){
        def u=new Usuario(properties)
        u.capitalizarNombre()

        return u
    }
    
}

