package com.ucreativa.presupuesto;

import com.ucreativa.presupuesto.repositories.FileRepository;
import com.ucreativa.presupuesto.services.PresupuestoService;

public class Main {

    public static void main(String[] args) {

        //Inicializar la clase de PresupuestoService.
        PresupuestoService oPresupuestoService = new PresupuestoService(new FileRepository());

        //Llamar el método que inicializa el menú principal de la aplicación.
        oPresupuestoService.inicializaMenuPrincipal();
    }
}
