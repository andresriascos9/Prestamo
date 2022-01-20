package com.ceiba.persona.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.persona.comando.ComandoPersona;
import com.ceiba.persona.comando.manejador.ManejadorCrearPersona;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "*")
@Api(tags = { "Controlador comando personas"})
public class ComandoControladorPersona {

    private final ManejadorCrearPersona manejadorCrearpersona;

    public ComandoControladorPersona(ManejadorCrearPersona manejadorCrearpersona) {
        this.manejadorCrearpersona = manejadorCrearpersona;
    }

    @PostMapping
    @ApiOperation("Crear Persona")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPersona comandoPersona) {
        return manejadorCrearpersona.ejecutar(comandoPersona);
    }
}
