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
public class Center {

    int maxConcurrentLlamadas;
    Semaphore CenterResources;

    List<Director> directores = new ArrayList<Director>();
    List<Supervisor> supervisores = new ArrayList<Supervisor>();
    List<Operador> operadores = new ArrayList<Operador>();

    private LinkedList<Integer> llamadas = new LinkedList<Integer>();

    public Center(int cantidadConcurrentLlamadas) {
        this.maxConcurrentLlamadas = cantidadConcurrentLlamadas;
        this.CenterResources = new Semaphore(maxConcurrentLlamadas, true);
    }

    void initCenter() {
        directores.add(new Director("Edgar", CenterResources, directores));
        directores.add(new Director("Marcela", CenterResources, directores));
        directores.add(new Director("Javier", CenterResources, directores));

        supervisores.add(new Supervisor("Jorge", CenterResources, supervisores));
        supervisores.add(new Supervisor("Tomas", CenterResources, supervisores));
        supervisores.add(new Supervisor("Jeromino", CenterResources, supervisores));

        operadores.add(new Operador("Andres", CenterResources, operadores));
        operadores.add(new Operador("Andrea", CenterResources, operadores));
        operadores.add(new Operador("Luisa", CenterResources, operadores));

        Dispatcher dispatcher = new Dispatcher(CenterResources, operadores, supervisores, directores, llamadas);

        for (int i = 0; i < 10; i++) {
            Llamadas llamada = new Llamadas(i, llamadas);
            llamada.start();
        }
    }

    public List<Director> getDirectores() {
        return directores;
    }

    public List<Supervisor> getSupervisores() {
        return supervisores;
    }

    public List<Operador> getOperadores() {
        return operadores;
    }

    public void setDirectores(List<Director> directores) {
        this.directores = directores;
    }

    public void setSupervisores(List<Supervisor> supervisores) {
        this.supervisores = supervisores;
    }

    public void setOperadores(List<Operador> operadores) {
        this.operadores = operadores;
    }

    public Semaphore getCenterResources() {
        return CenterResources;
    }

    public LinkedList<Integer> getLlamadas() {
        return llamadas;
    }
}
