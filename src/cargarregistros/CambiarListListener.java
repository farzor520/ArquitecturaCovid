package cargarregistros;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CambiarListListener implements ActionListener {

    private JRadioButton segundaF;
    private JList listaSintomas;
    private JList listaSintomasF;
    private JButton agregar;
    private JButton regresar;
    private JButton agregarS;
    private JButton regresarS;

    public CambiarListListener(JRadioButton segundaF, JList listaSintomas, JList listaSintomasF, JButton agregar, JButton regresar, JButton agregarS, JButton regresarS) {
        this.segundaF = segundaF;
        this.listaSintomas = listaSintomas;
        this.listaSintomasF = listaSintomasF;
        this.agregar = agregar;
        this.regresar = regresar;
        this.agregarS = agregarS;
        this.regresarS = regresarS;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (segundaF.isSelected()){
            listaSintomas.setVisible(false);
            listaSintomasF.setVisible(true);
            agregar.setVisible(false);
            regresar.setVisible(false);
            agregarS.setVisible(true);
            regresarS.setVisible(true);
        }
    }
}
