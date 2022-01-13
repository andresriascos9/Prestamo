package com.ceiba.abono.controlador;

import com.ceiba.abono.consulta.ManejadorListarAbonos;
import com.ceiba.abonos.modelo.dto.DtoAbono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/abonos")
@Api(tags={"Controlador consulta abonos"})
public class ConsultaControladorAbono {

    private final ManejadorListarAbonos manejadorListarAbonos;

    public ConsultaControladorAbono(ManejadorListarAbonos manejadorListarAbonos) {
        this.manejadorListarAbonos = manejadorListarAbonos;
    }

    @GetMapping
    @ApiOperation("Listar Abonos")
    public List<DtoAbono> listar() {
        return this.manejadorListarAbonos.ejecutar();
    }

}
