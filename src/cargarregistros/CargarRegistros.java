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

    public void cargarRegistros() {
       // DatosRegistros datosRegistros = new DatosRegistros();
        registros = new Registros();
    }

    private void cargarSintoma() {

    }

    public Registro getRegistro() {
        cargarSintoma();
        Sintomas sintomasUsuario = new Sintomas();
        registro = new CargarRegistrosUI(sintomas,sintomasUsuario,registros);
        return new Registro(new Date(),sintomasUsuario);
    }

    public Registros getRegistros() {
        Sintomas sintomasPaciente = new Sintomas();
        registro = new CargarRegistrosUI(sintomas,sintomasPaciente,registros);
        return registros;
    }

    public Date getFecha(){
        return fecha;
    }

}
