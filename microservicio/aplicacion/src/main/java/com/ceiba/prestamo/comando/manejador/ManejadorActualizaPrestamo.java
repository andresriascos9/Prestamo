package com.ceiba.prestamo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.comando.fabrica.FabricaPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.servicio.ServicioActualizarPrestamo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizaPrestamo implements ManejadorComando<ComandoPrestamo> {

    private final FabricaPrestamo fabricaPrestamo;
    private final ServicioActualizarPrestamo servicioActualizarPrestamo;

    public ManejadorActualizaPrestamo(FabricaPrestamo fabricaPrestamo, ServicioActualizarPrestamo servicioActualizarPrestamo) {
        this.fabricaPrestamo = fabricaPrestamo;
        this.servicioActualizarPrestamo = servicioActualizarPrestamo;
    }

    public void ejecutar(ComandoPrestamo comandoPrestamo) {
        Prestamo prestamo = this.fabricaPrestamo.crear(comandoPrestamo);
        this.servicioActualizarPrestamo.ejecutar(prestamo);
    }


}
