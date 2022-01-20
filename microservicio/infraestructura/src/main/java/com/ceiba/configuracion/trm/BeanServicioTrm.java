package com.ceiba.configuracion.trm;

import com.ceiba.trm.puerto.repositorio.RepositorioTrm;
import com.ceiba.trm.servicio.ServicioConsultarTrm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioTrm {
    @Bean
    public ServicioConsultarTrm servicioConsultarTrm(RepositorioTrm repositorioTrm) {
        return new ServicioConsultarTrm(repositorioTrm);
    }
}
