package com.ceiba.abono.adaptador.repositorio;

import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.abonos.puerto.repositorio.RepositorioAbono;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioAbonoMysql implements RepositorioAbono {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="abono", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="abono", value="sumaAbonos")
    private static String sqlSumaAbonos;

    public RepositorioAbonoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Abono abono) {
        return this.customNamedParameterJdbcTemplate.crear(abono, sqlCrear);
    }

    public int sumarAbonos(Long prestamo){
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("prestamo", prestamo);
        try{
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSumaAbonos,paramSource, Integer.class);
        }catch (Exception NullPointerException){
            return 0;
        }

    }

}
