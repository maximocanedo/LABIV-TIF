/**
 * EnvioMailSoapSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.webservices;

public class EnvioMailSoapSkeleton implements com.microsoft.webservices.EnvioMailSoap, org.apache.axis.wsdl.Skeleton {
    private com.microsoft.webservices.EnvioMailSoap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Email"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Asunto"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "Mail"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("enviarMail", _params, new javax.xml.namespace.QName("http://microsoft.com/webservices/", "EnviarMailResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://microsoft.com/webservices/", "EnviarMail"));
        _oper.setSoapAction("http://microsoft.com/webservices/EnviarMail");
        _myOperationsList.add(_oper);
        if (_myOperations.get("enviarMail") == null) {
            _myOperations.put("enviarMail", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("enviarMail")).add(_oper);
    }

    public EnvioMailSoapSkeleton() {
        this.impl = new com.microsoft.webservices.EnvioMailSoapImpl();
    }

    public EnvioMailSoapSkeleton(com.microsoft.webservices.EnvioMailSoap impl) {
        this.impl = impl;
    }
    public java.lang.String enviarMail(java.lang.String email, java.lang.String asunto, java.lang.String mail) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.enviarMail(email, asunto, mail);
        return ret;
    }

}
