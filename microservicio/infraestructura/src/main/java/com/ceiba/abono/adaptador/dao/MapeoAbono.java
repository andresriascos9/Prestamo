package com.ceiba.abono.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ceiba.abonos.modelo.dto.DtoAbono;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

public class MapeoAbono implements RowMapper<DtoAbono>, MapperResult{

    @Override
    public DtoAbono mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        LocalDate fecha_abono = extraerLocalDate(resultSet, "fecha_abono");
        int valor_abono = resultSet.getInt("valor_abono");
        Long prestamo = resultSet.getLong("prestamo");

        return new DtoAbono(id,fecha_abono,valor_abono,prestamo);
    }
}
