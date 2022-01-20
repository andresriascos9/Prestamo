package com.ceiba.persona.controlador;

import com.ceiba.persona.consulta.ManejadorListarPersonas;
import com.ceiba.persona.modelo.dto.DtoPersona;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "*")
@Api(tags={"Controlador consulta personas"})
public class ConsultaControladorPersona {

    private final ManejadorListarPersonas manejadorListarPersonas;

    public ConsultaControladorPersona(ManejadorListarPersonas manejadorListarPersonas) {
        this.manejadorListarPersonas = manejadorListarPersonas;
    }

    @GetMapping
    @ApiOperation("Listar personas")
    public List<DtoPersona> listar() {
        return this.manejadorListarPersonas.ejecutar();
    }

}
