package com.ceiba.prestamo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.prestamo.adaptador.dao.MapeoPrestamo;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
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

    @SqlStatement(namespace="prestamo", value="obtenerPrestamo")
    private static String sqlObtenerPrestamo;

    @SqlStatement(namespace="prestamo", value="obtenerEstadoPrestamo")
    private static String sqlObtenerEstadoPrestamo;

    public RepositorioPrestamoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Prestamo prestamo) {
        return this.customNamedParameterJdbcTemplate.crear(prestamo, sqlCrear);
    }

    @Override
    public void actualizar(Long prestamo, boolean estadoPrestamoPago) {
        Prestamo prestamoObject = new Prestamo(prestamo, estadoPrestamoPago);
        this.customNamedParameterJdbcTemplate.actualizar(prestamoObject, sqlActualizar);
    }

    @Override
    public boolean existePorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }

    @Override
    public boolean obtenerEstadoPrestamo(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerEstadoPrestamo,paramSource, Boolean.class);
    }

    @Override
    public boolean existePersonaPorIdConPrestamoSinCancelar(Long persona) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("persona", persona);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlexistePersonaPorId,paramSource, Boolean.class);
    }

    @Override
    public DtoPrestamo obteneroPrestamo(Long prestamo){
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("prestamo", prestamo);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPrestamo,paramSource,new MapeoPrestamo());
    }
}
