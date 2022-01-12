package com.ceiba.prestamo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.comando.manejador.ManejadorActualizaPrestamo;
import com.ceiba.prestamo.comando.manejador.ManejadorCrearPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/prestamos")
@Api(tags = { "Controlador comando prestamo"})
public class ComandoControladorPrestamo {

    private final ManejadorCrearPrestamo manejadorCrearPrestamo;
    private final ManejadorActualizaPrestamo manejadorActualizarPrestamo;

    @Autowired
    public ComandoControladorPrestamo(ManejadorCrearPrestamo manejadorCrearPrestamo,
                                      ManejadorActualizaPrestamo manejadorActualizarPrestamo) {
        this.manejadorCrearPrestamo = manejadorCrearPrestamo;
        this.manejadorActualizarPrestamo = manejadorActualizarPrestamo;
    }

    @PostMapping
    @ApiOperation("Crear Usuario")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPrestamo comandoPrestamo) {
        return manejadorCrearPrestamo.ejecutar(comandoPrestamo);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar Prestamo")
    public void actualizar(@RequestBody ComandoPrestamo comandoPrestamo,@PathVariable Long id) {
        comandoPrestamo.setId(id);
        manejadorActualizarPrestamo.ejecutar(comandoPrestamo);
    }
}
