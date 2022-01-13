package com.ceiba.configuracion.abono;

import com.ceiba.abonos.puerto.repositorio.RepositorioAbono;
import com.ceiba.abonos.servicio.ServicioCrearAbono;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioAbono {

    @Bean
    public ServicioCrearAbono servicioCrearAbono(RepositorioAbono repositorioAbono, RepositorioPrestamo repositorioPrestamo){
        return new ServicioCrearAbono(repositorioAbono, repositorioPrestamo);
    }
}
