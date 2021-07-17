package cargarregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.PrimeraFase;
import sintomas.SegundaFase;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ManejoArchivosR{


    private final String nombrePaquete = "cargarregistros";
    private Sintomas todosS = new Sintomas();
    private PrimeraFase primera;
    private SegundaFase segunda;
    private Date dia;




    public void registroTexto(DefaultListModel<String> lista, Registros registros) {

        int primeraFecha = 0;

        try {
            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String dato = "";
                dato = sc.nextLine();
                lista.addElement(dato);
                if (esFecha(dato)){
                    if (primeraFecha > 0){
                        Registro reg = new Registro(dia,todosS);
                            registros.push(reg);
                        todosS = new Sintomas();
                    }
                    dia = new SimpleDateFormat("yyyy-MM-dd").parse(dato);
                    primeraFecha++;
                }else {
                    if (dato.contains("10")){
                        primera = new PrimeraFase(dato);
                        todosS.add(primera);
                    }else if (dato.contains("100")){
                        segunda = new SegundaFase(dato);
                        todosS.add(segunda);
                    }
                }
            }
            Registro reg = new Registro(dia,todosS);
            registros.push(reg);
            sc.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean esFecha(String nextLine){

        if (nextLine.contains("-")){
            return true;
        }
        return false;
    }

    public void guardarRegistro(String registro){

        try {
            FileWriter myWriter = new FileWriter(getPath(),true);
            myWriter.write("" + registro );
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath(){
        File miDir = new File(".");
        String dir = "", path="", separador = System.getProperty("file.separator");
        try{
            dir = miDir.getCanonicalPath();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();
        for(int i=0;i<a.length;i++){
            if (a[i].equals("src")){
                esDesarrollo=true;
            }
        }

        if (!esDesarrollo){
            path = "FavioGuerraRegistros.txt";
        } else {
            path = dir+separador+"src"+separador+nombrePaquete+separador+"registros.txt";
        }
        return path;
    }

    public void revizarExistenciaArchivo() {

        File file = new File(getPath());
        try {
            if (file.createNewFile()) {

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int manejoDeDias(DefaultListModel<String> lista)   {

        String str = "";
        int numberAnterior = -1;
        int numberActual = -1;
        int dias = -1;
        int mitad = 10;
        try {
            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                str = str.replaceAll("[^\\d]", " ");
                str = str.trim();
                str = str.replaceAll(" +", " ");
                if (str.equals("")){
                    str = "-1";
                }
                if (str.length()<2){
                    mitad++;
                }
                if (str.length()>=2){
                    if (mitad >= lista.getSize()/4) {
                        mitad = 0;
                        str = str.substring(0, 2);
                        try {
                            numberActual = Integer.parseInt(str);
                            if (numberAnterior == -1) {
                                numberAnterior = numberActual;
                                dias = 1;
                            } else if (numberAnterior + 1 == numberActual) {
                                numberAnterior = numberActual;
                                dias++;
                            } else if (numberAnterior + 1 != numberActual) {
                                numberAnterior = numberActual;
                                dias = 1;
                            }
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dias;
    }


}
