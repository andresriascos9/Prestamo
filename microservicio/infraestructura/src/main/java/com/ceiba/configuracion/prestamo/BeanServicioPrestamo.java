package com.ceiba.configuracion.prestamo;

import com.ceiba.persona.puerto.repositorio.RepositorioPersona;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.ServicioCrearPrestamo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioPrestamo {

    @Bean
    public ServicioCrearPrestamo servicioCrearPrestamo(RepositorioPrestamo repositorioPrestamo, RepositorioPersona repositorioPersona){
        return new ServicioCrearPrestamo(repositorioPrestamo, repositorioPersona);
    }
}
