package com.luxsoft.cfdi

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.lang.exception.ExceptionUtils
import java.io.ByteArrayInputStream
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import java.text.DecimalFormat

import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller



@Transactional
class CfdiService {

	def  consultaService

    def cargarComprobante(byte[] bytes,String fileName,String referencia,String grupo,String usuario) {

    	try {
            Cfdi cfdi=new Cfdi()
            cfdi.fileName=fileName
            cfdi.cargarXml(bytes)
            
            def found=Cfdi.findByUuid(cfdi.uuid)
            if(found){
                throw new CfdiException(message:"UUID ya registrado $found.uuid",cfdi:cfdi)
            }
            
            cfdi.referencia=referencia
            cfdi.grupo=grupo
            cfdi.usuario=usuario

            cfdi.save (failOnError:true)
            
            validarEnElSat(cfdi)
            log.info "CFDI importado: "+cfdi.uuid
            return cfdi    
    	}
    	catch(Exception e) {
    		//log.error e
            e.printStackTrace()
    		String msg=ExceptionUtils.getRootCauseMessage(e)
    		log.info msg
    		throw new CfdiException(message:msg)
    	}
    }

    

    def Acuse validarEnElSat(Cfdi cfdi){
        try {
            def emisor=cfdi.comprobante.emisor.rfc
            def receptor=cfdi.comprobante.receptor.rfc
            DecimalFormat format=new DecimalFormat("####.000000")
            String stotal=format.format(cfdi.comprobante.total)
            String qq="?re=$emisor&rr=$receptor&tt=$stotal&id=$cfdi.uuid"
            log.debug 'Validando en SAT Expresion impresa: '+qq
            Acuse acuse=consultaService.consulta(qq)
            cfdi.acuseEstado=acuse.getEstado().getValue().toString()
            cfdi.acuseCodigoEstatus=acuse.getCodigoEstatus().getValue().toString()
            registrarAcuse(cfdi,acuse)
            cfdi.save()
            return acuse
        }
        catch(Exception e) {
            String msg=ExceptionUtils.getRootCauseMessage(e)
            cfdi.acuseEstado='PENDIENTE'
            cfdi.acuseCodigoEstatus=msg
            throw new CfdiException(message:msg,cfdi:cfdi)
        }
        
		
    }

    def  toXml(Acuse acuse){
        try {
            JAXBContext context = JAXBContext.newInstance(Acuse.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
            StringWriter w=new StringWriter();
            m.marshal(acuse,w);
            println w
            return w.toString();
            // Write to File
            //m.marshal(emp, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    def  registrarAcuse(Cfdi cfdi,Acuse acuse){
        try {
            JAXBContext context = JAXBContext.newInstance(Acuse.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ByteArrayOutputStream out=new ByteArrayOutputStream()
            m.marshal(acuse,out);
            cfdi.acuse=out.toByteArray()
            
            // Write to File
            //m.marshal(emp, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    def  toAcuse(byte[] data){
        try {
            JAXBContext context = JAXBContext.newInstance(Acuse.class)
            Unmarshaller u = context.createUnmarshaller()
            ByteArrayInputStream is=new ByteArrayInputStream(data)
            Object o = u.unmarshal( is )
            return (Acuse)o
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



    
}

class CfdiException extends RuntimeException{
	String message
	Cfdi cfdi
	List errors
}
