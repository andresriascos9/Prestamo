package com.ceiba.trm.superintendencia;

import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashSet;
import java.util.Iterator;

public class TCRMServicesWebServiceLocator extends Service implements TCRMServicesWebService{
    private static final long serialVersionUID = 1L;

    private static final String NAMESPACE_URI = "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/";

    private static final String TRM_WEBSERVICE_PORT_ADDRESS = "http://AlexChacon:8080/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";

    private static final String WSDL_SERVICE_NAME = "TCRMServicesWebServicePort";

    private HashSet<QName> ports = null;

    /**
     * Empty constructor. Please do not delete. Is used by introspection.
     */
    public TCRMServicesWebServiceLocator() {
        //
        // Empty constructor. Is used by introspection.
    }

    public String getTCRMServicesWebServicePortWSDDServiceName() {
        return WSDL_SERVICE_NAME;
    }

    public TCRMServicesInterface getTCRMServicesWebServicePort() throws ServiceException {
        URL endpoint;
        try {
            endpoint = new URL(TRM_WEBSERVICE_PORT_ADDRESS);
        } catch (MalformedURLException e) {
            throw new ServiceException(e);
        }
        return getTCRMServicesWebServicePort(endpoint);
    }

    public TCRMServicesInterface getTCRMServicesWebServicePort(URL portAddress) {
        TCRMServicesWebServiceSoapBindingStub stub = new TCRMServicesWebServiceSoapBindingStub(portAddress, this);
        stub.setPortName(getTCRMServicesWebServicePortWSDDServiceName());
        return stub;
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    @Override
    public Remote getPort(Class serviceEndpointInterface) throws ServiceException {
        try {
            if (TCRMServicesInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                TCRMServicesWebServiceSoapBindingStub stub = new TCRMServicesWebServiceSoapBindingStub(new URL(TRM_WEBSERVICE_PORT_ADDRESS), this);
                stub.setPortName(getTCRMServicesWebServicePortWSDDServiceName());
                return stub;
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        throw new ServiceException("There is no stub implementation for the interface:  " + serviceEndpointInterface.getName());
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    @Override
    public Remote getPort(QName portName, Class serviceEndpointInterface) throws ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }

        String inputPortName = portName.getLocalPart();

        if (WSDL_SERVICE_NAME.equals(inputPortName)) {
            return getTCRMServicesWebServicePort();
        } else {
            Remote stub = getPort(serviceEndpointInterface);
            ((Stub) stub).setPortName(portName);
            return stub;
        }
    }
    @Override
    public QName getServiceName() {
        return new QName(NAMESPACE_URI, "TCRMServicesWebService");
    }
    @Override
    public Iterator<QName> getPorts() {
        if (ports == null) {
            ports = new HashSet<>();
            ports.add(new QName(NAMESPACE_URI, WSDL_SERVICE_NAME));
        }
        return ports.iterator();
    }
}
