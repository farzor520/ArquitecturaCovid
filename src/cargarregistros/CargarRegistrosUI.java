package cargarregistros;

import monitor.Registros;
import monitor.Sintomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class CargarRegistrosUI extends JFrame{
    private final DefaultListModel<String> listaM = new DefaultListModel<>();
    private final DefaultListModel<String> listaS = new DefaultListModel<>();
    private final DefaultListModel<String> listaF = new DefaultListModel<>();
    private final DefaultListModel<String> listaR = new DefaultListModel<>();
    private final DefaultListModel<String> listaFecha = new DefaultListModel<>();
    private JList listaSintomas;
    private JList listaSintomasF;
    private JList listaSintomasFinal;
    private int dias;

    public CargarRegistrosUI(Sintomas sintomas, Registros registros) {

        ManejoArchivosR archivos = new ManejoArchivosR();
        archivos.revizarExistenciaArchivo();
        JFrame panel = new JFrame();

        panel.setSize(650, 700);
        panel.setTitle("Registros");

        JPanel panelj = new JPanel();
        Color colorCafe = new Color(4, 120, 239);
        panelj.setSize(650, 700);
        panelj.setBackground(colorCafe);

        JLabel titulo = new JLabel("REGISTRO COVID");
        titulo.setBounds(220, 10, 230, 80);
        panel.add(titulo);

        JRadioButton segundaF = new JRadioButton("Segunda Fase");
        segundaF.setBounds(20, 30, 120, 20);
        panel.add(segundaF);

        JRadioButton primeraF = new JRadioButton("Primera Fase");
        primeraF.setBounds(20, 10, 120, 20);
        panel.add(primeraF);

        ButtonGroup bg=new ButtonGroup();
        bg.add(primeraF);bg.add(segundaF);

        JLabel fase = new JLabel("Primera fase");
        fase.setBounds(260, 30, 230, 80);
        panel.add(fase);

        JLabel sintomasTitulo = new JLabel("Lista de Sintomas");
        sintomasTitulo.setBounds(30, 50, 230, 80);
        panel.add(sintomasTitulo);

        JLabel registroTitulo = new JLabel("Sintomas a Cargar");
        registroTitulo.setBounds(350, 50, 230, 80);
        panel.add(registroTitulo);

        JLabel mensajeAdvertencia = new JLabel("No hay Registros empieza a tomarte la prueba");
        mensajeAdvertencia.setBounds(20, 580, 550, 80);
        mensajeAdvertencia.setForeground(new Color(198, 150, 10));
        panel.add(mensajeAdvertencia);

        JLabel textoRegistros = new JLabel("Registros");
        textoRegistros.setBounds(40, 350, 230, 80);
        panel.add(textoRegistros);

        listaSintomasFinal = new JList<>(listaF);
        listaSintomasFinal.setBounds(350, 100, 250, 250);
        listaSintomasFinal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(listaSintomasFinal);

        JButton agregar = new JButton(">");
        agregar.setBounds(290, 180, 50, 30);
        panel.add(agregar);

        JButton regresar = new JButton("<");
        regresar.setBounds(290, 220, 50, 30);
        panel.add(regresar);

        JButton agregarS = new JButton(">");
        agregarS.setBounds(290, 180, 50, 30);
        agregarS.setVisible(false);
        panel.add(agregarS);

        JButton regresarS = new JButton("<");
        regresarS.setBounds(290, 220, 50, 30);
        regresarS.setVisible(false);
        panel.add(regresarS);

        JButton cargarAlRegistro = new JButton("Cargar");
        cargarAlRegistro.setBounds(350, 360, 110, 30);
        panel.add(cargarAlRegistro);

        JButton finalizarRegistro = new JButton("Finalizar");
        finalizarRegistro.setBounds(480, 360, 110, 30);
        panel.add(finalizarRegistro);

        listaSintomas = new JList<>(listaM);
        listaSintomas.setBounds(30, 100, 250, 250);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(listaSintomas);

        listaSintomasF = new JList<>(listaS);
        listaSintomasF.setBounds(30, 100, 250, 250);
        listaSintomasF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaSintomasF.setVisible(false);
        panel.add(listaSintomasF);

        JList listaRegistros = new JList<>(listaR);
        listaRegistros.setBounds(40, 400, 550, 200);
        listaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTextArea textField = new JTextArea();
        textField.setBounds(40, 400, 550, 200);
        textField.setEditable ( false );

        JList fechas = new JList<>(listaFecha);
        fechas.setBounds(20, 400, 240, 200);
        fechas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(fechas);

        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setBounds(270, 400, 330, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.getContentPane().add(scrollPane);
        panel.add(scrollPane);

        Date data = new Date();
        String x = data.toString();
        JLabel fecha = new JLabel("FECHA DE EVALUCION: " + x);
        fecha.setBounds(30, 240, 320, 250);
        panel.add(fecha);
        panel.add(panelj);

        panel.setLayout(null);
        panel.setVisible(true);

        CargaOrdenarSintomas ordenar = new CargaOrdenarSintomas();
        ordenar.cargarSintomas(sintomas,listaM,listaS,listaF,dias, mensajeAdvertencia, fase);
        archivos.registroTexto(listaR,registros,listaFecha);
        archivos.ordenarListaFecha(listaFecha);
        archivos.soloUnDia(listaR,cargarAlRegistro,mensajeAdvertencia);
        fechas.addListSelectionListener(new FechastextFieldListener(textField,fechas,listaR,archivos));
        agregar.addActionListener(new AgregarListener(listaSintomas,listaM,listaF){});
        regresar.addActionListener(new RegresarListener(listaSintomasFinal,listaM,listaF){});
        cargarAlRegistro.addActionListener(new CargarAlRegistroListener(listaF,listaR,listaS, fase,sintomas, archivos,registros,listaM,dias,ordenar, textField, cargarAlRegistro, mensajeAdvertencia,listaFecha));
        primeraF.addActionListener(new CambiarListListener(primeraF,listaSintomasF,listaSintomas, agregarS, regresarS, agregar, regresar));
        segundaF.addActionListener(new CambiarListListener(segundaF,listaSintomas,listaSintomasF, agregar, regresar, agregarS, regresarS));
        agregarS.addActionListener(new AgregarListener(listaSintomasF,listaS,listaF));
        regresarS.addActionListener(new RegresarListener(listaSintomasFinal,listaS,listaF));

        finalizarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(panel);
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

    private void exit(JFrame panel){

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
}
