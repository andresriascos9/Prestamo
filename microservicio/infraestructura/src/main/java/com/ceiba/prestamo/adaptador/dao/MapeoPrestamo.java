package com.ceiba.prestamo.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import org.springframework.jdbc.core.RowMapper;

public class MapeoPrestamo implements RowMapper<DtoPrestamo>, MapperResult{

    @Override
    public DtoPrestamo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        LocalDate fechaPago = extraerLocalDate(resultSet, "fecha_pago");
        int valorPrestamo = resultSet.getInt("valor_prestamo");
        Long persona = resultSet.getLong("persona");
        boolean estadoPrestamoPago = resultSet.getBoolean("estado_prestamo_pago");

        return new DtoPrestamo(id,fechaPago,valorPrestamo,persona,estadoPrestamoPago);
    }
}
