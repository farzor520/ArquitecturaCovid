package cargarregistros;

import manejoarchivos.ManejoArchivos;
import monitor.Monitor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CargarRegistrosUI extends JFrame{

    static DefaultListModel listaM = new DefaultListModel();
    static DefaultListModel listaF = new DefaultListModel();
    static JPanel panelj;
    static JLabel titulo;
    static JButton agregar;
    static JButton actulizar;
    static JList listaSintomas;
    static JList listaSintomasFinal;

    ManejoArchivos archivos = new ManejoArchivos();

    public CargarRegistrosUI() {


    //  agregarSintomasFlexible();

        //**Crear el panel pricipal**
        JFrame panel = new JFrame();//instancia de jframe
        panel.setSize(650, 440);
        panel.setTitle("Registros");


        //**Declaracion de elementos necesarios**
        panelj = new JPanel();
        Color colorCafe = new Color(4, 120, 239);
        panelj.setSize(650, 440);
        panelj.setBackground(colorCafe);


        titulo = new JLabel("REGISTRO COVID");
        titulo.setBounds(220, 10, 230, 80);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        listaSintomasFinal = new JList(listaF);
        listaSintomasFinal.setBounds(350, 100, 250, 250);
        //listaSintomasFinal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        agregar = new JButton(">");
        agregar.setBounds(290, 180, 50, 30);

        actulizar = new JButton("Actualizar");
        actulizar.setBounds(10, 10, 110, 30);

        listaSintomas = new JList(listaM);
        listaSintomas.setBounds(30, 100, 250, 250);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        //**Agregar elementos al panel**

        panel.add(actulizar);
        panel.add(listaSintomas);
        panel.add(agregar);
        panel.add(listaSintomasFinal);
        panel.add(titulo);
        panel.add(panelj);


        //**Mostrar elementos**
        panel.setLayout(null);//using no layout managers
        panel.setVisible(true);//making the frame visible

        //**Carga los sintomas en jList lista sintomas
        archivos.cargarSintomas(listaM);

        //**Acciones de los botones**





        listaSintomas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //listaSintomas.getSelectedValuesList();
                String remplazo = listaSintomas.getSelectedValuesList().toString().replace("[", "").replace("]","");
                listaF.addElement(remplazo);
                listaM.removeElement(remplazo);
            }
            
        });

        actulizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaM.removeAllElements();
                listaF.removeAllElements();
                archivos.cargarSintomas(listaM);
            }
        });

    }
}
