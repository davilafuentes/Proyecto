package com.ucreativa.presupuesto.entities;

import com.ucreativa.presupuesto.repositories.FileRepository;
import com.ucreativa.presupuesto.services.PresupuestoService;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.Date;
import java.util.List;

public class GeneradorDeGrafico {

    Date dFechaInicial;
    Date dFechaFinal;

    //Declaracion del Repositorio.
    private FileRepository oRepository = new FileRepository();

    public GeneradorDeGrafico(Date pFechaInicial, Date pFechaFinal)
    {
        //Asignar las variables de la clase.
        this.dFechaInicial = pFechaInicial;
        this.dFechaFinal = pFechaFinal;
    }

    public void GenerarGraficoCircular()
    {

        //Leer los datos de la BD.
        List<Transaccion> lstTransacciones = oRepository.leerDatos();

        //Crear el objeto tipo reporte.
        PieChart chart = new PieChartBuilder().width(400).height(450).title(getClass().getSimpleName()).build();

        //Asignar propiedades.
        chart.getStyler().setCircular(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideS);
        chart.getStyler().setLegendLayout(Styler.LegendLayout.Horizontal);

        //Variables para revisar si existen datos que mostrar en el rango de fechas.
        Double dMontoTotalIngreso = sumarTotalMontos(lstTransacciones, TipoTransaccion.INGRESO);
        Double dMontoTotalGasto = sumarTotalMontos(lstTransacciones, TipoTransaccion.GASTO);

        if (dMontoTotalIngreso > 0 || dMontoTotalGasto > 0)
        {
            System.out.println("Generando Reporte...");

            //Sumar los montos y mostrar la información obtenida de la BD.
            chart.addSeries("Ingresos", dMontoTotalIngreso);
            chart.addSeries("Gastos", dMontoTotalGasto);

            //Mostrar el reporte en pantalla.
            new SwingWrapper<>(chart).displayChart();
        }
        else
        {
            System.out.println("No existen transacciones almacenadas en la base de datos. No es posible generar el gráfico circular.");

            //Regresar al menú principal.
            PresupuestoService oPresupuestoService = new PresupuestoService(new FileRepository());
            oPresupuestoService.inicializaMenuPrincipal();
        }
    }

    public Double sumarTotalMontos(List<Transaccion> pTransacciones, TipoTransaccion pTipoTransaccion)
    {
        Double dTotal= 0.0;

        if (pTipoTransaccion == TipoTransaccion.INGRESO)
        {
            for (Transaccion oTransaccion : pTransacciones)
            {
                if (oTransaccion.getTipoTransaccion() == TipoTransaccion.INGRESO)
                {
                    Ingreso oIngreso = (Ingreso) oTransaccion;

                    //Si la fecha del ingreso es mayor que la fecha inicial configurada y la fecha del ingreso
                    //es menor que la fecha final configurada, agregar el monto a la variable total.
                    if (oIngreso.getFecha().after(this.dFechaInicial) && oIngreso.getFecha().before(this.dFechaFinal))
                    {
                        dTotal = dTotal + oIngreso.getMonto();
                    }
                }
            }
        }
        else
        {
            for (Transaccion oTransaccion : pTransacciones)
            {
                if (oTransaccion.getTipoTransaccion() == TipoTransaccion.GASTO)
                {
                    Gasto oGasto = (Gasto) oTransaccion;

                    //Si la fecha del ingreso es mayor que la fecha inicial configurada y la fecha del ingreso
                    //es menor que la fecha final configurada, agregar el monto a la variable total.
                    if (oGasto.getFecha().after(this.dFechaInicial) && oGasto.getFecha().before(this.dFechaFinal))
                    {
                        dTotal = dTotal + oGasto.getMonto();
                    }
                }
            }
        }

        return dTotal;
    }
}
