package com.ceiba.configuracion.persona;

import com.ceiba.persona.puerto.repositorio.RepositorioPersona;
import com.ceiba.persona.servicio.ServicioCrearPersona;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioPersona {

    @Bean
    public ServicioCrearPersona servicioCrearPersona(RepositorioPersona repositorioPersona) {
        return new ServicioCrearPersona(repositorioPersona);
    }
}
