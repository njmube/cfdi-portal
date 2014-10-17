package com.luxsoft.cfdi


import groovy.transform.ToString
import java.io.ByteArrayInputStream
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante

class Cfdi {
	
	String uuid
	Date fecha
	String folio
	String serie
	String emisor
	String emisorRfc
	String receptor

	String receptorRfc
	String fileName

	String acuseEstado
	String acuseCodigoEstatus
	
	BigDecimal total
	byte[] xml
	byte[] acuse

	
	ComprobanteDocument comprobanteDocument
	
	TimbreFiscal timbreFiscal
	
	Date dateCreated
	Date lastUpdated

	static constraints = {
		serie nullable:true,maxSize:10
		folio nullable:true,maxSize:10
		uuid unique:true
		emisor blank:false,maxSize:600
		receptor blank:false,maxSize:600
		xml maxSize:(1024 * 512)  // 50kb para almacenar el xml
		acuse nullable:true,maxSize:(1024*256)
		fileName nullable:true
		acuseEstado nullable:true
		acuseCodigoEstatus nullable:true
		receptorRfc maxSize:20
		emisorRfc maxSize:20
	}
	
	static transients = ['comprobanteDocument','timbreFiscal']
	
	public Cfdi() {}

	
	Comprobante getComprobante(){
		getComprobanteDocument().getComprobante()
	}
	
	public ComprobanteDocument getComprobanteDocument(){
		if(this.comprobanteDocument==null){
			loadComprobante()
		}
		return this.comprobanteDocument
	}

	public TimbreFiscal getTimbreFiscal(){
		if(!timbreFiscal)
			timbreFiscal=new TimbreFiscal(getComprobante())
		return timbreFiscal
	}
	
	void loadComprobante(){
		ByteArrayInputStream is=new ByteArrayInputStream(getXml())
		this.comprobanteDocument=ComprobanteDocument.Factory.parse(is)
		
	}

	void cargarXml(byte[] data){
		ByteArrayInputStream is=new ByteArrayInputStream(data)
		this.comprobanteDocument=ComprobanteDocument.Factory.parse(is)
		def c=getComprobante()
		this.xml=data
		this.uuid=getTimbreFiscal().uuid
		this.serie=c.serie
		this.folio=c.folio
		this.fecha=c.fecha.getTime()
		this.emisor=c.emisor.nombre
		this.emisorRfc=c.emisor.rfc
		this.receptor=c.receptor.nombre
		this.receptorRfc=c.receptor.rfc
		this.total=c.getTotal()
	}
	
	String toString(){
		return "($emisor) Id:$id   Serie:$serie Folio:$folio  UUID:$uuid "
	}

	
	
}

