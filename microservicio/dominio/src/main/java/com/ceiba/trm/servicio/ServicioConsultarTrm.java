package com.ceiba.trm.servicio;
import com.ceiba.trm.puerto.repositorio.RepositorioTrm;

import java.time.LocalDateTime;
import java.text.DecimalFormat;

public class ServicioConsultarTrm {
    private final RepositorioTrm repositorioTrm;
    private static final String FORMATO_DOS_DECIMALES ="#.00";

    public ServicioConsultarTrm(RepositorioTrm repositorioTrm) {
        this.repositorioTrm = repositorioTrm;
    }

    public double ejecutar(LocalDateTime fechaAConsultar) {
        return darFormatoDosDecimales(repositorioTrm.obtenerTrm(fechaAConsultar));
    }

    public static double darFormatoDosDecimales(double valor){
        DecimalFormat df = new DecimalFormat(FORMATO_DOS_DECIMALES);
        return Double.parseDouble(df.format(valor).replace(',','.'));
    }



}
