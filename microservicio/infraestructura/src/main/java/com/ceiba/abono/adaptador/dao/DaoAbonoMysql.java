package com.ceiba.abono.adaptador.dao;

import java.util.List;

import com.ceiba.abonos.modelo.dto.DtoAbono;
import com.ceiba.abonos.puerto.dao.DaoAbono;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

@Component

public class DaoAbonoMysql implements DaoAbono {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="abono", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="abono", value="listarPorPrestamo")
    private static String sqlListarPorPrestamo;

    public DaoAbonoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoAbono> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoAbono());
    }

    @Override
    public List<DtoAbono> listarPorPrestamo(Long prestamo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("prestamo", prestamo);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorPrestamo, paramSource, new MapeoAbono());
    }
}
