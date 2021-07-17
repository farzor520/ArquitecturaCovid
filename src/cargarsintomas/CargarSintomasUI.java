package cargarsintomas;

import monitor.Sintoma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CargarSintomasUI extends JFrame{


    private String[] peligro = new String[2];
    private static DefaultListModel<String> listaM = new DefaultListModel<String>();
    private static DefaultListModel<String> listaS = new DefaultListModel<String>();
    private static JPanel panelj;
    private static JLabel titulo;
    private static JLabel tipoSintomas;
    private static JLabel sintomasAgregados;
    private static JLabel agregarSintomaTexto;
    private static JLabel fase;
    private static JTextField nombre;
    private static JComboBox sintomas;
    private static JButton agregar;
    private static JButton prueba;
    private static JList listaSintomas;
    private  static JList listaSintomasF;


    private String direccion = "";
    private ArrayList<String> nombreA = new ArrayList<String>();
    private ArrayList<String> tipoA = new ArrayList<String>();
    private ManejoArchivosS archivos = new ManejoArchivosS();


    public CargarSintomasUI() throws IOException {


        direccion = archivos.getPath();
        archivos.revizarExistenciaArchivo();
        cargarLosSintomasTexto();

        agregarSintomas();

        JFrame panel = new JFrame();
        panel.setSize(650, 440);
        panel.setTitle("Sintomas");

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

        tipoSintomas = new JLabel("Tipo de sintomas");
        tipoSintomas.setBounds(20, 60, 230, 80);
        tipoSintomas.setFont(new Font("Arial", Font.BOLD, 14));

        agregarSintomaTexto = new JLabel("Sintoma a agregar");
        agregarSintomaTexto.setBounds(20, 148, 230, 80);
        agregarSintomaTexto.setFont(new Font("Arial", Font.BOLD, 14));

        sintomasAgregados = new JLabel("Sintomas Agregados");
        sintomasAgregados.setBounds(400, 50, 230, 80);
        sintomasAgregados.setFont(new Font("Arial", Font.BOLD, 15));

        nombre = new JTextField();
        nombre.setBounds(20, 200, 120, 30);


        sintomas = new JComboBox<String>(peligro);
        sintomas.setBounds(20, 110, 130, 20);


        agregar = new JButton("AGREGAR");
        agregar.setBounds(20, 280, 90, 30);

        prueba = new JButton("FINALIZAR");
        prueba.setBounds(200, 280, 110, 30);


        listaSintomas = new JList<String>(listaM);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaSintomasF = new JList<String>(listaS);
        listaSintomasF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        JScrollPane scrollPane = new JScrollPane(listaSintomas);
        scrollPane.setBounds(350, 100, 250, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane1 = new JScrollPane(listaSintomasF);
        scrollPane1.setBounds(350, 100, 250, 250);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setVisible(false);


        panel.add(scrollPane);
        panel.add(scrollPane1);
        panel.add(sintomasAgregados);
        panel.add(tipoSintomas);
        panel.add(agregarSintomaTexto);
        panel.add(prueba);
        panel.add(agregar);
        panel.add(sintomas);
        panel.add(nombre);
        panel.add(fase);
        panel.add(titulo);
        panel.add(panelj);

        panel.setLayout(null);
        panel.setVisible(true);

        archivos.cargarSintomas(listaM,listaS);

     prueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(panel);

            }
     });

     sintomas.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {
             if (sintomas.getSelectedItem().equals("SegundaFase")){
                 scrollPane.setVisible(false);
                 scrollPane1.setVisible(true);
                 fase.setText("Segunda Fase");
             } else {
                 scrollPane1.setVisible(false);
                 scrollPane.setVisible(true);
                 fase.setText("Primera Fase");
             }
         }
     });

        agregar.addActionListener(new AgregarListener(listaS,nombre,sintomas,nombreA,tipoA,listaM){});


        panel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(panel);
            }
        });

            detener();

    }

    private void agregarSintomas() throws IOException {
        if(esDesarrollo()){
            agregarFinal();
        } else jar();
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

    private void agregarFinal() throws IOException{
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

    private void jar() throws IOException{
        int cont = 0;
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("sintomas");
            try {
                ZipInputStream zip = new ZipInputStream(new FileInputStream("home.jar"));
                for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()){
                    if(!entry.isDirectory() && entry.getName().endsWith(".class")) {
                        String className = entry.getName().replace('/','.');
                        if (className.split("\\.")[0].equals("sintomas")){
                            this.peligro[cont] = className.split("\\.")[1];
                            cont++;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private boolean esDesarrollo(){
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
        for(int i=0;i<a.length;i++){
            if (a[i].equals("src")){
                desarrollo=true;
            }
        }

        return desarrollo;
    }


    private void cargarLosSintomasTexto(){

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exit(JFrame panel){

        synchronized (this) {
            this.notify();
        }
        panel.setVisible(false);
        panel.dispose();
    }

    public ArrayList<String> sacarNombre(){
        return nombreA;
    }

    public ArrayList<String> sacarTipo(){
        return  tipoA;
    }

}
