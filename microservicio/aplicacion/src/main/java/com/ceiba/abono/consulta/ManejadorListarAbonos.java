package com.ceiba.abono.consulta;

import com.ceiba.abonos.modelo.dto.DtoAbono;
import com.ceiba.abonos.puerto.dao.DaoAbono;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarAbonos {

    private final DaoAbono daoAbono;

    public ManejadorListarAbonos(DaoAbono daoAbono){
        this.daoAbono = daoAbono;
    }

    public List<DtoAbono> ejecutar(){ return this.daoAbono.listar(); }

    public List<DtoAbono> consultaPorPrestamo(Long prestamo){ return this.daoAbono.listarPorPrestamo(prestamo); }

}
