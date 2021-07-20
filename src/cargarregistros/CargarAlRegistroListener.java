package cargarregistros;

import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CargarAlRegistroListener implements ActionListener {

    private DefaultListModel<String> listaF;
    private DefaultListModel<String> listaFecha;
    private DefaultListModel<String> listaR;
    private DefaultListModel<String> listaM;
    private DefaultListModel<String> listaS;
    private JLabel fase;
    private Sintomas sintomas;
    private ManejoArchivosR archivos;
    private Registros registros;
    private int dias;
    private CargaOrdenarSintomas ordenar;
    private JTextArea textField;
    private JButton cargarAlRegistro;
    private JLabel mensajeAdvertencia;

    public CargarAlRegistroListener(DefaultListModel<String> listaF, DefaultListModel<String> listaR,DefaultListModel<String> listaS, JLabel fase, Sintomas sintomas, ManejoArchivosR archivos, Registros registros, DefaultListModel<String> listaM, int dias, CargaOrdenarSintomas ordenar, JTextArea textField, JButton cargarAlRegistro, JLabel mensajeAdvertencia,DefaultListModel<String> listaFecha) {
        this.listaF = listaF;
        this.listaR = listaR;
        this.listaM = listaM;
        this.listaFecha = listaFecha;
        this.sintomas = sintomas;
        this.archivos = archivos;
        this.registros = registros;
        this.dias = dias;
        this.ordenar = ordenar;
        this.textField = textField;
        this.cargarAlRegistro = cargarAlRegistro;
        this.mensajeAdvertencia = mensajeAdvertencia;
        this.listaS = listaS;
        this.fase = fase;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date date = new Date();
        Sintomas todosS = new Sintomas();

        if (!listaF.isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            archivos.guardarRegistro("" + format.format(date) + "\n");
            listaR.addElement("" + format.format(date));
            listaFecha.addElement("" + format.format(date));
            sintomas.forEach(sintoma -> {
                if (listaF.contains(sintoma.toString())){
                    archivos.guardarRegistro(sintoma.toString()+ ": ");
                    archivos.guardarRegistro(sintoma.peso()+"\n");
                    listaR.addElement(sintoma.toString()+ ": "+sintoma.peso());
                    todosS.add(sintoma);
                }
            });
            Registro reg = new Registro(date,todosS);
            registros.push(reg);

            if (listaF.size() >= listaM.size()){
                dias++;
            }
            ordenar.cargarSintomas(sintomas,listaM,listaS,listaF,dias,mensajeAdvertencia,fase);
           textField.setText("");
            cargarAlRegistro.setVisible(false);

        }else {
            mensajeAdvertencia.setText("Porfavor pase los sintomas de lista de sintomas a sintomas a cargar");
        }

    }

}
