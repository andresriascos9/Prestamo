package com.ceiba.trm.superintendencia;

import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;
import org.apache.axis.NoEndPointException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.soap.SOAPConstants;
import org.apache.axis.utils.JavaUtils;

import javax.xml.namespace.QName;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

public class TCRMServicesWebServiceSoapBindingStub extends Stub implements TCRMServicesInterface {
    private static final String NAMESPACE_URI = "http://action.trm.services.generic.action.superfinanciera.nexura.sc.com.co/";
    private static final String W3_XMLSCHEMA = "http://www.w3.org/2001/XMLSchema";
    static OperationDesc[] operations;

    static {
        operations = new OperationDesc[1];
        initOperationDesc1();
    }

    private final List<Class> cachedSerClasses = new ArrayList<>();
    private final List<QName> cachedSerQNames = new ArrayList<>();
    private final List<Class<BeanSerializerFactory>> cachedSerializerFactories = new ArrayList<>();
    private final List<Class<BeanDeserializerFactory>> cachedDeserializerFactories = new ArrayList<>();

    public TCRMServicesWebServiceSoapBindingStub(URL endpointURL, Service service) {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public TCRMServicesWebServiceSoapBindingStub(Service service) {
        if (service == null) {
            super.service = new Service();
        } else {
            super.service = service;
        }
        ((Service) super.service).setTypeMappingVersion("1.2");

        QName qName = new QName(NAMESPACE_URI, "tcrm");

        Class cls = Tcrm.class;
        Class<BeanSerializerFactory> beanSerializerFactory = BeanSerializerFactory.class;
        Class<BeanDeserializerFactory> beanDeserializerFactory = BeanDeserializerFactory.class;


        cachedSerQNames.add(qName);

        cachedSerClasses.add(cls);
        cachedSerializerFactories.add(beanSerializerFactory);
        cachedDeserializerFactories.add(beanDeserializerFactory);

        qName = new QName(NAMESPACE_URI, "tcrmResponse");
        cachedSerQNames.add(qName);
        cls = TcrmResponse.class;
        cachedSerClasses.add(cls);
        cachedSerializerFactories.add(beanSerializerFactory);
        cachedDeserializerFactories.add(beanDeserializerFactory);

    }

    private static void initOperationDesc1() {
        OperationDesc operationDesc = new OperationDesc();
        ParameterDesc param;

        operationDesc.setName("queryTCRM");
        param = new ParameterDesc(new QName("", "tcrmQueryAssociatedDate"), ParameterDesc.IN, new QName(W3_XMLSCHEMA, "dateTime"), Calendar.class, false, false);
        param.setOmittable(true);
        operationDesc.addParameter(param);
        operationDesc.setReturnType(new QName(NAMESPACE_URI, "tcrmResponse"));
        operationDesc.setReturnClass(TcrmResponse.class);
        operationDesc.setReturnQName(new QName("", "return"));
        operationDesc.setStyle(Style.WRAPPED);
        operationDesc.setUse(Use.LITERAL);
        operations[0] = operationDesc;

    }

    protected Call createCall() throws RemoteException {
        try {
            Call call = super._createCall();
            if (super.maintainSessionSet) {
                call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                call.setPortName(super.cachedPortName);
            }

            Enumeration<Object> keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                call.setProperty(key, super.cachedProperties.get(key));
            }
            //
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    //
                    // Must set encoding style before registering serializers
                    call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerializerFactories.size(); ++i) {
                        Class cls = cachedSerClasses.get(i);
                        QName qName = cachedSerQNames.get(i);

                        Class<BeanSerializerFactory> sf = cachedSerializerFactories.get(i);
                        Class<BeanDeserializerFactory> df = cachedDeserializerFactories.get(i);
                        call.registerTypeMapping(cls, qName, sf, df, false);
                    }
                }
            }
            return call;
        } catch (Exception e) {
            throw new AxisFault("Failure trying to get the Call object", e);
        }
    }

    public TcrmResponse queryTCRM(Calendar trmQueryAssociatedDate) throws RemoteException {
        if (super.cachedEndpoint == null) {
            throw new NoEndPointException();
        }

        trustVerificationWorkaround();

        Call call = createCall();
        call.setOperation(operations[0]);
        call.setUseSOAPAction(true);
        call.setSOAPActionURI("");
        call.setEncodingStyle(null);
        call.setProperty(Call.SEND_TYPE_ATTR, Boolean.FALSE);
        call.setProperty(AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        call.setSOAPVersion(SOAPConstants.SOAP11_CONSTANTS);
        call.setOperationName(new QName(NAMESPACE_URI, "queryTCRM"));

        setRequestHeaders(call);
        setAttachments(call);

        Object resp = call.invoke(new Object[]{trmQueryAssociatedDate});

        if (resp instanceof RemoteException) {
            throw (RemoteException) resp;
        } else {
            extractAttachments(call);
            return queryTCRMHelper(resp);
        }
    }

    /**
     * Bypass the HTTPS certificate validations
     */
    private void trustVerificationWorkaround() {
        System.setProperty("org.apache.axis.components.net.SecureSocketFactory", "org.apache.axis.components.net.SunFakeTrustSocketFactory");
    }

    public TcrmResponse queryTCRMHelper(Object resp) {
        try {
            return (TcrmResponse) resp;
        } catch (Exception e) {
            return (TcrmResponse) JavaUtils.convert(resp, TcrmResponse.class);
        }
    }
}
