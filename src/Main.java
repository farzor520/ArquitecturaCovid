import cargarregistros.CargarRegistrosUI;
import cargarsintomas.CargarSintomasUI;
import monitor.Monitor;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
       CargarSintomasUI covid = new CargarSintomasUI();
        CargarRegistrosUI covi =  new CargarRegistrosUI();

        //   System.out.println("diagnostico");
       Monitor monitor = new Monitor();
      //  monitor.monitorear();
     //   System.out.println("resultado: " + monitor.getResultado());
      //  TimeUnit.SECONDS.sleep(78);
      //  Monitor monitor1 = new Monitor();
      //  monitor.monitorear();
      //  System.out.println("resultado: " + monitor.getResultado());
      //  PruebaSet.prueba();
    }

}
