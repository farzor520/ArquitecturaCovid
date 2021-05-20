package cargarsintomas;

import manejoarchivos.ManejoArchivos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CargarSintomasUI extends JFrame  {


    // String peligro[] = {"Bajo", "Medio", "Critico", "Contables"};
    String[] peligro = new String[10];
    static DefaultListModel listaM = new DefaultListModel();
    static JPanel panelj;
    static JLabel titulo;
    static JTextField nombre, peso;
    static JComboBox sintomas;
    static JButton agregar;
    static JButton prueba;
    static JList listaSintomas;

   // Monitor monitor = new Monitor();
    ManejoArchivos archivos = new ManejoArchivos();
    CargarSintomas cargar =  new CargarSintomas();


    public CargarSintomasUI() {


        agregarSintomasFlexible();

        //**Crear el panel pricipal**
        JFrame panel = new JFrame();//instancia de jframe
        panel.setSize(650, 440);
        panel.setTitle("Sintomas");


        //**Declaracion de elementos necesarios**
        panelj = new JPanel();
        Color colorCafe = new Color(4, 120, 239);
        panelj.setSize(650, 440);
        panelj.setBackground(colorCafe);


        titulo = new JLabel("MONITOREO COVID");
        titulo.setBounds(220, 10, 230, 80);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        nombre = new JTextField();
        nombre.setBounds(20, 200, 120, 30);


        peso = new JTextField(77);
        peso.setBounds(150, 200, 120, 30);
        peso.setVisible(false);

        sintomas = new JComboBox(peligro);
        sintomas.setBounds(20, 90, 100, 20);


        agregar = new JButton("AGREGAR");
        agregar.setBounds(20, 280, 90, 30);

        prueba = new JButton("Probar");
        prueba.setBounds(200, 280, 90, 30);

        listaSintomas = new JList(listaM);
        listaSintomas.setBounds(350, 100, 250, 250);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //**Agregar elementos al panel**
        panel.add(prueba);
        panel.add(listaSintomas);
        panel.add(agregar);
        panel.add(sintomas);
        panel.add(nombre);
        panel.add(peso);
        panel.add(titulo);
        panel.add(panelj);


        //**Mostrar elementos**
        panel.setLayout(null);//using no layout managers
        panel.setVisible(true);//making the frame visible

        //**Carga los sintomas en jList lista sintomas
        archivos.cargarSintomas(listaM);
        //**Acciones de los botones**

        sintomas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (sintomas.getSelectedItem().equals("Contables")) {
                    peso.setVisible(true);
                } else
                    peso.setVisible(false);
            }
        });

        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (archivos.sintomasRepetidos(nombre.getText())) {
                    if (!sintomas.getSelectedItem().equals(peligro[1])) {
                        if (!nombre.getText().equals("")) {

                            listaM.addElement(nombre.getText());
                            try {
                                cargar.cargarSintomaX(nombre.getText(),sintomas.getSelectedItem().toString());
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            //  CargarInfo.cargarNuevoMedio(nombre.getText());
                            archivos.guardarArchivo(nombre.getText());
                        }
                    }

                    if (sintomas.getSelectedItem().equals(peligro[1])) {
                        if (!nombre.getText().equals("") && !peso.getText().equals("")) {

                            listaM.addElement(nombre.getText());
                            try {
                                cargar.cargarSintomaX(nombre.getText(),sintomas.getSelectedItem().toString());
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            //  CargarInfo.cargarNuevoContable(nombre.getText(), peso.getText());
                            archivos.guardarArchivo(nombre.getText());
                        }
                    }
                }
            }

        });

        prueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });


        listaSintomas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                peso.setVisible(false);

            }
        });

        peso.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = peso.getText();
                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    peso.setEditable(true);
                    //
                } else {
                    peso.setEditable(false);
                    // peso.setText("");
                }
            }
        });
    }



    public void agregarSintomasFlexible() {
        File directoryPath = new File("out\\production\\MonitorCovid\\sintomas");
        File filesList[] = directoryPath.listFiles();
        int cont = 0;
        for (File file : filesList) {
            String nombre = file.getName();
            String nombreSintoma = nombre.replace(".class", "");
            peligro[cont] = nombreSintoma;
            cont++;
        }
    }



}
