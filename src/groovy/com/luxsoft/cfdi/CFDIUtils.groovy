package com.luxsoft.cfdi

import java.security.PrivateKey
import java.text.SimpleDateFormat


import org.apache.commons.lang.StringUtils
import org.apache.xmlbeans.XmlCursor
import org.apache.xmlbeans.XmlDateTime
import org.apache.xmlbeans.XmlObject
import org.apache.xmlbeans.XmlOptions
import org.apache.xmlbeans.XmlValidationError

import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor.RegimenFiscal
import mx.gob.sat.cfd.x3.TUbicacion
import mx.gob.sat.cfd.x3.TUbicacionFiscal
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor

import javax.xml.namespace.QName
import com.luxsoft.luxor.Direccion

class CFDIUtils {

	/**
	 * 
	 * Transofrma un java.util.Date en un XmlDateTime
	 * El formato usado es ISO8601 'yyyy-MM-dd'T'HH:mm:ss'
	 * @param fecha
	 * @return
	 */
	static XmlDateTime toXmlDate(Date fecha){
		Calendar c=Calendar.getInstance();
		c.setTime(fecha)
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
		XmlDateTime xmlDateTime = XmlDateTime.Factory.newInstance()
		xmlDateTime.setStringValue(df.format(c.getTime()))
		return xmlDateTime
	}

	static Direccion toDireccion(TUbicacionFiscal uf){
		Direccion d=new Direccion()
		d.calle=uf.calle
		d.numeroExterior=uf.getNoExterior()
		d.numeroInterior=uf.getNoInterior()
		d.colonia=uf.colonia
		d.municipio=uf.municipio
		d.estado=uf.estado
		d.pais=uf.pais
		d.codigoPostal=uf.codigoPostal
		return d

		
	}
	
	public validarDocumento(ComprobanteDocument document) {
		List<XmlValidationError> errors=findErrors(document);
		if(errors.size()>0){
			return errors
		}
		return true
	}
	
	List findErrors(final XmlObject node){
		final XmlOptions options=new XmlOptions()
		final List errors=new ArrayList()
		options.setErrorListener(errors)
		node.validate(options)
		return errors
		
	}
	
	static  depurar(ComprobanteDocument document){
		XmlCursor cursor=document.newCursor()
		if(cursor.toFirstChild()){
			QName qname=new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation","xsi")
			cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance","schemaLocation","xsi")
				,"http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd http://www.sat.gob.mx/nomina  http://www.sat.gob.mx/sitio_internet/cfd/nomina/nomina11.xsd" )
			cursor.toNextToken()
			cursor.insertNamespace("cfdi", "http://www.sat.gob.mx/cfd/3")
			cursor.toNextToken()
			cursor.insertNamespace("nomina", "http://www.sat.gob.mx/nomina")
		}
	}
	
	
	
	
	
	
	
}
