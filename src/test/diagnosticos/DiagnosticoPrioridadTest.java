package diagnosticos;

import monitor.*;
import org.junit.jupiter.api.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;



class DiagnosticoPrioridadTest  {

    @Test
    public void registroPruebaTest(){
        int peligroEsperado = 75;
        CargarInfo info = new CargarInfo();
        Registros registros = info.cargarRegistros();
        DiagnosticoPrioridad diagnosticoPrioridad = new DiagnosticoPrioridad(info.getSintomas());
        int peligro = diagnosticoPrioridad.diagnostico(registros);
        assertEquals(peligroEsperado,peligro);
    }

    @Test
    public void registroTest(){
        int peligroEsperado = 75;
        CargarInfo info = new CargarInfo();
        Registros registros = info.cargarRegistros();
        DiagnosticoPrioridad diagnosticoPrioridad = new DiagnosticoPrioridad(info.getSintomas());
        int peligro = diagnosticoPrioridad.diagnostico(registros);
        registros.push(AgregarFecha());
        assertEquals(peligroEsperado,peligro);
    }

    public Registro AgregarFecha(){
        Sintomas sin = new Sintomas();
        Date x = new Date(120,2,2);
        System.out.println(" " + x);
        return new Registro(x ,sin);

    }

}