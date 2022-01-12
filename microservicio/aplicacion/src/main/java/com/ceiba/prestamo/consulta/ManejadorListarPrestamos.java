package com.ceiba.prestamo.consulta;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPrestamos {

    private final DaoPrestamo daoPrestamo;

    public ManejadorListarPrestamos(DaoPrestamo daoPrestamo){
        this.daoPrestamo = daoPrestamo;
    }

    public List<DtoPrestamo> ejecutar(){ return this.daoPrestamo.listar(); }

}
