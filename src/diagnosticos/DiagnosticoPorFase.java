package diagnosticos;

import monitor.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class DiagnosticoPorFase extends FuncionDiagnostico {

    private Map<Sintoma,String> sintomasDeUnaFase;
    private Map<String,Integer> nroSintomasDeCadaFase;
    int cantSintomasF1;
    int cantSintomasF2;
    String fechaAnterior = "";
    int controlFecha = 0;

    public DiagnosticoPorFase(Sintomas ls) {
        super(ls);
        cargarFaseSintomasMonitor(ls);

        for (Sintoma s : ls) {
            if (s.getClass().getName() == "sintomas.PrimeraFase") {

                cantSintomasF1++;
            }else {
                cantSintomasF2++;
            }
        }
    }

    @Override
    public int diagnostico(Registros registros) {
        int tam =0;
        int fase1=0, fase2=0;
        int dia=0;
        int contDia = 0;
        int falto = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Registro r: registros) {
            if (r.getFecha()!=null){

           String fechaString= format.format (r.getFecha());
            for (Sintoma s: r.getSintomas()){
                tam++;
            }

            if (tam >= cantSintomasF1/2 && fase1 < 3 && calcularFecha(fechaString,fechaAnterior)){
                fase1++;
                tam = 0;
                falto=0;
                contDia++;
                fechaAnterior = fechaString;
            }else if (fase1 >= 3 && tam >= cantSintomasF2/2 && calcularFecha(fechaString,fechaAnterior)){
                fase2++;
                tam = 0;
                contDia++;
                falto=0;
                fechaAnterior = fechaString;
            }else if (fase1 < 3 && fase2 == 0 ){
                fase1 = 0;
                falto=0;
                contDia++;
                fechaAnterior = fechaString;
            }else if (fase1 >= 3 && tam <= cantSintomasF2/2 && !calcularFecha(fechaString,fechaAnterior)){
                fase2 = 1;
                falto = 2;
                contDia++;
                fechaAnterior = fechaString;
            }
            }
        }

        DatosFase datosFase = new DatosFase();
        Fase fase = new Fase("PrimeraFase");
        if (fase2>0) {
            dia = 20+fase2;
            fase.setNombre("SegundaFase");
        } else if (fase1>0) {
            dia = 10+fase1;
            fase.setNombre("PrimeraFase");
        }

        datosFase.guardarDatosFase(fase);
        System.out.println(dia);

        System.out.println("Dia Numero:" + contDia);
        if (falto == 0){
            return dia;
        }else {
            return 19;
        }

    }

    private boolean calcularFecha( String fechaString,String fechaAnterior) {
        int numberActual = 0;
        int numberAnterior = 0;
        String p = fechaString.substring(fechaString.length()-2);
        String a = "";
        if (controlFecha != 0) {
            a = fechaAnterior.substring(fechaAnterior.length() - 2);
            numberAnterior = Integer.parseInt(a);
        }
        numberActual = Integer.parseInt(p);

        if (controlFecha != 0){
            if (numberAnterior+1 == numberActual){
                return true;
            }
        }else if (controlFecha == 0){
            controlFecha++;
            return true;
        }
        return false;
    }

    private String dateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private void cargarFaseSintomasMonitor(Sintomas ls) {
        sintomasDeUnaFase = new HashMap<>();
        nroSintomasDeCadaFase = new HashMap<>();
        nroSintomasDeCadaFase.put("PrimeraFase",0);
        nroSintomasDeCadaFase.put("SegundaFase",0);

        for (Sintoma s: ls) {
            String nombre = s.getClass().getName().split("\\.")[1];
            sintomasDeUnaFase.put(s,nombre);
            nroSintomasDeCadaFase.put(nombre, nroSintomasDeCadaFase.get(nombre)+1);
        }
    }
}