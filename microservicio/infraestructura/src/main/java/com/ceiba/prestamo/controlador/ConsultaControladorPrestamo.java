package com.ceiba.prestamo.controlador;

import com.ceiba.prestamo.consulta.ManejadorListarPrestamos;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
@Api(tags={"Controlador consulta prestamo"})
public class ConsultaControladorPrestamo {

    private final ManejadorListarPrestamos manejadorListarPrestamos;

    public ConsultaControladorPrestamo(ManejadorListarPrestamos manejadorListarPrestamos) {
        this.manejadorListarPrestamos = manejadorListarPrestamos;
    }

    @GetMapping
    @ApiOperation("Listar Prestamos")
    public List<DtoPrestamo> listar() {
        return this.manejadorListarPrestamos.ejecutar();
    }
}
