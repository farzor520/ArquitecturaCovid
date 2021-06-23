import cargarregistros.CargarRegistrosUI;
import cargarsintomas.CargarSintomas;
import cargarsintomas.CargarSintomasUI;
import monitor.Monitor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

     //   ManejoArchivos man = new ManejoArchivos();
      // CargarRegistrosUI covi =  new CargarRegistrosUI();
     //
        //   System.out.println("diagnostico");
       Monitor monitor = new Monitor();
       monitor.monitorear();
        // System.out.println("resultado: " + monitor.getResultado());

    }

}
