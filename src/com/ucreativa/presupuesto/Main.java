package com.ucreativa.presupuesto;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Llamar al menu principal del programa que a su vez llama a la funcion de seleccion.
        inicializaMenuPrincipal();

    }

    public static void inicializaMenuPrincipal(){

        System.out.println("Ingrese cualquier número entre 1 y 4");
        System.out.println();
        System.out.println("1) Crear Ingreso");
        System.out.println("2) Crear Gasto");
        System.out.println("3) Generar Reporte de Presupuesto");
        System.out.println("4) Salir");

        //Esperar por la seleccion para realizar el proceso indicado.
        ejecutaSeleccion();
    }

    public static void ejecutaSeleccion(){

        //Declaracion de nuestro scanner.
        Scanner myScanner = new Scanner(System.in);

        //Variable que contiene la seleccion del usuario desde el menu.
        int iSeleccion = 0;

        //Comprobar que la seleccion sea un numero entero.
        while (!myScanner.hasNextInt()) {

            //No es numero entero, imprimir error y volverlo a intentar.
            System.out.println("Opción Invalida. Intente de nuevo...");

            //Llamar al menu principal del programa que a su vez llama a la funcion de seleccion.
            inicializaMenuPrincipal();
        }

        //Al contener un numero entero, se continua con el proceso.
        iSeleccion = myScanner.nextInt();

        //Seleccion 1.
        if (iSeleccion == 1){

            System.out.println("*Crear Ingreso*");

        }

        //Seleccion 2.
        else if (iSeleccion == 2){

            System.out.println("*Crear Gasto*");

        }

        //Seleccion 3.
        else if (iSeleccion == 3){

            System.out.println("*Generar Reporte de Presupuesto*");

        }

        //Seleccion 4.
        else if (iSeleccion == 4){
            System.exit(0);;
        }

        //Ninguna opcion listada en el menu, imprimir error y volver a intentarlo.
        else {
            System.out.println("Opción Invalida. Intente de nuevo...");
            inicializaMenuPrincipal();
        }
    }
}
