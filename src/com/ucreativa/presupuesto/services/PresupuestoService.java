package com.ucreativa.presupuesto.services;

import com.ucreativa.presupuesto.entities.*;
import com.ucreativa.presupuesto.repositories.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PresupuestoService {

    //region Declaraciones de Atributos.

    //Declaracion del Repositorio.
    private Repository oRepository;

    //Declaracion de nuestro scanner.
    Scanner myScanner = new Scanner(System.in);

    //Variable que contiene la seleccion del usuario desde el menu.
    int iSeleccion = 0;

    //Variables para almacenar la información del User Input.
    double inputMonto;
    Date inputFecha;
    String inputDescripcion;
    //endregion

    //region Creación de los Constructores.
    public PresupuestoService(Repository pRepository) {
        this.oRepository = pRepository;
    }

    public PresupuestoService() {

    }
    //endregion

    //region Metodos de la Clase
    public void inicializaMenuPrincipal() {

        System.out.println();
        System.out.println("***** Menú Principal *****");
        System.out.println();
        System.out.println("Ingrese cualquier número entre 1 y 6");
        System.out.println("1) Crear Ingreso");
        System.out.println("2) Crear Gasto");
        System.out.println("3) Generar Gráfico Circular");
        System.out.println("4) Imprimir Transacciones Almacenadas");
        System.out.println("5) Salir");

        //Esperar por la seleccion para realizar el proceso indicado.
        ejecutaSeleccionPrincipal();
    }

    public void ejecutaSeleccionPrincipal() {

        //Comprobar que la seleccion sea un numero entero.
        while (!myScanner.hasNextInt()) {

            //No es numero entero, imprimir error y volverlo a intentar.
            System.out.println("Opción Invalida. Intente de nuevo...");

            //Limpiar Scanner.
            myScanner.next();

            //Llamar al menu principal del programa que a su vez llama a la funcion de seleccion.
            inicializaMenuPrincipal();
        }

        //Al contener un numero entero, se continua con el proceso.
        iSeleccion = myScanner.nextInt();

        //Seleccion 1.
        if (iSeleccion == 1) {
            inicializaMenuIngreso();
        }

        //Seleccion 2.
        else if (iSeleccion == 2) {
            inicializaMenuGasto();
        }

        //Seleccion 3.
        else if (iSeleccion == 3) {
            inicializaMenuGrafico();
        }

        //Seleccion 4.
        else if (iSeleccion == 4) {
            inicializaMenuImpresion();
        }

        //Seleccion 5.
        else if (iSeleccion == 5) {
            System.exit(0);
        }

        //Ninguna opcion listada en el menu, imprimir error y volver a intentarlo.
        else {
            System.out.println("Opción Invalida. Intente de nuevo...");
            inicializaMenuPrincipal();
        }
    }

    public void inicializaMenuIngreso() {
        System.out.println("Seleccione el Tipo de Ingreso: ");
        System.out.println();
        System.out.println("1) Salario");
        System.out.println("2) Extra");
        System.out.println("3) Regresar al menú principal");
        System.out.println("4) Salir");

        //Esperar por la seleccion para realizar el proceso indicado.
        ejecutaSeleccionIngreso();
    }

    public void ejecutaSeleccionIngreso() {
        //Comprobar que la seleccion sea un numero entero.
        while (!myScanner.hasNextInt()) {

            //No es numero entero, imprimir error y volverlo a intentar.
            System.out.println("Opción Invalida. Intente de nuevo...");

            //Limpiar Scanner.
            myScanner.next();

            //Llamar al menu principal de Ingreso.
            inicializaMenuIngreso();
        }

        //Al contener un numero entero, se continua con el proceso.
        iSeleccion = myScanner.nextInt();

        //Seleccion 1.
        if (iSeleccion == 1) {
            generarIngreso(TipoIngreso.SALARIO);
        }

        //Seleccion 2.
        else if (iSeleccion == 2) {
            generarIngreso(TipoIngreso.EXTRA);
        }

        //Seleccion 3.
        else if (iSeleccion == 3) {
            inicializaMenuPrincipal();
        }

        //Seleccion 4.
        else if (iSeleccion == 4) {
            System.exit(0);
        }

        //Ninguna opcion listada en el menu, imprimir error y volver a intentarlo.
        else {
            System.out.println("Opción Invalida. Intente de nuevo...");
            inicializaMenuIngreso();
        }
    }

    public void generarIngreso(TipoIngreso pTipoIngreso) {
        System.out.println("*Crear Ingreso*");
        System.out.println("");

        //Obtener el monto del ingreso.
        while (true) {
            System.out.println("1. Ingrese el monto total del ingreso a realizar: ");

            try {
                inputMonto = Double.parseDouble(myScanner.next());
                break;
            } catch (Exception e) {
                System.out.println("El monto digitado no es válido. Intente de nuevo...");
            }
        }

        //Obtener la fecha del ingreso.
        while (true) {
            System.out.println("2. Ingrese la fecha del ingreso (dd/MM/yyyy): ");

            try {
                inputFecha = new SimpleDateFormat("dd/MM/yyyy").parse(myScanner.next());
                break;
            } catch (Exception e) {
                System.out.println("La fecha digitada no es válida. Intente de nuevo...");
            }
        }

        //Obtener la descripción del ingreso.
        System.out.println("3. Ingrese la descripción del ingreso a realizar: ");
        inputDescripcion = myScanner.next();

        //Creación del objeto Ingreso.
        Ingreso oIngreso = new Ingreso(generarIdUnico(), inputFecha, TipoTransaccion.INGRESO, pTipoIngreso, inputMonto, inputDescripcion);

        //Enviar a guardar el Ingreso.
        this.oRepository.guardarTransaccion(oIngreso);

    }

    public void inicializaMenuGasto() {
        System.out.println("Seleccione el Tipo de Gasto: ");
        System.out.println();
        System.out.println("1) Personal");
        System.out.println("2) Préstamo");
        System.out.println("3) Servicio Público");
        System.out.println("4) Educación");
        System.out.println("5) Regresar al menú principal");
        System.out.println("6) Salir");

        //Esperar por la seleccion para realizar el proceso indicado.
        ejecutaSeleccionGasto();
    }

    public void ejecutaSeleccionGasto() {
        //Comprobar que la seleccion sea un numero entero.
        while (!myScanner.hasNextInt()) {

            //No es numero entero, imprimir error y volverlo a intentar.
            System.out.println("Opción Invalida. Intente de nuevo...");

            //Limpiar Scanner.
            myScanner.next();

            //Llamar al menu principal de Gasto.
            inicializaMenuGasto();
        }

        //Al contener un numero entero, se continua con el proceso.
        iSeleccion = myScanner.nextInt();

        //Seleccion 1.
        if (iSeleccion == 1) {
            generarGasto(TipoGasto.PERSONAL);
        }

        //Seleccion 2.
        else if (iSeleccion == 2) {
            generarGasto(TipoGasto.PRESTAMO);
        }

        //Seleccion 3.
        else if (iSeleccion == 3) {
            generarGasto(TipoGasto.SERVICIO_PUBLICO);
        }

        //Seleccion 4.
        else if (iSeleccion == 4) {
            generarGasto(TipoGasto.EDUCACION);
        }

        //Seleccion 5.
        else if (iSeleccion == 5) {
            inicializaMenuPrincipal();
        }

        //Seleccion 6.
        else if (iSeleccion == 6) {
            System.exit(0);
        }

        //Ninguna opcion listada en el menu, imprimir error y volver a intentarlo.
        else {
            System.out.println("Opción Invalida. Intente de nuevo...");
            inicializaMenuGasto();
        }
    }

    public void generarGasto(TipoGasto pTipoGasto) {
        System.out.println("*Crear Gasto*");
        System.out.println("");

        //Obtener el monto del gasto.
        while (true) {
            System.out.println("1. Ingrese el monto total del gasto realizado: ");

            try {
                inputMonto = Double.parseDouble(myScanner.next());
                break;
            } catch (Exception e) {
                System.out.println("El monto digitado no es válido. Intente de nuevo...");
            }
        }

        //Obtener la fecha del gasto.
        while (true) {
            System.out.println("2. Ingrese la fecha del gasto (dd/MM/yyyy): ");

            try {
                inputFecha = new SimpleDateFormat("dd/MM/yyyy").parse(myScanner.next());
                break;
            } catch (Exception e) {
                System.out.println("La fecha digitada no es válida. Intente de nuevo...");
            }
        }

        //Obtener la descripción del gasto.
        System.out.println("3. Ingrese la descripción del gasto realizado: ");
        inputDescripcion = myScanner.next();

        //Creación del objeto Gasto.
        Gasto oGasto = new Gasto(generarIdUnico(), inputFecha, TipoTransaccion.GASTO, pTipoGasto, inputMonto, inputDescripcion);

        //Enviar a guardar el Gasto.
        this.oRepository.guardarTransaccion(oGasto);
    }

    public void inicializaMenuGrafico() {
        System.out.println("Seleccione una opción: ");
        System.out.println();
        System.out.println("1) Generar Gráfico Circular");
        System.out.println("2) Regresar al menú principal");
        System.out.println("3) Salir");

        //Esperar por la seleccion para realizar el proceso indicado.
        ejecutaSeleccionGrafico();
    }

    public void inicializaMenuImpresion() {
        System.out.println("Seleccione una opción: ");
        System.out.println();
        System.out.println("1) Imprimir Ingresos");
        System.out.println("2) Imprimir Gastos");
        System.out.println("3) Imprimir Todos");
        System.out.println("4) Regresar al menú principal");
        System.out.println("5) Salir");

        //Esperar por la seleccion para realizar el proceso indicado.
        ejecutaSeleccionImpresion();
    }

    public void ejecutaSeleccionImpresion() {
        //Comprobar que la seleccion sea un numero entero.
        while (!myScanner.hasNextInt()) {

            //No es numero entero, imprimir error y volverlo a intentar.
            System.out.println("Opción Invalida. Intente de nuevo...");

            //Limpiar Scanner.
            myScanner.next();

            //Llamar al menu principal de Impresion.
            inicializaMenuImpresion();
        }

        //Al contener un numero entero, se continua con el proceso.
        iSeleccion = myScanner.nextInt();

        //Seleccion 1.
        if (iSeleccion == 1) {
            imprimirTransacciones(TipoTransaccion.INGRESO);
        }

        //Seleccion 2.
        else if (iSeleccion == 2) {
            imprimirTransacciones(TipoTransaccion.GASTO);
        }

        //Seleccion 3.
        else if (iSeleccion == 3) {
            imprimirTransacciones(TipoTransaccion.TODAS);
        }

        //Seleccion 4.
        else if (iSeleccion == 4) {
            inicializaMenuImpresion();
        }

        //Seleccion 5.
        else if (iSeleccion == 5) {
            System.exit(0);
        }

        //Ninguna opcion listada en el menu, imprimir error y volver a intentarlo.
        else {
            System.out.println("Opción Invalida. Intente de nuevo...");
            inicializaMenuImpresion();
        }
    }

    public void ejecutaSeleccionGrafico() {
        //Comprobar que la seleccion sea un numero entero.
        while (!myScanner.hasNextInt()) {

            //No es numero entero, imprimir error y volverlo a intentar.
            System.out.println("Opción Invalida. Intente de nuevo...");

            //Limpiar Scanner.
            myScanner.next();

            //Llamar al menu principal de Reporte.
            inicializaMenuGrafico();
        }

        //Al contener un numero entero, se continua con el proceso.
        iSeleccion = myScanner.nextInt();

        //Seleccion 1.
        if (iSeleccion == 1) {
            generarGrafico();
        }

        //Seleccion 2.
        else if (iSeleccion == 2) {
            inicializaMenuPrincipal();
        }

        //Seleccion 3.
        else if (iSeleccion == 3) {
            System.exit(0);
        }

        //Ninguna opcion listada en el menu, imprimir error y volver a intentarlo.
        else {
            System.out.println("Opción Invalida. Intente de nuevo...");
            inicializaMenuGrafico();
        }
    }

    public void generarGrafico() {
        Date dFechaInicialReporte;
        Date dFechaFinalReporte;

        System.out.println("*Generar Gráfico Circular*");
        System.out.println("");

        //Obtener la fecha INICIAL del reporte.
        while (true) {
            System.out.println("1. Ingrese la fecha inicial del gráfico circular (dd/MM/yyyy): ");

            try {
                dFechaInicialReporte = new SimpleDateFormat("dd/MM/yyyy").parse(myScanner.next());
                break;
            } catch (Exception e) {
                System.out.println("La fecha inicial digitada no es válida. Intente de nuevo...");
            }
        }

        //Obtener la fecha FINAL del reporte.
        while (true) {
            System.out.println("2. Ingrese la fecha final del gráfico circular (dd/MM/yyyy): ");

            try {
                dFechaFinalReporte = new SimpleDateFormat("dd/MM/yyyy").parse(myScanner.next());
                break;
            } catch (Exception e) {
                System.out.println("La fecha final digitada no es válida. Intente de nuevo...");
            }
        }

        if (dFechaInicialReporte.after(dFechaFinalReporte)) {
            System.out.println("La fecha inicial es menor que la fecha final. Intente de nuevo...");
            generarGrafico();
        } else {
            //Inicializar la clase para la generación del gráfico circular.
            GeneradorDeGrafico oGeneradorReportes = new GeneradorDeGrafico(dFechaInicialReporte, dFechaFinalReporte);
            oGeneradorReportes.GenerarGraficoCircular();
        }
    }

    private int generarIdUnico() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    private void imprimirTransacciones(TipoTransaccion pTipoTransaccion) {
        List<Transaccion> lstTransacciones = this.oRepository.leerDatos();

        Integer iContador = 0;

        if (!lstTransacciones.isEmpty())
        {
            if (pTipoTransaccion == TipoTransaccion.INGRESO) {
                for (Transaccion oTransaccion : lstTransacciones) {
                    if (oTransaccion.getTipoTransaccion() == TipoTransaccion.INGRESO) {
                        Ingreso oIngreso = (Ingreso) oTransaccion;

                        //Convertir fecha al formato deseado.
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String tmpFecha = simpleDateFormat.format(oIngreso.getFecha());

                        //Aumentar el contador.
                        iContador = iContador + 1;

                        System.out.println(oIngreso.getId() + "," + tmpFecha + "," + oIngreso.getTipoTransaccion() + "," + oIngreso.getMonto() + "," + oIngreso.getDescripcion());
                    }
                }
            }

            if (pTipoTransaccion == TipoTransaccion.GASTO) {
                for (Transaccion oTransaccion : lstTransacciones) {
                    if (oTransaccion.getTipoTransaccion() == TipoTransaccion.GASTO) {
                        Gasto oGasto = (Gasto) oTransaccion;

                        //Convertir fecha al formato deseado.
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String tmpFecha = simpleDateFormat.format(oGasto.getFecha());

                        //Aumentar el contador.
                        iContador = iContador + 1;

                        System.out.println(oGasto.getId() + "," + tmpFecha + "," + oGasto.getTipoTransaccion() + "," + oGasto.getMonto() + "," + oGasto.getDescripcion());
                    }
                }
            }
            if (pTipoTransaccion == TipoTransaccion.TODAS) {
                for (Transaccion oTransaccion : lstTransacciones) {
                    if (oTransaccion.getTipoTransaccion() == TipoTransaccion.INGRESO) {
                        Ingreso oIngreso = (Ingreso) oTransaccion;

                        //Convertir fecha al formato deseado.
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String tmpFecha = simpleDateFormat.format(oIngreso.getFecha());

                        System.out.println(oIngreso.getId() + "," + tmpFecha + "," + oIngreso.getTipoTransaccion() + "," + oIngreso.getMonto() + "," + oIngreso.getDescripcion());
                    } else {
                        Gasto oGasto = (Gasto) oTransaccion;

                        //Convertir fecha al formato deseado.
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String tmpFecha = simpleDateFormat.format(oGasto.getFecha());

                        //Aumentar el contador.
                        iContador = iContador + 1;

                        System.out.println(oGasto.getId() + "," + tmpFecha + "," + oGasto.getTipoTransaccion() + "," + oGasto.getMonto() + "," + oGasto.getDescripcion());
                    }
                }
            }

            if (iContador <= 0)
            {
                System.out.println("No existen transacciones almacenadas actualmente para mostrar...");
            }
        }
        else
        {
            System.out.println("No existen transacciones almacenadas actualmente para mostrar...");
        }

        //Regresar al menu principal.
        inicializaMenuPrincipal();
    }
    //endregion
}
