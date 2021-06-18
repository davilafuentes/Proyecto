package com.ucreativa.presupuesto.repositories;

import com.ucreativa.presupuesto.entities.Transaccion;

import java.util.List;

public interface Repository {
    void guardarTransaccion(Transaccion pTransaccion);
    List<Transaccion> leerDatos();
}
