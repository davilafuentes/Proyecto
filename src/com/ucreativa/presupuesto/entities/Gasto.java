package com.ucreativa.presupuesto.entities;

public class Gasto extends Transaccion {

    //region Declaraciones de Atributos.
    private TipoGasto tipoGasto;
    private Double monto;
    private String descripcion;
    //endregion

    //region Creación de los Constructores.
    public Gasto(TipoTransaccion pTipoTransaccion, TipoGasto pTipoGasto, Double pMonto, String pDescripcion) {
        super(pTipoTransaccion);
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
