package com.ucreativa.presupuesto.entities;

import java.util.Date;
import java.util.UUID;

public class Transaccion {

    //region Declaraciones de Atributos.
    private int id;
    private TipoTransaccion tipoTransaccion;
    private Date fecha;
    //endregion

    //region Creación de los Constructores.
    public Transaccion(TipoTransaccion pTipoTransaccion) {

        //Establecer los atributos de la clase desde los parámetros.
        this.id = generarIdUnico();
        this.tipoTransaccion = pTipoTransaccion;
        this.fecha = new Date();
    }
    //endregion

    //region Metodos de la Clase
    public static int generarIdUnico() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
    //endregion

    //region Gets y Sets
    public int getId() {
        return id;
    }

    public TipoTransaccion getTipo() {
        return tipoTransaccion;
    }

    public Date getFecha() {
        return fecha;
    }
    //endregion
}
