package Controlador;

import Modelo.Director;
import Modelo.Operador;
import Modelo.Supervisor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author EDGAR
 */
public class Dispatcher extends Thread {

    static Semaphore SE = new Semaphore(1);

    private List<Director> directores = new ArrayList<Director>();
    private List<Supervisor> supervisores = new ArrayList<Supervisor>();
    private List<Operador> operadores = new ArrayList<Operador>();
    private LinkedList<Integer> Llamadas = new LinkedList<Integer>();
    private Semaphore CenterResources;
    Integer LlamadaId;

    public Dispatcher(Semaphore CenterResources, List<Operador> operadores, List<Supervisor> supervisores, List<Director> directores, LinkedList<Integer> Llamadas) {
        this.CenterResources = CenterResources;
        this.Llamadas = Llamadas;
        this.directores = directores;
        this.operadores = operadores;
        this.supervisores = supervisores;
    }

    public void distribirLlamda() {
        System.out.println("Distribuir Llamada...");
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                SE.acquire();
                if (Llamadas.size() > 0) {
                    CenterResources.acquire();
                } else if (!operadores.isEmpty() || !supervisores.isEmpty() || directores.isEmpty()) {
                    LlamadaId = Llamadas.remove();
                    System.out.println("Tomamdo la Llamada " + LlamadaId);
                } else if (!operadores.isEmpty()) {
                    Operador operador = operadores.remove(0);
                    System.out.println("Asignada la Llamada: " + LlamadaId + operador);
                    operador.start();
                } else if (!supervisores.isEmpty()) {
                    Supervisor supervisor = supervisores.remove(0);
                    System.out.println("Asignada la Llamada: " + LlamadaId + supervisor);
                    supervisor.start();
                } else if (!directores.isEmpty()) {
                    Director director = directores.remove(0);
                    System.out.println("Asignada la Llamada: " + LlamadaId + director);
                    director.start();
                } else {
                    CenterResources.release();
                }
                SE.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
