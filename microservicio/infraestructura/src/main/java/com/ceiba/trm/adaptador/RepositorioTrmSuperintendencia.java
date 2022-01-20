package com.ceiba.trm.adaptador;

import com.ceiba.infraestructura.excepcion.ExcepcionTrm;
import com.ceiba.trm.puerto.repositorio.RepositorioTrm;
import com.ceiba.trm.superintendencia.TCRMServicesInterfaceProxy;
import com.ceiba.trm.superintendencia.TcrmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Calendar;

@Repository
public class RepositorioTrmSuperintendencia implements RepositorioTrm {

    private static final String ERROR_CONSULTANDO_TRM = "No se pudo obtener la TRM";
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositorioTrmSuperintendencia.class);
    private static final int AJUSTE_MES_CALENDAR = 1;

    @Value("${superintendencia.end-point}")
    private String endPoint;

    @Override
    public double obtenerTrm(LocalDateTime fechaConsultar) {
        Calendar fechaCalendar = convertirACalendar(fechaConsultar);
        try {
            TCRMServicesInterfaceProxy tcrmServicesInterfaceProxy = new TCRMServicesInterfaceProxy(endPoint);
            TcrmResponse tcrmResponse = tcrmServicesInterfaceProxy.queryTCRM(fechaCalendar);
            return tcrmResponse.getValue();
        } catch (RemoteException | ServiceException e) {
            LOGGER.error(e.getMessage());
            throw new ExcepcionTrm(ERROR_CONSULTANDO_TRM, e);
        }
    }

    private Calendar convertirACalendar(LocalDateTime fechaConsultar) {
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.set(fechaConsultar.getYear(), fechaConsultar.getMonthValue() - AJUSTE_MES_CALENDAR, fechaConsultar.getDayOfMonth());
        return fechaCalendar;
    }
}
