package com.ceiba.abono.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.abono.comando.ComandoAbono;
import com.ceiba.abono.comando.manejador.ManejadorCrearAbono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/abonos")
@Api(tags = { "Controlador comando abonos"})
public class comandoControladorAbono {

    private final ManejadorCrearAbono manejadorCrearAbono;

    @Autowired
    public comandoControladorAbono(ManejadorCrearAbono manejadorCrearAbono) {
        this.manejadorCrearAbono = manejadorCrearAbono;
    }

    @PostMapping
    @ApiOperation("Crear abono")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoAbono comandoAbono) {
        return manejadorCrearAbono.ejecutar(comandoAbono);
    }
}
