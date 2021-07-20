package cargarsintomas;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ManejoArchivosS {

    private final String nombrePaquete = "cargarsintomas";

    public void guardarArchivo1(String sintoma,String tipo){

      try {
            FileWriter myWriter = new FileWriter(getPath(),true);
            myWriter.write("" + sintoma + "-" + tipo +"\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void agregarSintomas(String[] peligro) throws IOException {
        AgregarSintomasFlexible flex = new AgregarSintomasFlexible();
        if(esDesarrollo() || esClass()){
            flex.agregarFinal(peligro);
        } else flex.jar(peligro);
    }

    private boolean esClass() {
        File miDir = new File(".");
        String dir = "", path="", separador = System.getProperty("file.separator");
        try{
            dir = miDir.getCanonicalPath();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        boolean clase = false;
        File file2 = new File(dir);
        String[] a = file2.list();
        for(int i = 0; i< Objects.requireNonNull(a).length; i++){
            if (a[i].equals("Main.class")) {
                clase = true;
                break;
            }
        }

        return clase;
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


    public void cargarSintomas(DefaultListModel<String> lista,DefaultListModel<String> listaS) {

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> listS = new ArrayList<>();
        try {

            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains("PrimeraFase")){
                    list.add(line);
                }else {
                    listS.add(line);
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
        Collections.sort(listS);
        Ordenar(list,lista);
        Ordenar(listS,listaS);
    }

    private void Ordenar(ArrayList<String> list, DefaultListModel<String> lista) {

        for (String s : list) {
            lista.addElement(s);
        }

    }

    public boolean sintomasRepetidos(String nombre) {

        try {
            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                if (nombre.equals(sc.nextLine())) {
                    return false;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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
        if (esClass()){
            path = "cargarSintomas/Sintomas.txt";
        } else { if (esDesarrollo()){
            path = dir+separador+"src"+separador+nombrePaquete+separador+"sintomas.txt";
        } else {
            path = "FavioGuerraSintomas.txt"  ;
        }
        }
        return path;
    }

    public boolean esDesarrollo(){
        File miDir = new File(".");
        String dir = "", path="", separador = System.getProperty("file.separator");
        try{
            dir = miDir.getCanonicalPath();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        boolean desarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();
        for(int i = 0; i< Objects.requireNonNull(a).length; i++){
            if (a[i].equals("src")) {
                desarrollo = true;
                break;
            }
        }

        return desarrollo;
    }

    public void cargarLosSintomasTexto(String direccion, List<String> nombreA, List<String> tipoA){

        String nombre;
        String tipo;

        try {
            FileInputStream fis = new FileInputStream(direccion);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split("-");
                nombre = parts[0];
                tipo = parts[1];
                nombreA.add(nombre);
                tipoA.add(tipo);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
