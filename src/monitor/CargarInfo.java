package monitor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CargarInfo {

    private static Sintomas sintomas;
    Sintoma perdidaDeOlfato;
    Sintoma temperaturaAlta;
    Sintoma tosSeca;
    Sintoma dolorDeGarganta;
    Sintoma oximetro;



    public CargarInfo() {
        cargarSintoma();
        cargarSintomas();
    }


  /*  public static Sintoma cargarNuevoMedio(String nombre) {
       Sintoma sintoma = new Medio(nombre);
       sintomas.add(sintoma);
       System.out.println(sintomas);
       cargarRegistros(sintoma);
       return sintoma;
    }

    public static Sintoma cargarNuevoContable(String nombreSin,String valor ) {
        int i =Integer.parseInt(valor);
        Sintoma sintoma = new Contables(nombreSin);
        sintomas.add(sintoma);
        System.out.println(sintomas);
        cargarRegistros(sintoma);
        return sintoma;
    }

   */

    private void cargarSintoma() {
        /*
        perdidaDeOlfato = new Critico("Perdida de olfato");
        temperaturaAlta = new Contables("Temperatura alta");
        tosSeca = new Medio("tos seca");
        dolorDeGarganta = new Bajo("dolor de garganta");
        oximetro = new Contables("oximetro");
         */
    }

    private void cargarSintomas() {
        sintomas = new Sintomas();
        sintomas.add(perdidaDeOlfato);
        sintomas.add(temperaturaAlta);
        sintomas.add(tosSeca);
        sintomas.add(dolorDeGarganta);
        sintomas.add(oximetro);

    }

    public Registros cargarRegistros() {
        Sintomas sintomas = new Sintomas();
        sintomas.add(temperaturaAlta);
        sintomas.add(perdidaDeOlfato);
        sintomas.add(tosSeca);
        sintomas.add(dolorDeGarganta);


        Registros registros = new Registros();
        registros.push(new Registro(new Date(),sintomas));
        return registros;
    }

    public static Registros cargarRegistros(Sintoma sin) {
        Sintomas sintomas = new Sintomas();
        sintomas.add(sin);


        Registros registros = new Registros();
        registros.push(new Registro(new Date(),sintomas));
        return registros;
    }

    public Sintomas getSintomas() {
        return sintomas;
    }
}
