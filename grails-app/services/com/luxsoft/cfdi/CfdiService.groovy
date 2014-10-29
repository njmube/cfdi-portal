package com.luxsoft.cfdi

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile
import org.apache.commons.lang.exception.ExceptionUtils
import java.io.ByteArrayInputStream
import mx.gob.sat.cfd.x3.ComprobanteDocument
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante
import java.text.DecimalFormat

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



@Transactional
class CfdiService {

	def  consultaService

    def cargarComprobante(MultipartFile xmlFile,String referencia) {

    	Cfdi cfdi=new Cfdi()
    	cfdi.referencia=referencia
    	try {
    		
            cfdi.fileName=xmlFile.getOriginalFilename()
        	cfdi.cargarXml(xmlFile.getBytes())
			
			validarEnElSat(cfdi)
            cfdi.save failOnError:true
			log.info "CFDI importado: "+cfdi.uuid
			return cfdi
    	}
    	catch(Exception e) {
    		//log.error e
    		String msg=ExceptionUtils.getRootCauseMessage(e)
    		log.info msg
    		//throw new CfdiException(message:msg,cfdi:cfdi)
    	}
    }

    

    def Acuse validarEnElSat(Cfdi cfdi){
		def emisor=cfdi.comprobante.emisor.rfc
		def receptor=cfdi.comprobante.receptor.rfc
		DecimalFormat format=new DecimalFormat("####.000000")
		String stotal=format.format(cfdi.comprobante.total)
		String qq="?re=$emisor&rr=$receptor&tt=$stotal&id=$cfdi.uuid"
		log.debug 'Validando en SAT Expresion impresa: '+qq
		Acuse acuse=consultaService.consulta(qq)
		cfdi.acuseEstado=acuse.getEstado().getValue().toString()
		cfdi.acuseCodigoEstatus=acuse.getCodigoEstatus().getValue().toString()
        return acuse
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

    
}

class CfdiException extends RuntimeException{
	String message
	Cfdi cfdi
	List errors
}
