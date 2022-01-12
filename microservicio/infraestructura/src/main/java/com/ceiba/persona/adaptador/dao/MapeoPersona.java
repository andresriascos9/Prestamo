package com.ceiba.persona.adaptador.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.persona.modelo.dto.DtoPersona;
import org.springframework.jdbc.core.RowMapper;

public class MapeoPersona implements RowMapper<DtoPersona>, MapperResult{

    @Override
    public DtoPersona mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        int identificacion = resultSet.getInt("identificacion");
        String nombre = resultSet.getString("nombre");

        return new DtoPersona(id,identificacion,nombre);
    }
}
