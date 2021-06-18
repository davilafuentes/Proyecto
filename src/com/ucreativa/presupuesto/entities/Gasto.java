package com.ucreativa.presupuesto.entities;

import java.util.Date;

public class Gasto extends Transaccion {

    //region Declaraciones de Atributos.
    private TipoGasto tipoGasto;
    private double monto;
    private String descripcion;
    //endregion

    //region Creación de los Constructores.
    public Gasto(Integer pId, Date pFecha, TipoTransaccion pTipoTransaccion, TipoGasto pTipoGasto, Double pMonto, String pDescripcion) {
        super(pId, pFecha, pTipoTransaccion);
        this.tipoGasto = pTipoGasto;
        this.monto = pMonto;
        this.descripcion = pDescripcion;
    }
    //endregion

    //region Gets y Sets
    public TipoGasto getTipoGasto() {
        return tipoGasto;
    }

    public Double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }
    //endregion
}
