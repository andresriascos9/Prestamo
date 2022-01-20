package com.ceiba.trm.superintendencia;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

public interface TCRMServicesInterface extends Remote{
    TcrmResponse queryTCRM(Calendar trmQueryAssociatedDate) throws RemoteException;
}
