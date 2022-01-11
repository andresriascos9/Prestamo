package com.ceiba.persona.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.persona.modelo.entidad.Persona;
import com.ceiba.persona.servicio.testDataBuilder.PersonaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonaTest {

    @Test
    @DisplayName("Deberia crear correctamente la persona")
    void deberiaCrearCorrectamenteLaPersona() {
        Persona persona = new PersonaTestDataBuilder().build();
        //assert
        assertEquals(1116269914, persona.getIdentificacion());
        assertEquals("Test Lorem Insup", persona.getNombre());
    }

    @Test
    void deberiaFallarConIdentificacionNegativo() {

        //Arrange
        PersonaTestDataBuilder personaTestDataBuilder = new PersonaTestDataBuilder().conIdentificacion(-1055);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    personaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El número de identificación debe ser positivo");
    }

    @Test
    void deberiaFallarSinNombre() {

        //Arrange
        PersonaTestDataBuilder personaTestDataBuilder = new PersonaTestDataBuilder().conNombre(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    personaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe agregar un nombre completo");
    }


}
