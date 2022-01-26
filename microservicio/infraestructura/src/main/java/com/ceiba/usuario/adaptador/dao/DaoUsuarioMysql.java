package com.ceiba.usuario.adaptador.dao;

import java.util.List;

import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.usuario.puerto.dao.DaoUsuario;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.usuario.modelo.dto.DtoUsuario;

@Component
public class DaoUsuarioMysql implements DaoUsuario {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    private static final String NO_SE_ENCONTRO_EL_USUARIO = "No se encontró el usuario con el id: %s";

    @SqlStatement(namespace="usuario", value="listar")
    private static String sqlListar;

    @SqlStatement(namespace="usuario", value="listarUsuario")
    private static String sqlListarUsuario;

    public DaoUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoUsuario> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoUsuario());
    }

    @Override
    public DtoUsuario listarUnSoloUsuario(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        List<DtoUsuario>  dtoUsuarios = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarUsuario,paramSource, new MapeoUsuario());
        return dtoUsuarios.stream().findFirst().orElseThrow(() -> new ExcepcionTecnica(
                String.format(NO_SE_ENCONTRO_EL_USUARIO, id)));
    }
}
