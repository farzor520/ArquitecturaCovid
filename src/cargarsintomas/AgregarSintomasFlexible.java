package cargarsintomas;

import monitor.Sintoma;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AgregarSintomasFlexible {

    public void jar(String[] peligro) throws IOException {
        int cont = 0;
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("sintomas");
        try {
            ZipInputStream zip = new ZipInputStream(new FileInputStream("home.jar"));
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()){
                if(!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/','.');
                    if (className.split("\\.")[0].equals("sintomas")){
                        peligro[cont] = className.split("\\.")[1];
                        cont++;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarFinal(String[] peligro) throws IOException{
        Stack<String> stack = new Stack<>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources("sintomas");
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            File dir = new File(url.getFile());
            int cont = 0;
            for( File f : Objects.requireNonNull(dir.listFiles())){
                String nombreClases = f.getName().split("\\.")[0];
                try{
                    Class.forName("sintomas." + nombreClases).asSubclass(Sintoma.class);
                    String x = (Class.forName("sintomas." + nombreClases).asSubclass(Sintoma.class)).toString().replace("sintomas.", "");
                    peligro[cont] = x.replace("class ", "");
                    cont++;
                    stack.push(nombreClases);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
