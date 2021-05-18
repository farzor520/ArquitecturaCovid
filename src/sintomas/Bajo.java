package sintomas;

import monitor.Sintoma;

public class Bajo extends Sintoma {

    public Bajo(String n) {
        super(n);
    }

    @Override
    public int peso() {
        return 5;
    }

}
