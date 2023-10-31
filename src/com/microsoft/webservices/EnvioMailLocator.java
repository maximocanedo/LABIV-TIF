/**
 * EnvioMailLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class EnvioMailLocator extends org.apache.axis.client.Service implements com.microsoft.webservices.EnvioMail {

    public EnvioMailLocator() {
    }


    public EnvioMailLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EnvioMailLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EnvioMailSoap
    private java.lang.String EnvioMailSoap_address = "https://localhost:44339/EnvioMail.asmx";

    public java.lang.String getEnvioMailSoapAddress() {
        return EnvioMailSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EnvioMailSoapWSDDServiceName = "EnvioMailSoap";

    public java.lang.String getEnvioMailSoapWSDDServiceName() {
        return EnvioMailSoapWSDDServiceName;
    }

    public void setEnvioMailSoapWSDDServiceName(java.lang.String name) {
        EnvioMailSoapWSDDServiceName = name;
    }

    public com.microsoft.webservices.EnvioMailSoap getEnvioMailSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EnvioMailSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEnvioMailSoap(endpoint);
    }

    public com.microsoft.webservices.EnvioMailSoap getEnvioMailSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.microsoft.webservices.EnvioMailSoapStub _stub = new com.microsoft.webservices.EnvioMailSoapStub(portAddress, this);
            _stub.setPortName(getEnvioMailSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEnvioMailSoapEndpointAddress(java.lang.String address) {
        EnvioMailSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.microsoft.webservices.EnvioMailSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.microsoft.webservices.EnvioMailSoapStub _stub = new com.microsoft.webservices.EnvioMailSoapStub(new java.net.URL(EnvioMailSoap_address), this);
                _stub.setPortName(getEnvioMailSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EnvioMailSoap".equals(inputPortName)) {
            return getEnvioMailSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://microsoft.com/webservices/", "EnvioMail");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "EnvioMailSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EnvioMailSoap".equals(portName)) {
            setEnvioMailSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
