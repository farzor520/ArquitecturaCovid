package cargarsintomas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;

public class CargarSintomasResponsive implements AWTEventListener{

    private JFrame panel;
    Dimension dimension;
    JScrollPane scrollPane,scrollPane1;
    JButton agregar,prueba;

    public CargarSintomasResponsive(JFrame panel, Dimension dimension, JScrollPane scrollPane, JScrollPane scrollPane1, JButton agregar, JButton prueba) {
        this.panel = panel;
        this.dimension = dimension;
        this.scrollPane = scrollPane;
        this.scrollPane1 = scrollPane1;
        this.agregar = agregar;
        this.prueba = prueba;
    }

    @Override
    public void eventDispatched(AWTEvent event) {

            Dimension newScreenSize = panel.getSize();
            if (newScreenSize.width != dimension.width || newScreenSize.height != dimension.height)
            {

                changeSize(newScreenSize.width - 650,newScreenSize.height - 440);
            }
    }

    private void changeSize(int width, int height){
        scrollPane.setBounds(350, 100, 250+width, 250+height);
        scrollPane1.setBounds(350, 100, 250+width, 250+height);
        agregar.setBounds(20, 280+height, 90, 30);
        prueba.setBounds(200, 280+height, 110, 30);
    }

}
