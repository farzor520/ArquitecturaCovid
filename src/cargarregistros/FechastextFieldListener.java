package cargarregistros;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FechastextFieldListener implements ListSelectionListener{

    DefaultListModel<String> listaR;
    JTextArea textField;
    JList fechas;
    ManejoArchivosR archivos;

    public FechastextFieldListener(JTextArea textField, JList fechas, DefaultListModel<String> listaR, ManejoArchivosR archivos) {
        this.textField = textField;
        this.fechas = fechas;
        this.listaR = listaR;
        this.archivos = archivos;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) {
            textField.setText(null);
            String fecha = fechas.getSelectedValuesList().toString();
            fecha = fecha.replace("[", "");
            fecha = fecha.replace("]", "");
            for (int i = 0; i < listaR.getSize();i++) {
                if (fecha.equals(listaR.get(i))) {
                    if (!archivos.esFecha(listaR.get(i+1))) {
                        for (int x = i+1;x < listaR.getSize() ;x++) {
                            if (archivos.esFecha(listaR.get(x))) {
                                break;
                            }
                            textField.append(listaR.get(x) + "\n");
                        }
                    }
                }
            }
        }
    }
}
