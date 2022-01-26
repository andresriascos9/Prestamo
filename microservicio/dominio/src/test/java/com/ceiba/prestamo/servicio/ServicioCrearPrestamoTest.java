package com.ceiba.prestamo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.persona.puerto.repositorio.RepositorioPersona;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearPrestamoTest {

    @Test
    @DisplayName("Deberia Crear el prestamo de manera correcta")
    void deberiaCrearElPrestamoDeManeraCorrecta() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
        Mockito.when(repositorioPersona.existePersonaPorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioPrestamo.crear(prestamo)).thenReturn(10L);
        ServicioCrearPrestamo servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioPrestamo, repositorioPersona);
        // act
        Long idPrestamo = servicioCrearPrestamo.ejecutar(prestamo);
        //- assert
        assertEquals(10L,idPrestamo);
        Mockito.verify(repositorioPrestamo, Mockito.times(1)).crear(prestamo);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del prestamo a la persona sin tener el otro prestamo pago")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaPersonaSinCancelarPago() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
        Mockito.doReturn(true).when(repositorioPrestamo).existePersonaPorIdConPrestamoSinCancelar(prestamo.getPersona());
        ServicioCrearPrestamo servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioPrestamo, repositorioPersona);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPrestamo.ejecutar(prestamo),
                ExcepcionDuplicidad.class,"La persona ya tiene un prestamo sin cancelar");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la persona")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaPersona() {
        // arrange
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        RepositorioPrestamo repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        RepositorioPersona repositorioPersona = Mockito.mock(RepositorioPersona.class);
        Mockito.doReturn(false).when(repositorioPersona).existePersonaPorId(prestamo.getPersona());
        ServicioCrearPrestamo servicioCrearPrestamo = new ServicioCrearPrestamo(repositorioPrestamo, repositorioPersona);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPrestamo.ejecutar(prestamo),
                ExcepcionValorInvalido.class,"La persona no existe");
    }
}
