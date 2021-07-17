package cargarregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.PrimeraFase;
import sintomas.SegundaFase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


public class CargarRegistrosUI extends JFrame{

    static DefaultListModel<String> listaM = new DefaultListModel<String>();
    static DefaultListModel<String> listaS = new DefaultListModel<String>();
    static DefaultListModel<String> listaF = new DefaultListModel<String>();
    static DefaultListModel<String> listaR = new DefaultListModel<String>();
    JRadioButton primeraF;
    JRadioButton segundaF;
    static JPanel panelj;
    static JLabel titulo;
    static JLabel fase;
    static JButton agregar;
    static JButton regresar;
    static JButton agregarS;
    static JButton regresarS;
    static JButton actulizar,finalizarRegistro;
    static JButton cargarAlRegistro;
    static JLabel fecha;
    static JList listaSintomas;
    static JList listaSintomasF;
    static JList listaSintomasFinal;
    static JList listaRegistros;
    static JLabel sintomasTitulo;
    static JLabel registroTitulo;
    static JLabel textoRegistros;
    static JLabel mensajeAdvertencia;
    static JTextArea textField;
    private PrimeraFase primera;
    private SegundaFase segunda;

    private int dias;




    ManejoArchivosR archivos = new ManejoArchivosR();
    Date data = new Date();

    public CargarRegistrosUI(Sintomas sintomas, Sintomas sintomas1, Registros registros) {



        archivos.revizarExistenciaArchivo();

        JFrame panel = new JFrame();

        panel.setSize(650, 700);
        panel.setTitle("Registros");

        panelj = new JPanel();
        Color colorCafe = new Color(4, 120, 239);
        panelj.setSize(650, 700);
        panelj.setBackground(colorCafe);


        titulo = new JLabel("REGISTRO COVID");
        titulo.setBounds(220, 10, 230, 80);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        segundaF=new JRadioButton("Segunda Fase");
        segundaF.setBounds(20, 30, 120, 20);

        primeraF=new JRadioButton("Primera Fase");
        primeraF.setBounds(20, 10, 120, 20);

        ButtonGroup bg=new ButtonGroup();
        bg.add(primeraF);bg.add(segundaF);

        fase = new JLabel("Primera fase");
        fase.setBounds(260, 30, 230, 80);
        fase.setFont(new Font("Arial", Font.BOLD, 15));

        sintomasTitulo = new JLabel("Lista de Sintomas");
        sintomasTitulo.setBounds(30, 50, 230, 80);
        sintomasTitulo.setFont(new Font("Arial", Font.BOLD, 15));

        registroTitulo = new JLabel("Sintomas a Cargar");
        registroTitulo.setBounds(350, 50, 230, 80);
        registroTitulo.setFont(new Font("Arial", Font.BOLD, 15));

        mensajeAdvertencia = new JLabel("No hay Registros empieza a tomarte la prueba");
        mensajeAdvertencia.setBounds(20, 580, 550, 80);
        mensajeAdvertencia.setFont(new Font("Arial", Font.BOLD, 17));
        mensajeAdvertencia.setForeground(new Color(198, 150, 10));

        textoRegistros = new JLabel("Registros");
        textoRegistros.setBounds(40, 350, 230, 80);
        textoRegistros.setFont(new Font("Arial", Font.BOLD, 13));

        listaSintomasFinal = new JList<String>(listaF);
        listaSintomasFinal.setBounds(350, 100, 250, 250);
        listaSintomasFinal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        agregar = new JButton(">");
        agregar.setBounds(290, 180, 50, 30);

        regresar = new JButton("<");
        regresar.setBounds(290, 220, 50, 30);

        agregarS = new JButton(">");
        agregarS.setBounds(290, 180, 50, 30);
        agregarS.setVisible(false);

        regresarS = new JButton("<");
        regresarS.setBounds(290, 220, 50, 30);
        regresarS.setVisible(false);

        actulizar = new JButton("Actualizar");
        actulizar.setBounds(10, 10, 110, 30);


        cargarAlRegistro = new JButton("Cargar");
        cargarAlRegistro.setBounds(350, 360, 110, 30);

        finalizarRegistro = new JButton("Finalizar");
        finalizarRegistro.setBounds(480, 360, 110, 30);

        listaSintomas = new JList<String>(listaM);
        listaSintomas.setBounds(30, 100, 250, 250);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaSintomasF = new JList<String>(listaS);
        listaSintomasF.setBounds(30, 100, 250, 250);
        listaSintomasF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaSintomasF.setVisible(false);

        listaRegistros = new JList<String>(listaR);
        listaRegistros.setBounds(40, 400, 550, 200);
        listaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        textField = new  JTextArea(

        );
        textField.setBounds(40, 400, 550, 200);
        textField.setEditable ( false );

        JScrollPane scrollPane = new JScrollPane(textField);

        scrollPane.setBounds(40, 400, 550, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        String x =data.toString();
        fecha = new JLabel("FECHA DE EVALUCION: " + x);
        fecha.setBounds(30, 240, 320, 250);


        panel.getContentPane().add(scrollPane);
        panel.add(scrollPane);
        panel.add(primeraF);
        panel.add(segundaF);
        panel.add(mensajeAdvertencia);
        panel.add(finalizarRegistro);
        panel.add(textoRegistros);
        panel.add(fecha);
        panel.add(sintomasTitulo);
        panel.add(registroTitulo);
        panel.add(listaSintomasF);
        panel.add(listaSintomas);
        panel.add(regresar);
        panel.add(agregar);
        panel.add(regresarS);
        panel.add(agregarS);
        panel.add(cargarAlRegistro);
        panel.add(listaSintomasFinal);
        panel.add(fase);
        panel.add(titulo);
        panel.add(panelj);

        panel.setLayout(null);
        panel.setVisible(true);

        cargarSintomas(sintomas);

        archivos.registroTexto(listaR,registros);

        for (int i = 0; i < listaR.getSize();i++) {
            textField.append(listaR.get(i) + "\n");
        }

        agregar.addActionListener(new AgregarListener(listaSintomas,listaM,listaF){});

        regresar.addActionListener(new RegresarListener(listaSintomasFinal,listaM,listaF){});


        cargarAlRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Date date = new Date();
               Sintomas todosS = new Sintomas();

               if (!listaF.isEmpty()){
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               archivos.guardarRegistro("" + format.format(date) + "\n");
                   listaR.addElement("" + format.format(date));
                sintomas.forEach(sintoma -> {
                    if (listaF.contains(sintoma.toString())){
                        archivos.guardarRegistro(sintoma.toString()+ ": ");
                        archivos.guardarRegistro(sintoma.peso()+"\n");
                        listaR.addElement(sintoma.toString()+ ": "+sintoma.peso());
                        todosS.add(sintoma);
                        //sintomas1.add(sintoma);
                    }
                });
                   Registro reg = new Registro(date,todosS);
                   registros.push(reg);

                if (listaF.size() >= listaM.size()){
                    dias++;
                }
                //listaR.removeAllElements();

               // archivos.registroTexto(listaR, registros);

                cargarSintomas(sintomas);
                textField.setText("");
                for (int i = 0; i < listaR.getSize();i++) {
                    textField.append(listaR.get(i) + "\n");
                }
            }
                cargarAlRegistro.setVisible(false);
            }
        });

        finalizarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(panel);
            }
        });

        actulizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        primeraF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (primeraF.isSelected()){
                    listaSintomas.setVisible(true);
                    listaSintomasF.setVisible(false);
                    agregar.setVisible(true);
                    regresar.setVisible(true);
                    agregarS.setVisible(false);
                    regresarS.setVisible(false);
                }
            }
        });

        segundaF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundaF.isSelected()){
                    listaSintomas.setVisible(false);
                    listaSintomasF.setVisible(true);
                    agregar.setVisible(false);
                    regresar.setVisible(false);
                    agregarS.setVisible(true);
                    regresarS.setVisible(true);
                }
            }
        });

        agregarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String remplazo = listaSintomasF.getSelectedValuesList().toString().replace("[", "").replace("]","");
                listaF.addElement(remplazo);
                listaS.removeElement(remplazo);
            }
        });

        regresarS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String remplazo = listaSintomasFinal.getSelectedValuesList().toString().replace("[", "").replace("]", "");
                listaF.removeElement(remplazo);
                listaS.addElement(remplazo);
            }
        });

        panel.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit(panel);
            }
        });


        detener();
    }

    private void cargarSintomas(Sintomas sintomas) {



        AtomicInteger segundaF = new AtomicInteger(1);
        listaM.removeAllElements();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();

        sintomas.forEach(sintoma -> {

            if (sintoma.getClass().getName() == "sintomas.SegundaFase"){
                segundaF.set(2);
                list2.add(sintoma.toString());
            }else {
                list.add(sintoma.toString());
            }

        });

        Collections.sort(list);
        Collections.sort(list2);

        Ordenar(list,listaM);
        Ordenar(list2,listaS);

        dias = archivos.manejoDeDias(listaM);
        listaF.removeAllElements();

        if (dias>=1){
            mensajeAdvertencia.setText("Recuerda siempre hacerte un control diario y visitar a tu medico");
        }

        String x = segundaF.toString();

        if (x.equals("2")){
            mensajeAdvertencia.setText("Realizate una prueba especializada y visita a tu medico");
            fase.setText("Segunda Fase");
        }
    }

    private void Ordenar(ArrayList<String> list, DefaultListModel<String> lista) {

        for (int x = 0;x < list.size(); x++){
            lista.addElement(list.get(x));
        }

    }


    public void exit(JFrame panel){

        synchronized (this) {
            this.notify();
        }
        panel.setVisible(false);
        panel.dispose();
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
