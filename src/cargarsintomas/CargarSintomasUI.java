package cargarsintomas;

import cargarregistros.CargarRegistrosUI;
import cargarsintomas.ManejoArchivosS;
import monitor.CargarInfo;
import monitor.Sintoma;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Stack;

public class CargarSintomasUI extends JFrame{


    String[] peligro = new String[10];
    static DefaultListModel listaM = new DefaultListModel();
    static JPanel panelj;
    static JLabel titulo;
    static JLabel fase;
    static JTextField nombre;
    static JComboBox sintomas;
    static JButton agregar;
    static JButton prueba;
    static JList listaSintomas;
    String nombre1 = "toz";
    String tipo1 = "PrimeraFase";
    String direccion = "";
    ArrayList<String> nombreA = new ArrayList<String>();
    ArrayList<String> tipoA = new ArrayList<String>();
    int apagado =0;

   // Monitor monitor = new Monitor();
    ManejoArchivosS archivos = new ManejoArchivosS();
  //  CargarSintomasAyuda cargar =  new CargarSintomasAyuda();








    public CargarSintomasUI() throws IOException {

        //Agrega los sintomas de sintomas critico medio cuantitativo etc..
      //  agregarSintomasFlexible();

        direccion = archivos.getPath();
        archivos.revizarExistenciaArchivo();
        cargarLosSintomasTexto();
        agragarFinal();


        //Envia el cargarSintomas al Cargar Registro para usar el mismo get (PREGUNTAR)


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

        fase = new JLabel("Primera fase");
        fase.setBounds(260, 30, 230, 80);
        fase.setFont(new Font("Arial", Font.BOLD, 15));

        nombre = new JTextField();
        nombre.setBounds(20, 200, 120, 30);

        sintomas = new JComboBox(peligro);
        sintomas.setBounds(20, 90, 100, 20);


        agregar = new JButton("AGREGAR");
        agregar.setBounds(20, 280, 90, 30);

        prueba = new JButton("FINALIZAR");
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
        panel.add(fase);
        panel.add(titulo);
        panel.add(panelj);


        //**Mostrar elementos**
        panel.setLayout(null);
        panel.setVisible(true);






     //**Carga los sintomas en jList lista sintomas


       /* if (archivos.pasaron3Dias()){
            fase.setText("Segunda Fase");
            archivos.cargarSintomas2da(listaM);
        }
        else {
            archivos.cargarSintomas(listaM);
        }
        */
        archivos.cargarSintomas(listaM);




        //**Acciones de los botones**

        sintomas.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (sintomas.getSelectedItem().equals("Contables")) {
                }
            }
        });

        prueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   CargarRegistrosUI Registros = new CargarRegistrosUI();
            // Registros.setCargarSintomas(cargar);
                panel.setVisible(false);
                seguir();

            }
        });

        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (archivos.sintomasRepetidos(nombre.getText())) {
                        if (!nombre.getText().equals("")) {

                            listaM.addElement(nombre.getText());
                            try {
                                nombreA.add(nombre.getText());
                                tipoA.add(sintomas.getSelectedItem().toString());

                                //nombre1 = nombre.getText();
                                //tipo1 = sintomas.getSelectedItem().toString();
                              //  cargar.cargarSintomaX(nombre.getText(),sintomas.getSelectedItem().toString());
                                agragarFinal();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                             //CargarInfo.cargarNuevoMedio(nombre.getText());
                             //   archivos.guardarArchivo(nombre.getText());;
                            archivos.guardarArchivo1(nombre.getText(),sintomas.getSelectedItem().toString());
                        }
                }
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



    public void agregarSintomasFlexible(){

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

    public void agragarFinal() throws IOException{
        Stack<String> stack = new Stack<>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("sintomas");
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            File dir = new File(url.getFile());
            int cont = 0;
            for( File f : dir.listFiles()){
                String nombreClases = f.getName().split("\\.")[0];
                try{
                    Class.forName("sintomas." + nombreClases).asSubclass(Sintoma.class);
                    String x = (Class.forName("sintomas." + nombreClases).asSubclass(Sintoma.class)).toString().replace("sintomas.", "");
                    peligro[cont] = x.replace("class ", "");
                    cont++;
                    stack.push(nombreClases);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }


    }

    public void cargarLosSintomasTexto(){

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
               // cargar.cargarSintomaX(nombre,tipo);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> sacarNombre(){

        return nombreA;

        //crear un array de string con su respectivo tipo para pasarlo a sintomas
    }

    public ArrayList<String> sacarTipo(){
        return  tipoA;
    }




}
