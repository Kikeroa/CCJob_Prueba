package Modelo;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author EDGAR
 */
public class Operador extends Thread implements TiposEmpleado {

    private String nombre;
    private Semaphore CenterResources;
    private List<Operador> empleados;

    public Operador(String nombre, Semaphore CenterResources, List<Operador> empleados) {
        this.nombre = nombre;
        this.CenterResources = CenterResources;
        this.empleados = empleados;
    }

      
    
    @Override
    public void contentarCall() {
        System.out.println("Operador " + nombre + " Contestar Llamada...");
    }

    @Override
    public void FinalCall() {
        System.out.println("Operador " + nombre + " Final de la Llamada...");
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
