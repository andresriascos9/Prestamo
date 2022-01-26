package com.ceiba.trm.superintendencia;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;
import java.rmi.RemoteException;
import java.util.Calendar;


public class TCRMServicesInterfaceProxy implements TCRMServicesInterface{
    private static final Logger LOGGER = LoggerFactory.getLogger(TCRMServicesInterfaceProxy.class);
    private static final String ENDPOINT_PROPERTY = "javax.xml.rpc.service.endpoint.address";
    private static final String SERVICIO_EXCEPCION = "Excepción en el servicio";
    private String endPoint;
    private TCRMServicesInterface tCRMServicesInterface = null;

    public TCRMServicesInterfaceProxy(String endPoint) throws ServiceException {
        this.endPoint = endPoint;
        initTCRMServicesInterfaceProxy();
    }

    private void initTCRMServicesInterfaceProxy() throws ServiceException {

        this.tCRMServicesInterface = (new TCRMServicesWebServiceLocator()).getTCRMServicesWebServicePort();
        if (this.tCRMServicesInterface != null) {
            if (endPoint != null)
                ((Stub) this.tCRMServicesInterface)._setProperty(ENDPOINT_PROPERTY, this.endPoint);
            else
                this.endPoint = (String) ((Stub) this.tCRMServicesInterface)._getProperty(ENDPOINT_PROPERTY);
        }
    }

    public TcrmResponse queryTCRM(Calendar trmQueryAssociatedDate) throws RemoteException {
        try {
            if (this.tCRMServicesInterface == null)
                initTCRMServicesInterfaceProxy();
            return this.tCRMServicesInterface.queryTCRM(trmQueryAssociatedDate);
        }catch (RemoteException e){
            LOGGER.info(e.getMessage());
            throw  new RemoteException(e.getMessage());
        }catch (ServiceException e){
            throw new ExcepcionTecnica(SERVICIO_EXCEPCION);
        }
    }
}
