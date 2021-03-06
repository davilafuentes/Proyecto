package com.ucreativa.presupuesto.repositories;

import com.ucreativa.presupuesto.entities.*;
import com.ucreativa.presupuesto.services.PresupuestoService;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FileRepository implements Repository{

    //Declarar las variables para almacenar la ruta del archivo.
    private final String sKey = "abc123ghidef9123";
    private final Path root = Paths.get(".").normalize().toAbsolutePath();
    private final String FILE_PATH = root + "\\presupuestoDB.txt";
    private Ingreso oIngreso;
    private Gasto oGasto;

    @Override
    public void guardarTransaccion(Transaccion pTransaccion)
    {
        try {
            //Declarar el contenido a guardar.
            String txtContent = "";

            //Convertir fecha al formato deseado.
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String tmpFecha = simpleDateFormat.format(pTransaccion.getFecha());

            //Convertir la Transaccion a su clase correspondiente.
            if (pTransaccion.getTipoTransaccion().equals(TipoTransaccion.INGRESO))
            {
                oIngreso = (Ingreso) pTransaccion;

                txtContent = pTransaccion.getId() + "," +
                             tmpFecha + "," +
                             TipoTransaccion.INGRESO.toString() + "," +
                             oIngreso.getTipoIngreso() + "," +
                             oIngreso.getMonto() + "," +
                             oIngreso.getDescripcion();

                //Encriptar el string para que el mismo no pueda ser modificado desde el TXT
                // y así no generar excepciones de parseo al leer el archivo.
                txtContent = Encryptor.encriptar(sKey, "RandomInitVector", txtContent);
            }
            else
            {
                oGasto = (Gasto) pTransaccion;

                txtContent = pTransaccion.getId() + "," +
                             tmpFecha + "," +
                             TipoTransaccion.GASTO.toString() + "," +
                             oGasto.getTipoGasto() + "," +
                             oGasto.getMonto() + "," +
                             oGasto.getDescripcion();

                //Encriptar el string para que el mismo no pueda ser modificado desde el TXT
                // y así no generar excepciones de parseo al leer el archivo.
                txtContent = Encryptor.encriptar(sKey, "RandomInitVector", txtContent);
            }

            //Declarar el lugar donde se guardará. Se accede al root de la solución
            //ya que de otra forma podría indicar error de acceso.
            Path root = Paths.get(".").normalize().toAbsolutePath();

            //Instanciar el objeto para el archivo.
            File myFile = new File(FILE_PATH);

            //Si el archivo no existe, crearlo automáticamente.
            if (!myFile.exists()) {
                myFile.createNewFile();
            }

            //Crear el Writter.
            FileWriter fw = new FileWriter(myFile.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            //Imprimir, pero además agregar el carácter para crear una nueva línea.
            bw.write(txtContent + "\n");

            //Cerrar la conexión.
            bw.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al tratar de guardar el archivo de la base de datos: " + e.getMessage());
        }
        finally
        {
            //Regresar al menu principal de la aplicacion.
            PresupuestoService oPresupuestoService = new PresupuestoService(new FileRepository());
            oPresupuestoService.inicializaMenuPrincipal();
        }
    }

    @Override
    public List<Transaccion> leerDatos() {

        Boolean lbOk = true;
        String strError = "";

        //Declaración de la lista que va a contener todas las transacciones del archivo.
        List<Transaccion> lstTransacciones = new ArrayList<Transaccion>();

        //Leer la BD.
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String sLine = "";

            while ((sLine = br.readLine()) != null) {

                //Desencriptar el string almacenado en el TXT para su lectura interna.
                sLine = Encryptor.desencriptar(sKey, "RandomInitVector", sLine);

                List<String> aLine = new ArrayList<String>(Arrays.asList(sLine.split(",")));

                if (lbOk)
                {
                    if (aLine.get(2).equals(TipoTransaccion.INGRESO.toString())) {
                        try {
                            Integer tmpId = Integer.parseInt(aLine.get(0));
                            Date tmpFecha = new SimpleDateFormat("dd/MM/yyyy").parse(aLine.get(1));
                            TipoTransaccion tmpTipoTransaccion = aLine.get(2).equals(TipoTransaccion.INGRESO.toString()) ? TipoTransaccion.INGRESO : TipoTransaccion.GASTO;
                            TipoIngreso tmpTipoIngreso = aLine.get(3).equals(TipoIngreso.SALARIO.toString()) ? TipoIngreso.SALARIO : TipoIngreso.EXTRA;
                            Double tmpMonto = Double.parseDouble(aLine.get(4));
                            String tmpDescripcion = aLine.get(5);

                            //Crear el objeto tipo Ingreso.
                            Transaccion oTransaccion = new Ingreso(tmpId, tmpFecha, tmpTipoTransaccion, tmpTipoIngreso, tmpMonto, tmpDescripcion);

                            //Guardar el Ingreso a la lista.
                            lstTransacciones.add(oTransaccion);

                        } catch (ParseException e) {
                            lbOk = false;
                            strError = "Ocurrió un error al tratar de realizar el parseo del ingreso: " + e.getMessage();
                        }
                    } else {
                        try {
                            Integer tmpId = Integer.parseInt(aLine.get(0));
                            Date tmpFecha = new SimpleDateFormat("dd/MM/yyyy").parse(aLine.get(1));
                            TipoTransaccion tmpTipoTransaccion = aLine.get(2).equals(TipoTransaccion.INGRESO.toString()) ? TipoTransaccion.INGRESO : TipoTransaccion.GASTO;

                            //Inicializar el TipoGasto, después lo cambiamos al leer desde la BD.
                            TipoGasto tmpTipoGasto = TipoGasto.PERSONAL;

                            if (aLine.get(3).equals(TipoGasto.PERSONAL.toString())) {
                                tmpTipoGasto = TipoGasto.PERSONAL;
                            } else if (aLine.get(3).equals(TipoGasto.PRESTAMO.toString())) {
                                tmpTipoGasto = TipoGasto.PRESTAMO;
                            } else if (aLine.get(3).equals(TipoGasto.SERVICIO_PUBLICO.toString())) {
                                tmpTipoGasto = TipoGasto.SERVICIO_PUBLICO;
                            } else if (aLine.get(3).equals(TipoGasto.EDUCACION.toString())) {
                                tmpTipoGasto = TipoGasto.EDUCACION;
                            }

                            Double tmpMonto = Double.parseDouble(aLine.get(4));
                            String tmpDescripcion = aLine.get(5);

                            //Crear el objeto tipo Gasto.
                            Transaccion oTransaccion = new Gasto(tmpId, tmpFecha, tmpTipoTransaccion, tmpTipoGasto, tmpMonto, tmpDescripcion);

                            //Guardar el Ingreso a la lista.
                            lstTransacciones.add(oTransaccion);

                        } catch (ParseException e) {
                            lbOk = false;
                            strError = "Ocurrió un error al tratar de realizar el parseo del gasto: " + e.getMessage();
                        }
                    }
                }
                else
                {
                    System.out.println(strError);

                    //Regresar al menu principal de la aplicacion.
                    PresupuestoService oPresupuestoService = new PresupuestoService(new FileRepository());
                    oPresupuestoService.inicializaMenuPrincipal();
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al tratar de leer el archivo de la base de datos: " + e.getMessage());
        }

        return lstTransacciones;
    }
}
