package com.ceiba.abono.controlador;

import com.ceiba.ApplicationMock;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorAbono.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorAbonoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar abonos")
    void deberiaListarAbonos() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/abonos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].fechaAbono", is("2022-01-16")))
                .andExpect(jsonPath("$[0].valorAbono", is(2000000)))
                .andExpect(jsonPath("$[0].prestamo", is(1)));
    }

    @Test
    @DisplayName("Deberia listar abonos con prestamo")
    void deberiaListarAbonosConPrestamo() throws Exception {
        // arrange
        Long prestamo = 1L;
        // act - assert
        mocMvc.perform(get("/abonos/{prestamo}", prestamo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].fechaAbono", is("2022-01-16")))
                .andExpect(jsonPath("$[0].valorAbono", is(2000000)))
                .andExpect(jsonPath("$[0].prestamo", is(1)));
    }

}
