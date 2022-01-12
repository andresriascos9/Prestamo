package com.ceiba.prestamo.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.persona.modelo.dto.DtoPersona;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import org.springframework.jdbc.core.RowMapper;

public class MapeoPrestamo implements RowMapper<DtoPrestamo>, MapperResult{

    @Override
    public DtoPrestamo mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        LocalDate fecha_pago = extraerLocalDate(resultSet, "fecha_pago");
        int valor_prestamo = resultSet.getInt("valor_prestamo");
        Long persona = resultSet.getLong("persona");
        boolean estado_prestamo_pago = resultSet.getBoolean("estado_prestamo_pago");

        return new DtoPrestamo(id,fecha_pago,valor_prestamo,persona,estado_prestamo_pago);
    }
}
