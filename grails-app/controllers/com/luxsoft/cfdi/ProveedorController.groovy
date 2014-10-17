package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProveedorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Proveedor.list(params), model:[proveedorInstanceCount: Proveedor.count()]
    }

    def show(Proveedor proveedorInstance) {
        respond proveedorInstance
    }

    def create() {
        respond new Proveedor(params)
    }

    @Transactional
    def save(Proveedor proveedorInstance) {
        if (proveedorInstance == null) {
            notFound()
            return
        }

        if (proveedorInstance.hasErrors()) {
            respond proveedorInstance.errors, view:'create'
            return
        }

        proveedorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.id])
                redirect proveedorInstance
            }
            '*' { respond proveedorInstance, [status: CREATED] }
        }
    }

    def edit(Proveedor proveedorInstance) {
        respond proveedorInstance
    }

    @Transactional
    def update(Proveedor proveedorInstance) {
        if (proveedorInstance == null) {
            notFound()
            return
        }

        if (proveedorInstance.hasErrors()) {
            respond proveedorInstance.errors, view:'edit'
            return
        }

        proveedorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Proveedor.label', default: 'Proveedor'), proveedorInstance.id])
                redirect proveedorInstance
            }
            '*'{ respond proveedorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Proveedor proveedorInstance) {

        if (proveedorInstance == null) {
            notFound()
            return
        }

        proveedorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Proveedor.label', default: 'Proveedor'), proveedorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def comprobantes(Proveedor proveedor){
        params.max = 500
        def rows=Cfdi.findAllByEmisorRfc(proveedor.rfc,params)
        def total=Cfdi.countByEmisorRfc(proveedor.rfc)
        [rows:rows,totalRows:total,proveedorInstance:proveedor]

        
    }
}
