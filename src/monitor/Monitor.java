package monitor;

import cargarregistros.CargarRegistros;
import cargarsintomas.CargarSintomas;
import diagnosticos.DiagnosticoPorFase;

public class Monitor {

    private Fase fase;
    private Sintomas sintomas;
    private Registros registros;
    private FuncionDiagnostico funcion;
    private int resultadoDiagnostico;
    private CargarRegistros cargarRegistros;

    public Monitor() {

        CargarSintomas cargarSintomas = new CargarSintomas();
        sintomas = cargarSintomas.getSintomas();
        funcion = new DiagnosticoPorFase(sintomas);
        registros = new Registros();
        fase = (new DatosFase()).leerDatosFase();
        cargarRegistros = new CargarRegistros(sintomas.getSintomasFase(fase));
    }


    public void monitorear() {

        registros = cargarRegistros.getRegistros();
        resultadoDiagnostico = funcion.diagnostico(registros);
        mostrarDiaFase(resultadoDiagnostico);
    }

    private void mostrarDiaFase(int resultadoDiagnostico){

        String res = "";
        switch (resultadoDiagnostico) {
            case 11:
                res = "fase 1:\nSe ha iniciado el seguimiento de sus sintomas.\nEs importante que se haga un control diario y visite al medico.";
                break;
            case 12:
                res = "fase 1:\nAnteriormente se le recomendo que visite al medico.\n¿Ha seguido las recomendaciones?";
                break;
            case 13:
                res = "fase 1:\nSe le sugiere visitar a un medico los mas antes posible.";
                break;
            case 19:
                res = "fase 2:\nUsted no realizo el monitoreo correspondiente.\nEs urgente que se realize un monitoreo";
                break;
            case 21:
                res = "fase 2:\nSe ha iniciado el monitoreo de sintomas en segunda fase.\nEs urgente que vaya al medico y se haga una prueba rapida de covid.";
                break;
            case 22:
                res = "fase 2:\nAnteriormente se le recomendo que visite al medico de manera urgente.\nTambien se le recomendo realizarse una prueba rapida de covid.\n¿Ha seguido las recomendaciones?";
                break;
            case 23:
                res = "fase 2:\nAnteriormente se le recomendo que visite al medico de manera urgente.\nTambien se le recomendo realizarse una prueba rapida de covid.\n¿Ha seguido las recomendaciones?";
                break;
            case 24:
                res = "fase 2:\nAnteriormente se le recomendo que visite al medico de manera urgente.\nTambien se le recomendo realizarse una prueba rapida de covid.\n¿Ha seguido las recomendaciones?";
                break;
            case 25:
                res = "fase 2:\nAnteriormente se le recomendo que visite al medico de manera urgente.\nTambien se le recomendo realizarse una prueba rapida de covid.\n¿Ha seguido las recomendaciones?";
                break;
            default:
                res = "No hay la suficiente informacion.\nSi presenta cualquier sintoma de covid use de nuevo este verificador.";
        }
        System.out.println(res);
    }

    public int getResultado() {
        return resultadoDiagnostico;
    }

}
