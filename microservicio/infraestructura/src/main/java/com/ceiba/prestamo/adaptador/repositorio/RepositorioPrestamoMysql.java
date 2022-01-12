package com.ceiba.prestamo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.persona.puerto.repositorio.RepositorioPersona;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.usuario.modelo.entidad.Usuario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPrestamoMysql implements RepositorioPrestamo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="prestamo", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="prestamo", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="prestamo", value="existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace="prestamo", value="existePersonaPorId")
    private static String sqlexistePersonaPorId;

    public RepositorioPrestamoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Prestamo prestamo) {
        return this.customNamedParameterJdbcTemplate.crear(prestamo, sqlCrear);
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        this.customNamedParameterJdbcTemplate.actualizar(prestamo, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }

    @Override
    public boolean existePersonaPorIdConPrestamoSinCancelar(Long persona) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("persona", persona);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlexistePersonaPorId,paramSource, Boolean.class);
    }


}
