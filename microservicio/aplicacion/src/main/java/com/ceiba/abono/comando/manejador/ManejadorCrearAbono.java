package com.ceiba.abono.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.abono.comando.ComandoAbono;
import com.ceiba.abono.comando.fabrica.FabricaAbono;
import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.abonos.servicio.ServicioCrearAbono;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearAbono implements ManejadorComandoRespuesta<ComandoAbono, ComandoRespuesta<Long>> {

    private final FabricaAbono fabricaAbono;
    private final ServicioCrearAbono servicioCrearAbono;

    public ManejadorCrearAbono(FabricaAbono fabricaAbono, ServicioCrearAbono servicioCrearAbono) {
        this.fabricaAbono = fabricaAbono;
        this.servicioCrearAbono = servicioCrearAbono;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoAbono comandoAbono) {
        Abono abono = this.fabricaAbono.crear(comandoAbono);
        return new ComandoRespuesta<>(this.servicioCrearAbono.ejecutar(abono));
    }

}
