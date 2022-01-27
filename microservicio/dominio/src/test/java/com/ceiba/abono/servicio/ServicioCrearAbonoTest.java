package com.ceiba.abono.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.abono.servicio.testDataBuilder.AbonoTestDataBuilder;
import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.abonos.puerto.repositorio.RepositorioAbono;
import com.ceiba.abonos.servicio.ServicioCrearAbono;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearAbonoTest {

    private RepositorioPrestamo repositorioPrestamo;
    private RepositorioAbono repositorioAbono;
    private ServicioCrearAbono servicioCrearAbono;

    @BeforeEach
    void setUp() {
        repositorioPrestamo = Mockito.mock(RepositorioPrestamo.class);
        repositorioAbono = Mockito.mock(RepositorioAbono.class);
        servicioCrearAbono = new ServicioCrearAbono(repositorioAbono, repositorioPrestamo);
    }

    @Test
    @DisplayName("Deberia Crear el abono de manera correcta con un valor restante")
    void deberiaCrearElAbonoDeManeraCorrecta() {
        // arrange
        Abono abono = new AbonoTestDataBuilder().build();
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        // act
        Long idAbono = servicioCrearAbono.ejecutar(abono);
        //assert
        assertEquals(250000, abono.getValorAbono());
        assertEquals(1l, idAbono);
    }

    @Test
    @DisplayName("Deberia Crear el abono de manera correcta con pago total en dos abonos")
    void deberiaCrearElAbonoDeManeraCorrectaConDosAbonos() {
        // arrange
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(1000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(2000000).when(repositorioAbono).sumarAbonos(abono.getPrestamo());
        Mockito.doNothing().when(repositorioPrestamo).actualizar(abono.getPrestamo(), true);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        // act
        Long idAbono = servicioCrearAbono.ejecutar(abono);
        //assert
        assertEquals(1000000, abono.getValorAbono());
        assertEquals(1l, idAbono);
    }

    @Test
    @DisplayName("Deberia fallar porque el saldo es mayor a abono y está en fecha vencida")
    void deberiaFallarElAbonoPorqueSaldoEsMayorAbono() {
        // arrange
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(2000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        prestamo.setFechaPago(LocalDate.now().minusDays(2));
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(2000000).when(repositorioAbono).sumarAbonos(abono.getPrestamo());
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        //assert
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"El abono es mayor a el saldo del préstamo");
    }

    @Test
    @DisplayName("Deberia crear el abono con un descuento por un solo pago en fecha anticipada")
    void deberiaCrearElAbonoConDescuentoDeFechaActualizada() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(2850000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        Mockito.doNothing().when(repositorioPrestamo).actualizar(abono.getPrestamo(), true);
        // act
        Long idAbono = servicioCrearAbono.ejecutar(abono);
        //assert
        assertEquals(2850000, abono.getValorAbono());
        assertEquals(1l, idAbono);
    }

    @Test
    @DisplayName("Deberia crear el abono con un valor mayor en fecha vencida")
    void deberiaCrearElAbonoConMoraDeFechaVencida() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(3120000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        prestamo.setFechaPago(LocalDate.now().minusDays(2));
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        Mockito.doNothing().when(repositorioPrestamo).actualizar(abono.getPrestamo(), true);
        // act
        Long idAbono = servicioCrearAbono.ejecutar(abono);
        //assert
        assertEquals(3120000, abono.getValorAbono());
        assertEquals(1l, idAbono);
    }

    @Test
    @DisplayName("Deberia fallar el abono con un valor menor en fecha vencida")
    void deberiaFallarElAbonoConMoraDeFechaVencida() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(3000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        prestamo.setFechaPago(LocalDate.now().minusDays(2));
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        //assert
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"Tienes una tasa de mora por pago vencido, su valor a pagar es: $3120000");
    }

    @Test
    @DisplayName("Deberia fallar el abono con un valor menor en fecha vencida y tiene abono anterior")
    void deberiaFallarElAbonoConMoraDeFechaVencidaYTieneAbonoAnterior() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(2000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        prestamo.setFechaPago(LocalDate.now().minusDays(2));
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(false).when(repositorioPrestamo).obtenerEstadoPrestamo(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        Mockito.doReturn(1000000).when(repositorioAbono).sumarAbonos(abono.getPrestamo());
        //assert
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"Tienes una tasa de mora por pago vencido, su valor a pagar es: $2080000");
    }

    @Test
    @DisplayName("Deberia fallar el abono con un valor mayor al saldo")
    void deberiaFallarElAbonoConAbonoMayorASaldo() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(2500000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        Mockito.doReturn(1000000).when(repositorioAbono).sumarAbonos(abono.getPrestamo());
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"El abono es mayor a el saldo del préstamo");
    }

    @Test
    @DisplayName("Deberia fallar el abono con un valor de abono igual a valor de prestamo con descuento")
    void deberiaFallarElAbonoConDescuentoAplicadoPorFecha() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(3000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"Tienes un descuento por pago anticipado, su valor a pagar es: $2850000");
    }


    @Test
    @DisplayName("Deberia fallar porque el prestamo no existe")
    void deberiaFallarPrestamoNoExiste() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(3000000);
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"El préstamo no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia fallar porque el prestamo ya está cancelado")
    void deberiaFallarElAbonoPorPrestamoCancelado() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(3000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(true).when(repositorioPrestamo).obtenerEstadoPrestamo(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"El préstamo ya está cancelado");
    }

    @Test
    @DisplayName("Deberia Fallar el abono con descuento por fecha de pago en el día de hoy")
    void deberiaFallarElAbonoPorPagoConFechaDePagoHoy() {
        Abono abono = new AbonoTestDataBuilder().buildConValorAbono(3000000);
        Prestamo prestamo = new PrestamoTestDataBuilder().build();
        prestamo.setFechaPago(LocalDate.now());
        DtoPrestamo dtoPrestamo = new DtoPrestamo(prestamo.getId(), prestamo.getFechaPago(),prestamo.getValorPrestamo(),
                prestamo.getPersona(),prestamo.getEstadoPrestamoPago());
        Mockito.doReturn(1L).when(repositorioAbono).crear(abono);
        Mockito.doReturn(true).when(repositorioPrestamo).existePorId(abono.getPrestamo());
        Mockito.doReturn(dtoPrestamo).when(repositorioPrestamo).obteneroPrestamo(abono.getPrestamo());
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearAbono.ejecutar(abono),
                ExcepcionValorInvalido.class,"Tienes un descuento por pago anticipado, su valor a pagar es: $2850000");
    }


}
