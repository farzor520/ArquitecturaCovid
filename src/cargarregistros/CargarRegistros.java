package cargarregistros;

import java.util.Date;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;
import sintomas.Bajo;
import sintomas.Critico;
import sintomas.Medio;


public class CargarRegistros {

    private Sintomas sintomas;
    private Sintoma perdidaDeOlfato;
    private Sintoma temperaturaAlta;
    private Sintoma tosSeca;

    public CargarRegistros(Sintomas sintomas) {
        this.sintomas = sintomas;
    }

    private void cargarSintoma() {
        perdidaDeOlfato = new Bajo("Perdida de olfato");
        temperaturaAlta = new Critico("Temperatura alta");
        tosSeca = new Medio("Tos seca");
    }

    public Registro getRegistro() {
        cargarSintoma();
        Sintomas sintomas = new Sintomas();
        sintomas.add(temperaturaAlta);
        return new Registro(new Date(),sintomas);
    }
}
