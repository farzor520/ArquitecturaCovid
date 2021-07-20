package cargarregistros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegresarListener implements ActionListener {


    private JList listaSintomasFinal;
    private DefaultListModel<String> listaM;
    private DefaultListModel<String> listaF;

    public RegresarListener(JList listaSintomasFinal, DefaultListModel<String> listaM, DefaultListModel<String> listaF){
        this.listaSintomasFinal = listaSintomasFinal;
        this.listaM = listaM;
        this.listaF = listaF;
    }

    public void actionPerformed(ActionEvent e){
            String remplazo = listaSintomasFinal.getSelectedValuesList().toString().replace("[", "").replace("]", "");
            listaF.removeElement(remplazo);
            listaM.addElement(remplazo);
    }
}
