package cargarsintomas;

import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.Bajo;
import sintomas.Contables;
import sintomas.Critico;
import sintomas.Medio;

import java.awt.*;
import java.lang.*;
import java.lang.reflect.Constructor;

public class CargarSintomas {

    private Sintomas sintomas;
    private Sintoma perdidaDeOlfato;
    private Sintoma temperaturaAlta;
    private Sintoma tosSeca;

    public CargarSintomas() {
        cargarSintoma();
        cargarSintomas();

    }

    void cargarSintoma() {
        perdidaDeOlfato = new Critico("Perdida de olfato");
        temperaturaAlta = new Critico("Temperatura alta");
        tosSeca = new Bajo("Tos seca");
    }

    private void cargarSintomas() {
        sintomas = new Sintomas();
        sintomas.add(perdidaDeOlfato);
        sintomas.add(temperaturaAlta);
        sintomas.add(tosSeca);
    }

   public void cargarSintomaX(String nombre,String tipo) throws Exception{
       try {
           Constructor c = Class.forName("sintomas." + tipo).getConstructor(String.class);
           Sintoma tipos = (Sintoma) c.newInstance(nombre);
          // Class<?> classlldr = Class.forName("sintomas");
         //  System.out.println("Name of Class  = " + classlldr.getName());
           cargarSintomas(tipos);
       } catch(ClassNotFoundException ex) {
           System.out.println(ex.toString());
       }
   }


    private void cargarSintomas(Sintoma x) {
        sintomas.add(x);
        System.out.println("resultado: " + getSintomas());
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

}
