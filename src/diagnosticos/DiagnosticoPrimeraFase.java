package diagnosticos;

import monitor.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DiagnosticoPrimeraFase extends FuncionDiagnostico {

    int cantSintomasF1 = 0;
    int cantSintomasF2 = 0;
    boolean primera = true;
    int dias = 0;
    Sintomas sintomasFiltro = new Sintomas();

    public DiagnosticoPrimeraFase(Sintomas ls) {
        super(ls);

        for (Sintoma s : ls) {
            if (s.getClass().getName() == "sintomas.PrimeraFase") {
                cantSintomasF1++;
            }else {
                cantSintomasF2++;
            }
        }

        dias = leer();

        if (dias<=2) {
            for (Sintoma s : ls) {
                if (s.getClass().getName() == "sintomas.PrimeraFase") {
                    sintomasFiltro.add(s);
                }
            }
        } else if (dias>=3) {
            for (Sintoma s : ls) {
                if (s.getClass().getName() == "sintomas.SegundaFase") {
                    sintomasFiltro.add(s);
                }
            }
        }


    }


    @Override
    public int diagnostico(Registros registros) {
        revizarExistenciaArchivo();

        int tamF = 0;

        try {
            Registro registro = registros.peek();
            Sintomas sintomas = registro.getSintomas();
            for (Sintoma s: sintomas) {
                tamF ++;
            }
        }catch (NullPointerException ex){

        }


        if (leer()<3) {
            if (tamF >= cantSintomasF1 / 2) {
                mayor50("primera");
            } else {
                mayor50("inicio");
            }
        }
        else if (tamF >= cantSintomasF2 / 2){
            mayor50("segunda");
        }
        return tamF;
    }

    private int leer() {

        int diasA = 0;
        try {

            FileInputStream fis = new FileInputStream("diagnostico.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                if(!sc.nextLine().equals("inicio")){
                    diasA++;
                }else {
                    diasA = 0;
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diasA;
    }

    private void mayor50(String primera) {

        try {
            FileWriter myWriter = new FileWriter("diagnostico.txt",true);
            myWriter.write(primera + "\n");
            myWriter.flush();
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void revizarExistenciaArchivo() {

        File file = new File("diagnostico.txt");
        try {
            if (file.createNewFile()) {

            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Sintomas regresarSintomas(){

       return sintomasFiltro;

    }



}