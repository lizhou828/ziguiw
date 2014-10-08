package com.zigui.webservice;

public class SonNoblenessLocator extends org.apache.axis.client.Service implements com.zigui.webservice.SonNobleness {

    public SonNoblenessLocator() {
    }


    public SonNoblenessLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SonNoblenessLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_ISonNobleness
    private java.lang.String BasicHttpBinding_ISonNobleness_address = "http://webzxb.hsxue.com/ExternalInterface/SonNobleness.svc";

    public java.lang.String getBasicHttpBinding_ISonNoblenessAddress() {
        return BasicHttpBinding_ISonNobleness_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_ISonNoblenessWSDDServiceName = "BasicHttpBinding_ISonNobleness";

    public java.lang.String getBasicHttpBinding_ISonNoblenessWSDDServiceName() {
        return BasicHttpBinding_ISonNoblenessWSDDServiceName;
    }

    public void setBasicHttpBinding_ISonNoblenessWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_ISonNoblenessWSDDServiceName = name;
    }

    public com.zigui.webservice.ISonNobleness getBasicHttpBinding_ISonNobleness() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_ISonNobleness_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_ISonNobleness(endpoint);
    }

    public com.zigui.webservice.ISonNobleness getBasicHttpBinding_ISonNobleness(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zigui.webservice.BasicHttpBinding_ISonNoblenessStub _stub = new com.zigui.webservice.BasicHttpBinding_ISonNoblenessStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_ISonNoblenessWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_ISonNoblenessEndpointAddress(java.lang.String address) {
        BasicHttpBinding_ISonNobleness_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zigui.webservice.ISonNobleness.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zigui.webservice.BasicHttpBinding_ISonNoblenessStub _stub = new com.zigui.webservice.BasicHttpBinding_ISonNoblenessStub(new java.net.URL(BasicHttpBinding_ISonNobleness_address), this);
                _stub.setPortName(getBasicHttpBinding_ISonNoblenessWSDDServiceName());
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
        if ("BasicHttpBinding_ISonNobleness".equals(inputPortName)) {
            return getBasicHttpBinding_ISonNobleness();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "SonNobleness");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_ISonNobleness"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_ISonNobleness".equals(portName)) {
            setBasicHttpBinding_ISonNoblenessEndpointAddress(address);
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
