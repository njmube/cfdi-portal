package com.luxsoft.cfdi



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReceptorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Receptor.list(params), model:[receptorInstanceCount: Receptor.count()]
    }

    def show(Receptor receptorInstance) {
        respond receptorInstance
    }

    def create() {
        respond new Receptor(params)
    }

    @Transactional
    def save(Receptor receptorInstance) {
        if (receptorInstance == null) {
            notFound()
            return
        }

        if (receptorInstance.hasErrors()) {
            respond receptorInstance.errors, view:'create'
            return
        }

        receptorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'receptor.label', default: 'Receptor'), receptorInstance.id])
                redirect receptorInstance
            }
            '*' { respond receptorInstance, [status: CREATED] }
        }
    }

    def edit(Receptor receptorInstance) {
        respond receptorInstance
    }

    @Transactional
    def update(Receptor receptorInstance) {
        if (receptorInstance == null) {
            notFound()
            return
        }

        if (receptorInstance.hasErrors()) {
            respond receptorInstance.errors, view:'edit'
            return
        }

        receptorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Receptor.label', default: 'Receptor'), receptorInstance.id])
                redirect receptorInstance
            }
            '*'{ respond receptorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Receptor receptorInstance) {

        if (receptorInstance == null) {
            notFound()
            return
        }

        receptorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Receptor.label', default: 'Receptor'), receptorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'receptor.label', default: 'Receptor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def comprobantes(Receptor receptor){
        params.max = 500
        def rows=Cfdi.findAllByReceptorRfc(receptor.rfc,params)
        def total=Cfdi.countByReceptorRfc(receptor.rfc)
        //println 'Total: '+total
        [rows:rows,totalRows:total,receptorInstance:receptor]

    }
}
