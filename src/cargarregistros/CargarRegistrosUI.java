package cargarregistros;

import cargarsintomas.CargarSintomasUI;
import cargarsintomas.CargarSintomas;
import monitor.Monitor;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CargarRegistrosUI extends JFrame{

    static DefaultListModel listaM = new DefaultListModel();
    static DefaultListModel listaF = new DefaultListModel();
    static JPanel panelj;
    static JLabel titulo;
    static JLabel fase;
    static JButton agregar;
    static JButton actulizar;
    static JButton cargarAlRegistro;
    static JLabel fecha;
    static JList listaSintomas;
    static JList listaSintomasFinal;

    ManejoArchivosR archivos = new ManejoArchivosR();
    CargarSintomas cargar;
    Date data = new Date();



    public CargarRegistrosUI(Sintomas sintomas) {


        archivos.revizarExistenciaArchivo();
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

        fase = new JLabel("Primera fase");
        fase.setBounds(260, 30, 230, 80);
        fase.setFont(new Font("Arial", Font.BOLD, 15));

        listaSintomasFinal = new JList(listaF);
        listaSintomasFinal.setBounds(350, 100, 250, 250);
        //listaSintomasFinal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        agregar = new JButton(">");
        agregar.setBounds(290, 180, 50, 30);

        actulizar = new JButton("Actualizar");
        actulizar.setBounds(10, 10, 110, 30);


        cargarAlRegistro = new JButton("Cargar");
        cargarAlRegistro.setBounds(400, 360, 110, 30);


        listaSintomas = new JList(listaM);
        listaSintomas.setBounds(30, 100, 250, 250);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        String x =data.toString();
        fecha = new JLabel("FECHA DE EVALUCION: " + x);
        fecha.setBounds(30, 240, 320, 250);



        //**Agregar elementos al panel**
        panel.add(fecha);
       // panel.add(actulizar);
        panel.add(listaSintomas);
        panel.add(agregar);
        panel.add(cargarAlRegistro);
        panel.add(listaSintomasFinal);
        panel.add(fase);
        panel.add(titulo);
        panel.add(panelj);


        //**Mostrar elementos**
        panel.setLayout(null);//using no layout managers
        panel.setVisible(true);//making the frame visible

        //**Carga los sintomas en jList lista sintomas

        /////IMPORTANTE Al cargar sintomas se carga dependiendo del texto de Sintomas y no del getsintomas() de CargarSintomas como deberia ser
       // archivos.cargarSintomas(listaM);
        sintomas.forEach(sintoma -> listaM.addElement(sintoma.toString()));



        // listaM.addElement(cargar.getSintomas());


     //   if (archivos.pasaron3Dias()){
     //       fase.setText("Segunda Fase");
    //        archivos.cargarSintomas2da(listaM);
    //    }
    //    else {
     //       archivos.cargarSintomas(listaM);
     //   }

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
        
        cargarAlRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Date date = new Date();
                //carga los sintomas de cargarUI
               archivos.guardarRegistro("" + date + "\n");
            //    cargar.getSintomas().forEach(sintoma -> {
                sintomas.forEach(sintoma -> {
                    if (listaF.contains(sintoma.toString())){
                        //sintomas.add(sintoma);
                        archivos.guardarRegistro(sintoma.toString()+ ": ");
                        archivos.guardarRegistro(sintoma.peso()+"\n");
                    }
                });

                //Manejo de  archivos
                panel.setVisible(false);
                seguir();



            }
        });

        actulizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // listaM.removeAllElements();
               // listaF.removeAllElements();
               // archivos.cargarSintomas(listaM);
            }
        });
        detener();

    }

    private synchronized void detener(){
        try {

            this.wait();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private synchronized void seguir(){
        try{
            this.notify();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
