package sintomas;

import monitor.Sintoma;

public class Critico extends Sintoma {

    public Critico(String n) {
        super(n);
    }

    @Override
    public int peso() {
        return 30;
    }
}
