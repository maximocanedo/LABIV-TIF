/**
 * EnvioMailSoapImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.encoding.XMLType;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import java.net.URL;

public class EnvioMailSoapImpl implements EnvioMailSoap {
    @Override
    public String enviarMail(String email, String asunto, String mail) {
    	try {
            EnvioMailLocator locator = new EnvioMailLocator();
            EnvioMailSoap envioMailSoap = locator.getEnvioMailSoap();
        	System.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");

            String resultado = envioMailSoap.enviarMail(email, asunto, mail);

            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Exception";
        }/*
        try {
            // Crear un objeto Service
            Service service = new Service();
            Call call = (Call) service.createCall();

            // Establecer la URL del servicio web
            String endpointURL = "https://localhost:44339/EnvioMail.asmx";
            call.setTargetEndpointAddress(new URL(endpointURL));
            // Configurar el método a invocar
            call.setOperationName(new QName("http://microsoft.com/webservices/", "enviarMail"));
            call.addParameter(new QName("http://microsoft.com/webservices/", "Email"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://microsoft.com/webservices/", "Asunto"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName("http://microsoft.com/webservices/", "Mail"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);

            // Establecer propiedades de la llamada
            call.setProperty(HTTPConstants.MC_ACCEPT_GZIP, false);

            // Realizar la llamada al servicio web
            Object[] params = {email, asunto, mail};
            String response = (String) call.invoke(params);

            // Devolver la respuesta del servicio web
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar excepciones en caso de error
            return "Error al llamar al servicio web: " + e.getMessage();
        }*/
    }
}
