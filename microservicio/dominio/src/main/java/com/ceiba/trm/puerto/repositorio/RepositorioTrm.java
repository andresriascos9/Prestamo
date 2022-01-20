package com.ceiba.trm.puerto.repositorio;

import java.time.LocalDateTime;

public interface RepositorioTrm {
    double obtenerTrm(LocalDateTime fechaConsultar);
}
