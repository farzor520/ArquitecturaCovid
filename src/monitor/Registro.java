package monitor;

import monitor.Sintomas;

import java.util.Date;

public class Registro {

    private Date fecha;
    private Sintomas sintomas;

    public Registro(Date f, Sintomas s) {
        fecha = f;
        sintomas = s;
    }

    public Sintomas getSintomas() {
        return sintomas;
    }

}
