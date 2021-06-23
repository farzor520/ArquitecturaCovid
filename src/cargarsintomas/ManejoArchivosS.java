package cargarsintomas;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManejoArchivosS {

    private final String nombrePaquete = "cargarsintomas";

    public void guardarArchivo(String sintoma){
        try {

            FileWriter myWriter = new FileWriter(getPath());
            myWriter.write("" + sintoma + "\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarArchivo2dafase(String sintoma){
        try {
            FileWriter myWriter = new FileWriter(".\\sintomas2da.txt",true);
            myWriter.write("" + sintoma + "\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarArchivo1(String sintoma,String tipo){


      try {
            FileWriter myWriter = new FileWriter(getPath());
            myWriter.write("" + sintoma + "-" + tipo +"\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



     /*       File archivo = new File(getPath());

            if(archivo.length()==0){
                escribirSintomaCabecera(sintoma);
            }else{
                escribirSintoma(sintoma);
            }

      */
    }

    public void revizarExistenciaArchivo() {

            File file = new File(getPath());

            //Create the file
            try {
                if (file.createNewFile()) {

                } else {

                }
                //Write Content
               // FileWriter writer = new FileWriter(file);

              //  writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void escribirSintoma (String  s)
    {

        try
        {
            MiObjectOutputStream oos = new MiObjectOutputStream(
                    new FileOutputStream(getPath()));
            oos.writeObject(s);
            oos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void escribirSintomaCabecera(String s){

        try (ObjectOutputStream objectOutput = new ObjectOutputStream(
                new FileOutputStream(getPath(), true))) {
            objectOutput.writeObject(s);
            objectOutput.reset();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cargarSintomas(DefaultListModel lista) {

        try {

            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                lista.addElement(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarSintomas2da(DefaultListModel lista) {

        try {
            FileInputStream fis = new FileInputStream(".\\sintomas2da.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                lista.addElement(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void guardarRegistro(String registro){

        try {
            FileWriter myWriter = new FileWriter(".\\registros.txt",true);
            myWriter.write("" + registro );
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarSintomasRefelct() throws ClassNotFoundException {
        Class<?> classlldr = Class.forName("sintomas");
        System.out.println("Name of Class  = " + classlldr.getName());
    }

    public boolean pasaron3Dias(){
        if (unDia()+3 <= diaFinal()){
            return true;
        }
        return false;
    }

    public int unDia(){

        int i = 0;
        try {
            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            String res = sc.nextLine();
            if (res.contains("BOT")){
                Matcher matcher = Pattern.compile("\\d+").matcher(res);
                matcher.find();
                i = Integer.valueOf(matcher.group());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int diaFinal(){
        int i=0;
        try {
            FileInputStream fis = new FileInputStream(getPath());
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String res = sc.nextLine();
                if (res.contains("BOT")){
                    Matcher matcher = Pattern.compile("\\d+").matcher(res);
                    matcher.find();
                    i = Integer.valueOf(matcher.group());
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public String getPath1(){
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
            path = dir+separador+nombrePaquete+separador;
        } else {
            path = dir+separador+"src"+separador+nombrePaquete+separador+"sintomas.txt";
        }
       // System.out.println(path);
        return path;
    }

    public String getPath(){
        String path1 = "cargarsintomas/sintomas.txt";
        String path2 = "src/cargarsintomas/sintomas.txt";
        String res="";
        if(new File (path2).exists()){
            res += path2;
        }else{
            res+= path1;
        }
        System.out.println(res);
        return res;
    }

}
