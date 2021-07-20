package cargarsintomas;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AgregarListener implements ActionListener {

    private ManejoArchivosS archivos = new ManejoArchivosS();
    private JTextField nombre;
    private JComboBox sintomas;
    private List<String> nombreA;
    private List<String> tipoA;
    private DefaultListModel<String> listaM;
    private DefaultListModel<String> listaS;
    private String[] peligro = new String[10];
    private AgregarSintomasFlexible flex = new AgregarSintomasFlexible();


    public AgregarListener(DefaultListModel<String> listaS, JTextField nombre, JComboBox sintomas, List nombreA, List tipoA, DefaultListModel<String> listaM){
        this.nombre=nombre;
        this.sintomas=sintomas;
        this.nombreA=nombreA;
        this.tipoA=tipoA;
        this.listaM=listaM;
        this.listaS = listaS;
    }

    public void actionPerformed(ActionEvent e) {
        String nome = nombre.getText().toUpperCase(Locale.ROOT);

        if (archivos.sintomasRepetidos(nome) && !validadorRepetidos()) {
            if (!nome.equals("")) {
                try {
                    nombreA.add(nome);
                    tipoA.add(Objects.requireNonNull(sintomas.getSelectedItem()).toString());
                    if (archivos.esDesarrollo()) {
                        flex.agregarFinal(peligro);
                    }else {
                        flex.jar(peligro);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                archivos.guardarArchivo1(nome, Objects.requireNonNull(sintomas.getSelectedItem()).toString());
                listaM.removeAllElements();
                listaS.removeAllElements();
                archivos.cargarSintomas(listaM,listaS);
            }
        }
    }



    private boolean validadorRepetidos() {
        String nome = nombre.getText().toUpperCase(Locale.ROOT);

        boolean repetido = false;

        for (int i = 0;i<= listaM.size()-1; i++){
            if(nome.equals(listaM.get(i)) || validarSinonimos(nome, listaM.get(i))){
                repetido = true;
            }
        }
        return repetido;
    }

    private boolean validarSinonimos(String primero, String segundo){

        if (primero.length() >= 6 && segundo.length()>=6){
            if (primero.startsWith("fiebre") || primero.startsWith("temp")){
                if (segundo.startsWith("temp") || segundo.startsWith("fiebre") ){
                    return true;
                }
            }
        }
        if (primero.length()>2) {
            primero = primero.substring(0, 3);
            segundo = segundo.substring(0, 3);
            if (!primero.equals("dol")) {
                return primero.equals(segundo);
            }
        }
        return false;
    }
}
