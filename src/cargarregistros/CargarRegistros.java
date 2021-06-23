package cargarregistros;

import java.io.IOException;
import java.util.Date;

import cargarsintomas.CargarSintomas;
import cargarsintomas.CargarSintomasUI;
import monitor.Registro;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.PrimeraFase;


public class CargarRegistros {

    private Sintomas sintomas;
    private Sintoma perdidaDeOlfato;
    private Sintoma temperaturaAlta;
    private Sintoma tosSeca;
    private Date fecha;


    public CargarRegistros(Sintomas sintomas) {
        this.sintomas = sintomas;
    }

    public CargarRegistros() {

    }

    private void cargarSintoma() {

       /* perdidaDeOlfato = new Bajo("Perdida de olfato");
        temperaturaAlta = new Critico("Temperatura alta");
        tosSeca = new Medio("Tos seca");
        */
        CargarRegistrosUI registro = new CargarRegistrosUI(sintomas);
        //sintomas.forEach(sintoma -> System.out.println(sintoma));

    }

    public Registro getRegistro() {
        cargarSintoma();
        Sintomas sintomas = new Sintomas();
        sintomas.add(perdidaDeOlfato);
        Registro x = new Registro(new Date(),sintomas);

        return x;
    }

    public Date getFecha(){
        return fecha;
    }



}
