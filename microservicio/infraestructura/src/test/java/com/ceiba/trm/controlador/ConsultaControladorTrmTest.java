package com.ceiba.trm.controlador;

import com.ceiba.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorTRM.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorTrmTest {

    private static final String CONSULTA_CONTROLADOR_TRM = "/trm";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultarTrm() throws Exception {
        MockHttpServletRequestBuilder request = get(CONSULTA_CONTROLADOR_TRM);
        MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}
