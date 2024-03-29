package com.ceiba.usuario.controlador;

import java.util.List;

import com.ceiba.usuario.consulta.ManejadorListarUsuarios;
import org.springframework.web.bind.annotation.*;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags={"Controlador consulta usuario"})
public class ConsultaControladorUsuario {

    private final ManejadorListarUsuarios manejadorListarUsuarios;

    public ConsultaControladorUsuario(ManejadorListarUsuarios manejadorListarUsuarios) {
        this.manejadorListarUsuarios = manejadorListarUsuarios;
    }

    @GetMapping
    @ApiOperation("Listar Usuarios")
    public List<DtoUsuario> listar() {
        return this.manejadorListarUsuarios.ejecutar();
    }

    @GetMapping(value="/{id}")
    @ApiOperation("Listar un solo Usuario")
    public DtoUsuario usuario(@PathVariable Long id) {
        return this.manejadorListarUsuarios.ejecutarUnSoloUsuario(id);
    }


}
