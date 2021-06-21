package com.ucreativa.presupuesto.entities;

import java.util.Date;

public abstract class Transaccion {

    //region Declaraciones de Atributos.
    private int id;
    private Date fecha;
    private TipoTransaccion tipoTransaccion;
    //endregion

    //region Creación de los Constructores.
    public Transaccion(Integer pId, Date pFecha, TipoTransaccion pTipoTransaccion) {

        //Establecer los atributos de la clase desde los parámetros.
        this.id = pId;
        this.fecha = pFecha;
        this.tipoTransaccion = pTipoTransaccion;
    }
    //endregion

    //region Gets y Sets
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }
    //endregion
}
