package cargarsintomas;

import monitor.Sintoma;
import monitor.Sintomas;

import java.awt.*;
import java.io.IOException;
import java.lang.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class CargarSintomas {

    private Sintomas sintomas;
    private Sintoma perdidaDeOlfato;
    private Sintoma temperaturaAlta;
    private Sintoma tosSeca;
    private CargarSintomasUI carga;
    ArrayList<String> nombreArray = new ArrayList<String>();
    ArrayList<String> tipoArray = new ArrayList<String>();

    public CargarSintomas() {

      //  nombreArray.add("hola");
      //  nombreArray.add("hola2");
       // tipoArray.add("PrimeraFase");
       // tipoArray.add("PrimeraFase");

        cargarSintoma();
        cargarSintomas();
        try {

            //carga.sacarTipo();

            carga = new CargarSintomasUI();
            cargarSintomasArray();
           // cargarSintomaX(carga.sacarNombre(),carga.sacarTipo());
            nombreArray = carga.sacarNombre();
            tipoArray = carga.sacarTipo();
            cargarSintomasArray();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void cargarSintoma() {
       /* perdidaDeOlfato = new Critico("Perdida de olfato");
        temperaturaAlta = new Critico("Temperatura alta");
        tosSeca = new Bajo("Tos seca");
        */
    }

    private void cargarSintomas() {
        sintomas = new Sintomas();
      /*  sintomas.add(perdidaDeOlfato);
        sintomas.add(temperaturaAlta);
        sintomas.add(tosSeca);
       */
    }

    private void cargarSintomasArray() throws Exception {

        for (int i = nombreArray.size();i > 0;i--){
            cargarSintomaX(nombreArray.get(i-1),tipoArray.get(i-1));
        }

    }

   public void cargarSintomaX(String nombre,String tipo) throws Exception{
       try {
           Constructor c = Class.forName("sintomas." + tipo).getConstructor(String.class);
           Sintoma tipos = (Sintoma) c.newInstance(nombre);
           cargarSintomas(tipos);
       } catch(ClassNotFoundException ex) {
          System.out.println(ex.toString());
       }
   }


    void cargarSintomas(Sintoma x) {
        sintomas.add(x);
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

}
