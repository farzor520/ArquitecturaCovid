package cargarregistros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarListener implements ActionListener {


    private JList listaSintomas;
    private DefaultListModel<String> listaM;
    private DefaultListModel<String> listaF;

    public AgregarListener(JList listaSintomas,DefaultListModel<String> listaM, DefaultListModel<String> listaF){
        this.listaSintomas = listaSintomas;
        this.listaM = listaM;
        this.listaF = listaF;
    }

    public void actionPerformed(ActionEvent e){
        String remplazo = listaSintomas.getSelectedValuesList().toString().replace("[", "").replace("]","");
        listaF.addElement(remplazo);
        listaM.removeElement(remplazo);
    }
}
