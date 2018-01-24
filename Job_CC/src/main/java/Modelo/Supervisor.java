package Modelo;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author EDGAR
 */
public class Supervisor extends Thread implements TiposEmpleado {

    String nombre;
    private Semaphore CenterResources;
    private List<Supervisor> empleados;

    public Supervisor(String nombre, Semaphore CenterResources, List<Supervisor> empleados) {
        this.nombre = nombre;
        this.CenterResources = CenterResources;
        this.empleados = empleados;
    }

    
    
    @Override
    public void contentarCall() {
        System.out.println("Supervisor " + nombre + " Contestar Llamada...");
    }

    @Override
    public void FinalCall() {
        System.out.println("Supervisor " + nombre + " Final de la Llamada...");
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
