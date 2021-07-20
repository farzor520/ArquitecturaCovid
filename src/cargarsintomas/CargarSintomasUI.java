package cargarsintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class CargarSintomasUI extends JFrame{

    private final DefaultListModel<String> listaM = new DefaultListModel<>();
    private final DefaultListModel<String> listaS = new DefaultListModel<>();
    private JTextField nombre;
    private JComboBox sintomas;
    private List <String> nombreA = new ArrayList();
    private List <String> tipoA = new ArrayList();
    private final ManejoArchivosS archivos = new ManejoArchivosS();
    private Dimension dimension;

    public CargarSintomasUI(Dimension dimension) throws IOException {

        this.dimension = dimension;
        dimension.setSize(650, 440);

        String direccion = archivos.getPath();
        archivos.revizarExistenciaArchivo();
        archivos.cargarLosSintomasTexto(direccion,nombreA,tipoA);
        String[] peligro = new String[2];
        archivos.agregarSintomas(peligro);

        JFrame panel = new JFrame();
        panel.setSize(650, 440);

        panel.setTitle("Sintomas");

        JPanel panelj = new JPanel();
        panelj.setLayout(new FlowLayout());
        Color colorCafe = new Color(4, 120, 239);
        panelj.setSize(1400,900);
        panelj.setBackground(colorCafe);

        JLabel titulo = new JLabel("MONITOREO COVID");
        titulo.setBounds(220, 10, 230, 80);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo);

        JLabel fase = new JLabel("Primera fase");
        fase.setBounds(260, 30, 230, 80);
        fase.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(fase);

        JLabel tipoSintomas = new JLabel("Tipo de sintomas");
        tipoSintomas.setBounds(20, 60, 230, 80);
        tipoSintomas.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(tipoSintomas);

        JLabel agregarSintomaTexto = new JLabel("Sintoma a agregar");
        agregarSintomaTexto.setBounds(20, 148, 230, 80);
        agregarSintomaTexto.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(agregarSintomaTexto);

        JLabel sintomasAgregados = new JLabel("Sintomas Agregados");
        sintomasAgregados.setBounds(400, 50, 230, 80);
        sintomasAgregados.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(sintomasAgregados);

        nombre = new JTextField();
        nombre.setBounds(20, 200, 120, 30);
        panel.add(nombre);

        sintomas = new JComboBox<>(peligro);
        sintomas.setBounds(20, 110, 130, 20);
        panel.add(sintomas);

        JButton agregar = new JButton("AGREGAR");
        agregar.setBounds(20, 280, 90, 30);
        panel.add(agregar);

        JButton prueba = new JButton("FINALIZAR");
        prueba.setBounds(200, 280, 110, 30);
        panel.add(prueba);

        JList listaSintomas = new JList<>(listaM);
        listaSintomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JList listaSintomasF = new JList<>(listaS);
        listaSintomasF.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaSintomas);
        scrollPane.setBounds(350, 100, 250, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        JScrollPane scrollPane1 = new JScrollPane(listaSintomasF);
        scrollPane1.setBounds(350, 100, 250, 250);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1.setVisible(false);
        panel.add(scrollPane1);
        panel.add(panelj);

        panel.setLayout(null);
        panel.setVisible(true);

        archivos.cargarSintomas(listaM,listaS);
        sintomas.addItemListener(new SintomasItemListener(sintomas,scrollPane,scrollPane1, fase));
        agregar.addActionListener(new AgregarListener(listaS,nombre,sintomas,nombreA,tipoA,listaM){});
        nombre.addKeyListener(new EnterListener(nombre,listaS,sintomas,nombreA,tipoA,listaM));
        Toolkit.getDefaultToolkit().addAWTEventListener(new CargarSintomasResponsive(panel,dimension,scrollPane,scrollPane1,agregar,prueba){}, AWTEvent.PAINT_EVENT_MASK);

        prueba.addActionListener(new ActionListener() {
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

    private synchronized void detener(){
        try {
            this.wait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void exit(JFrame panel){

        synchronized (this) {
            this.notify();
        }
        panel.setVisible(false);
        panel.dispose();
    }
    public List sacarNombre(){
        return nombreA;
    }

    public List sacarTipo(){
        return  tipoA;
    }
}
