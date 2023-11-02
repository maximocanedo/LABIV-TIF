package com.microsoft.webservices;

public class EnvioMailSoapProxy implements com.microsoft.webservices.EnvioMailSoap {
  private String _endpoint = null;
  private com.microsoft.webservices.EnvioMailSoap envioMailSoap = null;
  
  public EnvioMailSoapProxy() {
    _initEnvioMailSoapProxy();
  }
  
  public EnvioMailSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEnvioMailSoapProxy();
  }
  
  private void _initEnvioMailSoapProxy() {
    try {
      envioMailSoap = (new com.microsoft.webservices.EnvioMailLocator()).getEnvioMailSoap();
      if (envioMailSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)envioMailSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)envioMailSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (envioMailSoap != null)
      ((javax.xml.rpc.Stub)envioMailSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.microsoft.webservices.EnvioMailSoap getEnvioMailSoap() {
    if (envioMailSoap == null)
      _initEnvioMailSoapProxy();
    return envioMailSoap;
  }
  
  public java.lang.String enviarMail(java.lang.String email, java.lang.String asunto, java.lang.String mail) throws java.rmi.RemoteException{
    if (envioMailSoap == null)
      _initEnvioMailSoapProxy();
    return envioMailSoap.enviarMail(email, asunto, mail);
  }
  
  
}