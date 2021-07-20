package cargarsintomas;

import monitor.Sintoma;
import monitor.Sintomas;

import java.awt.*;
import java.util.List;
import java.lang.reflect.Constructor;


public class CargarSintomas {


    private Sintomas sintomas;
    private List nombreArray;
    private List tipoArray;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public CargarSintomas() {

        cargarSintomas();
        try {
            CargarSintomasUI carga = new CargarSintomasUI(screenSize);
            nombreArray = carga.sacarNombre();
            tipoArray = carga.sacarTipo();
            cargarSintomasArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarSintomas() {
        sintomas = new Sintomas();
    }

    private void cargarSintomasArray() throws Exception {
        for (int i = nombreArray.size();i > 0;i--){
            cargarSintomaX(nombreArray.get(i-1).toString(),tipoArray.get(i-1).toString());
        }
    }

    private void cargarSintomaX(String nombre,String tipo) throws Exception{
       try {
           Constructor c = Class.forName("sintomas." + tipo).getConstructor(String.class);
           Sintoma tipos = (Sintoma) c.newInstance(nombre);
           cargarSintomas(tipos);
       } catch(ClassNotFoundException ex) {
          System.out.println(ex.toString());
       }
   }
    private void cargarSintomas(Sintoma x) {
        sintomas.add(x);
    }

    public Sintomas getSintomas() {
        return sintomas;
    }
}
