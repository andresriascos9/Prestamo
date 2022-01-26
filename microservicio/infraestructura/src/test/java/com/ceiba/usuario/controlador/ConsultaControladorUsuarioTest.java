package com.ceiba.usuario.controlador;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorUsuarioTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar usuarios")
    void deberiaListarUsuarios() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("test")))
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    @DisplayName("Deberia listar usuarios por Id")
    void deberiaListarUsuariosPorId() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(get("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.nombre", is("test")))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("Deberia fallar al consultar usuario sin existir")
    void deberiaFallarAlConsultarUsuarioSinExistir() throws Exception{
        // arrange
        // act - assert
        mocMvc.perform(get("/usuarios/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json("{'mensaje': 'No se encontró el usuario con el id: 2'}"));
    }


}
