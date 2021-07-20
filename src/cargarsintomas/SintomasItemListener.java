package cargarsintomas;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class SintomasItemListener implements ItemListener {

    private JLabel fase;
    private JComboBox sintomas;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane;

    public SintomasItemListener(JComboBox sintomas,JScrollPane scrollPane,JScrollPane scrollPane1,JLabel fase){
        this.sintomas = sintomas;
        this.scrollPane = scrollPane;
        this.scrollPane1 = scrollPane1;
        this.fase = fase;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (Objects.requireNonNull(sintomas.getSelectedItem()).equals("SegundaFase")){
            scrollPane.setVisible(false);
            scrollPane1.setVisible(true);
            fase.setText("Segunda Fase");
        } else {
            scrollPane1.setVisible(false);
            scrollPane.setVisible(true);
            fase.setText("Primera Fase");
        }
    }
}
