/**
 * EnvioMailSoapImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class EnvioMailSoapImpl implements EnvioMailSoap {
    @Override
    public String enviarMail(String email, String asunto, String mail) {
    	String resultado;
    	try {
            EnvioMailLocator locator = new EnvioMailLocator();
            EnvioMailSoap envioMailSoap = locator.getEnvioMailSoap();
        	System.setProperty("axis.socketSecureFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");

            resultado = envioMailSoap.enviarMail(email, asunto, mail);            
            return resultado;
        } catch (Exception e) {
        	return e.getMessage();
        }
    }
}
