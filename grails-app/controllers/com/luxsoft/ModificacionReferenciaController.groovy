package com.luxsoft

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import com.luxsoft.cfdi.*
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONArray
import grails.validation.Validateable
import grails.transaction.Transactional

@Secured(["hasAnyRole('ROLE_ADMIN')"])
@Transactional(readOnly = true)
class ModificacionReferenciaController {

    def index(Integer max) {
    	if(session.search){
    		def list=getCfdis(session.search)
    		render view:'index',model:[cfdiInstanceList:list,cfdiInstanceCount:list.size()]
    		return
    	}
    	params.max = Math.min(max ?: 50, 5000)
		//params.sort=params.sort?:'dateCreated'
		params.order='desc'
        respond Cfdi.list(params), model:[cfdiInstanceCount: Cfdi.count()]
    }

    def search( CfdiSeach example){
		def list=getCfdis(example)
		session.search=example
		render view:'index',model:[cfdiInstanceList:list,cfdiInstanceCount:list.size()]

    }

    private getCfdis(CfdiSeach example){
    	
    	example.emisor=example.emisor?:'%'
		example.fechaInicial=example.fechaInicial?:new Date()-60
		example.fechaFinal=example.fechaFinal?:new Date()

		example.referencia=example.referencia?:'%'
        example.folio=example.folio?:'%'
		

		params.max = example.max ?: 50
		params.sort=params.sort?:'dateCreated'
		params.order='desc'
		
		def args=[
			example.emisor
			,example.fechaInicial
			,example.fechaFinal
			,example.referencia
			,example.folio
			]
			println 'Argumentos: '+args

		def hql="from Cfdi c where lower(c.emisor) like ?  and date(c.fecha) between ? and ? and c.referencia like ? and c.folio like ?"
		/*
		def hql="from Cfdi c where lower(c.emisor) like ?  and c.referencia like ? and c.folio like ? "+ 
			" and date(c.fecha) between ? and ? "
		if(example.total>0.0){
			args.add(example.total)
			hql+=" and c.total=?"
		}
		*/

		def list=Cfdi.findAll(hql,args,params)
    }

     @Transactional
    def modificar(){
		println 'Modificando referencia de cfdis: '+params
		def referencia=params.referencia
		JSONArray jsonArray=JSON.parse(params.partidas);
		def rows=[]
		jsonArray.each {
			
			def cfdi=Cfdi.get(it.toLong())
			println 'Modificando cfdi: '+cfdi.uuid
			cfdi.referencia=referencia
			cfdi.save flush:true
			
			rows.add([id:cfdi.id,referencia:referencia])
		}
		def res=[comprobantes:rows]
		render res as JSON
		//rendre rows as JSON

	}
}
