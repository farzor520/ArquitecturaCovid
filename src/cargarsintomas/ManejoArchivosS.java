package cargarsintomas;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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

        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listS = new ArrayList<String>();
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

        for (int x = 0;x < list.size(); x++){
            lista.addElement(list.get(x));
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
        boolean esDesarrollo = false;
        File file2 = new File(dir);
        String[] a = file2.list();
        for(int i=0;i<a.length;i++){
            if (a[i].equals("src")){
                esDesarrollo=true;
            }
        }

        if (!esDesarrollo){
            path = "FavioGuerraSintomas.txt";
        } else {
            path = dir+separador+"src"+separador+nombrePaquete+separador+"sintomas.txt";
        }
        return path;
    }

}
