package com.ceiba.persona.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.persona.modelo.entidad.Persona;
import com.ceiba.persona.puerto.repositorio.RepositorioPersona;
import com.ceiba.persona.servicio.testDataBuilder.PersonaTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearPersonaTest {

    @Test
    @DisplayName("Deberia Crear el usuario de manera correcta")
    void deberiaCrearLaPersonaDeManeraCorrecta() {
        // arrange
        Persona persona = new PersonaTestDataBuilder().build();
        RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
        Mockito.when(repositorioPersona.existe(Mockito.anyInt())).thenReturn(false);
        Mockito.when(repositorioPersona.crear(persona)).thenReturn(1116269914L);
        ServicioCrearPersona servicioCrearPersona = new ServicioCrearPersona(repositorioPersona);
        // act
        Long idPersona = servicioCrearPersona.ejecutar(persona);
        //- assert
        assertEquals(1116269914L,idPersona);
        Mockito.verify(repositorioPersona, Mockito.times(1)).crear(persona);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la persona")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaPersona() {
        // arrange
        Persona persona = new PersonaTestDataBuilder().build();
        RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
        Mockito.when(repositorioPersona.existe(Mockito.anyInt())).thenReturn(true);
        ServicioCrearPersona servicioCrearPersona = new ServicioCrearPersona(repositorioPersona);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPersona.ejecutar(persona), ExcepcionDuplicidad.class,"Ya existe esta persona");
    }
}


