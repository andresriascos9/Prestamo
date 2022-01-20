package com.ceiba.trm.consulta;

import java.time.LocalDateTime;

import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultaTrm {

    private final ServicioConsultarTrm servicioConsultarTrm;

    public ManejadorConsultaTrm(ServicioConsultarTrm servicioConsultarTrm) {
        this.servicioConsultarTrm = servicioConsultarTrm;
    }

    public double ejecutar(LocalDateTime fechaConsulta) {
        return servicioConsultarTrm.ejecutar(fechaConsulta);
    }
}
