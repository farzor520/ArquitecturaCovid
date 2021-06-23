package diagnosticos;

import monitor.*;

import java.util.HashMap;
import java.util.Map;

public class DiagnosticoPrioridad extends FuncionDiagnostico {

    private Map<Sintoma,Integer> pesos;


    public DiagnosticoPrioridad(Sintomas ls) {
        super(ls);
        pesos = new HashMap<>();
        for (Sintoma s: ls) {
            pesos.put(s,s.peso());
        }
    }

    @Override
    public int diagnostico(Registros registros) {
        int pesoSintomas = 0;
        if (!registros.isEmpty()) {
            Registro registro = registros.peek();
            Sintomas sintomas = registro.getSintomas();
            //System.out.println("fecha: " + registro.getFecha());
            for (Sintoma s: sintomas) {
                pesoSintomas += pesos.get(s);
            }
        }

        return pesoSintomas;
    }

    public int cambiarPesoMedio(){
        int x = 10;
       return x;
    }

}
