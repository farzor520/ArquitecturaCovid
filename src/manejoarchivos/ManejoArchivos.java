package manejoarchivos;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ManejoArchivos {

    public void guardarArchivo(String sintoma){
        try {
            FileWriter myWriter = new FileWriter(".\\sintomas.txt",true);
            myWriter.write("" + sintoma + "\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarSintomas(DefaultListModel lista) {

        try {
            FileInputStream fis = new FileInputStream(".\\sintomas.txt");
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
            FileInputStream fis = new FileInputStream(".\\sintomas.txt");
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

    public void agregarSintomasRefelct() throws ClassNotFoundException {
        Class<?> classlldr = Class.forName("sintomas");
        System.out.println("Name of Class  = " + classlldr.getName());
    }

}
