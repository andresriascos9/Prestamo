package com.ceiba.abono.comando.fabrica;

import com.ceiba.abono.comando.ComandoAbono;
import com.ceiba.abonos.modelo.entidad.Abono;
import org.springframework.stereotype.Component;

@Component
public class FabricaAbono {

    public Abono crear (ComandoAbono comandoAbono){
        return new Abono(comandoAbono.getId(), comandoAbono.getFecha_abono(),
                comandoAbono.getValor_abono(), comandoAbono.getPrestamo());
    }
}
