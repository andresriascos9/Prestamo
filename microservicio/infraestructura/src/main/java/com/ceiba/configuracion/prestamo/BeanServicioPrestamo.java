package com.ceiba.configuracion.prestamo;

import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.ServicioActualizarPrestamo;
import com.ceiba.prestamo.servicio.ServicioCrearPrestamo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioPrestamo {

    @Bean
    public ServicioCrearPrestamo servicioCrearPrestamo(RepositorioPrestamo repositorioPrestamo){
        return new ServicioCrearPrestamo(repositorioPrestamo);
    }

    @Bean
    public ServicioActualizarPrestamo servicioActualizarPrestamo(RepositorioPrestamo repositorioPrestamo) {
        return new ServicioActualizarPrestamo(repositorioPrestamo);
    }
}
