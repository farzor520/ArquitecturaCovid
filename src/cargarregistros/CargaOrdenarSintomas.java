package cargarregistros;

import monitor.Sintomas;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CargaOrdenarSintomas {

    private final ManejoArchivosR archivos = new ManejoArchivosR();

    public void cargarSintomas(Sintomas sintomas, DefaultListModel<String> listaM, DefaultListModel<String> listaS, DefaultListModel<String> listaF, int dias, JLabel mensajeAdvertencia, JLabel fase) {



        AtomicInteger segundaF = new AtomicInteger(1);
        listaM.removeAllElements();
        List list = new ArrayList<String>();
        List list2 = new ArrayList<String>();

        sintomas.forEach(sintoma -> {

            if (sintoma.getClass().getName().equals("sintomas.SegundaFase")){
                segundaF.set(2);
                list2.add(sintoma.toString());
            }else {
                list.add(sintoma.toString());
            }
        });

        Collections.sort(list);
        Collections.sort(list2);

        Ordenar(list,listaM);
        Ordenar(list2,listaS);

        dias = archivos.manejoDeDias(listaM);
        listaF.removeAllElements();

        if (dias>=1){
            mensajeAdvertencia.setText("Recuerda siempre hacerte un control diario y visitar a tu medico");
        }

        String x = segundaF.toString();
        if (x.equals("2")){
            mensajeAdvertencia.setText("Realizate una prueba especializada y visita a tu medico");
            fase.setText("Segunda Fase");
        }
    }

    private void Ordenar(List list, DefaultListModel<String> lista) {
        for (Object s : list) {
            lista.addElement(s.toString());
        }
    }

}
