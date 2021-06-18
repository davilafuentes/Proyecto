package com.ucreativa.presupuesto.entities;

import java.util.Date;

public class Ingreso extends Transaccion{

    //region Declaraciones de Atributos.
    private TipoIngreso tipoIngreso;
    private double monto;
    private String descripcion;
    //endregion

    //region Creación de los Constructores.
    public Ingreso(Integer pId, Date pFecha, TipoTransaccion pTipoTransaccion, TipoIngreso pTipoIngreso, Double pMonto, String pDescripcion) {
        super(pId, pFecha, pTipoTransaccion);
        this.tipoIngreso = pTipoIngreso;
        this.monto = pMonto;
        this.descripcion = pDescripcion;
    }
    //endregion

    //region Gets y Sets
    public TipoIngreso getTipoIngreso() {
        return tipoIngreso;
    }

    public Double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }
    //endregion
}
