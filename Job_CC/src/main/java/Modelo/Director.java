package Modelo;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author EDGAR
 */
public class Director extends Thread implements TiposEmpleado {

    public String nombre;
    private Semaphore CenterResources;
    private List<Director> empleados;

    public Director(String nombre, Semaphore CenterResources, List<Director> empleados) {
        this.nombre = nombre;
        this.CenterResources = CenterResources;
        this.empleados = empleados;
    }
    
    
    @Override
    public void contentarCall() {
        System.out.println("Director " + nombre + "Contentando Llamada");
    }

    @Override
    public void FinalCall() {
        System.out.println("Director " + nombre + "Final de la Llamada");
    }

    @Override
    public void run() {
        int tiempo = (int) ((Math.random() * (20 - 9)) + 9);
        this.contentarCall();

        try {
            sleep(tiempo * 1000);
            FinalCall();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
