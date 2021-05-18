package sintomas;

import monitor.Sintoma;

public class Contables extends Sintoma {
    private int peso = 10;

    public Contables(String n, int peso) {
        super(n);
        this.peso = peso;
    }

    @Override
    public int peso() {
        return peso;
    }

}
