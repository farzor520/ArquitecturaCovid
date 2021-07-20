package cargarsintomas;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class EnterListener implements KeyListener {

    private JTextField nombre;
    private DefaultListModel<String> listaS,listaM;
    private JComboBox sintomas;
    private List nombreA,tipoA;

    public EnterListener(JTextField nombre, DefaultListModel<String> listaS, JComboBox sintomas, List nombreA, List tipoA, DefaultListModel<String> listaM) {
        this.nombre = nombre;
        this.listaS = listaS;
        this.listaM = listaM;
        this.sintomas = sintomas;
        this.nombreA = nombreA;
        this.tipoA = tipoA;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){
            nombre.addActionListener(new AgregarListener(listaS,nombre,sintomas,nombreA,tipoA,listaM){});
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
