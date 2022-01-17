package com.ceiba.prestamo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.persona.comando.ComandoPersona;
import com.ceiba.persona.testDataBuilder.ComandoPersonaTestDataBuilder;
import com.ceiba.prestamo.comando.ComandoPrestamo;
import com.ceiba.prestamo.testDataBuilder.ComandoPrestamoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorPrestamo.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorPrestamoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear un prestamo")
    void deberiaCrearUnPrestamo() throws Exception{
        //creamos primero otra persona
        // arrange
        ComandoPersona persona = new ComandoPersonaTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
        //luego si ejecutamos la prueba con el 2l de id y a esa persona el asignamos el prestamo
        // arrange
        ComandoPrestamo prestamo = new ComandoPrestamoTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("Deberia fallar al crear un prestamo com prestamo sin cancelar")
    void deberiaFallarAlCrearUnPrestamoSinCancelarAnterior() throws Exception{
        // arrange
        ComandoPrestamo prestamo = new ComandoPrestamoTestDataBuilder().buildConPersona(1L);
        // act - assert
        mocMvc.perform(post("/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'La persona ya tiene un prestamo sin cancelar'}"));
    }

    @Test
    @DisplayName("Deberia fallar al crear un prestamo sin existir la persona")
    void deberiaFallarAlCrearUnPrestamoSinExistirLaPersona() throws Exception{
        // arrange
        ComandoPrestamo prestamo = new ComandoPrestamoTestDataBuilder().buildConPersona(3L);
        // act - assert
        mocMvc.perform(post("/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'La persona no existe'}"));
    }
}
