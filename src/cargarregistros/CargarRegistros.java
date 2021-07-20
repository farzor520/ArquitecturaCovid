package cargarregistros;

import java.util.Date;

import diagnosticos.DiagnosticoSimple;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintomas;



public class CargarRegistros {

    private Sintomas sintomas;
    private Registros registros;
    private DiagnosticoSimple diagnostico;
    private Date fecha;
    private CargarRegistrosUI registro;
    private Registro registro1;


    public CargarRegistros(Sintomas sintomas) {
        this.sintomas = sintomas;
        cargarRegistros();
    }

    private void cargarRegistros() {
        registros = new Registros();
    }

    private void cargarSintoma() {

    }

    public Registro getRegistro() {
        cargarSintoma();
        Sintomas sintomasUsuario = new Sintomas();
        registro = new CargarRegistrosUI(sintomas, registros);
        return new Registro(new Date(),sintomasUsuario);
    }

    public Registros getRegistros() {
        registro = new CargarRegistrosUI(sintomas, registros);
        return registros;
    }

    public Date getFecha(){
        return fecha;
    }

}
