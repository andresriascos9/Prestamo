package com.ceiba.trm.controlador;
import com.ceiba.trm.consulta.ManejadorConsultaTrm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/trm")
@CrossOrigin(origins = "*")
@Api(tags={"Controlador consulta trm"})
public class ConsultaControladorTRM {

    private final ManejadorConsultaTrm manejadorConsultaTrm;

    public ConsultaControladorTRM(ManejadorConsultaTrm manejadorConsultaTrm) {
        this.manejadorConsultaTrm = manejadorConsultaTrm;
    }

    @GetMapping
    @ApiOperation("Consulta TRM Día")
    public double consultarTrmDia() {
        return this.manejadorConsultaTrm.ejecutar(LocalDateTime.now());
    }
}
