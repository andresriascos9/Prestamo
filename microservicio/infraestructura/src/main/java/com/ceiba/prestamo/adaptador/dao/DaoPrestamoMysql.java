package com.ceiba.prestamo.adaptador.dao;

import java.util.List;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.prestamo.puerto.dao.DaoPrestamo;

import org.springframework.stereotype.Component;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;

@Component
public class DaoPrestamoMysql implements DaoPrestamo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="prestamo", value="listar")
    private static String sqlListar;

    public DaoPrestamoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoPrestamo> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoPrestamo());
    }
}
