package com.ucreativa.presupuesto.entities;

public class Ingreso extends Transaccion{

    //region Declaraciones de Atributos.
    private TipoIngreso tipoIngreso;
    private Double monto;
    private String descripcion;
    //endregion

    //region Creación de los Constructores.
    public Ingreso(TipoTransaccion pTipoTransaccion, TipoIngreso pTipoIngreso, Double pMonto, String pDescripcion) {
        super(pTipoTransaccion);
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
