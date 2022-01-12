package com.ceiba.persona.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPersona {
    private Long id;
    private int identificacion;
    private String nombre;
}
