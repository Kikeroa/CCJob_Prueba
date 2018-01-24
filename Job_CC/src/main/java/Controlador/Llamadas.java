package Controlador;

import java.util.LinkedList;

/**
 * @author EDGAR
 */
public class Llamadas extends Thread {

    Integer id;
    LinkedList<Integer> Llamada;

    public Llamadas(Integer id, LinkedList<Integer> Llamada) {
        this.id = id;
        this.Llamada = Llamada;
    }

    @Override
    public void run() {
        int tiempo = (int) ((Math.random() * (20 - 9)) + 9);

        while (!Llamada.contains(id)) {
            System.out.println("Llamada " + id + " Realizar Llamada");
            Llamada.add(id);
            try {
                sleep(tiempo * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
