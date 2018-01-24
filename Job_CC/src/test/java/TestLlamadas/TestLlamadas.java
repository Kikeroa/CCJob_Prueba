
package TestLlamadas;

import Controlador.Dispatcher;
import Controlador.Llamadas;
import Controlador.Center;
import Modelo.Director;
import Modelo.Operador;
import Modelo.Supervisor;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EDGAR
 */
public class TestLlamadas {
    
@Test
    public void testDispatch10CallsOk(){
        Center callCenter = new Center(10);

        List<Director> directores = new ArrayList<Director>();
        List<Supervisor> supervisores = new ArrayList<Supervisor>();
        List<Operador> operadores = new ArrayList<Operador>();

        directores.add(new Director("Edgar",callCenter.getCenterResources(), directores));
        directores.add(new Director("Marcela",callCenter.getCenterResources(), directores));
        directores.add(new Director("Tomas",callCenter.getCenterResources(), directores));
        directores.add(new Director("Jeronimo",callCenter.getCenterResources(), directores));

        supervisores.add(new Supervisor("Gilma", callCenter.getCenterResources(), supervisores));
        supervisores.add(new Supervisor("Elibrando", callCenter.getCenterResources(), supervisores));


        operadores.add(new Operador("Daniel", callCenter.getCenterResources(), operadores));
        operadores.add(new Operador("Laura", callCenter.getCenterResources(), operadores));
        operadores.add(new Operador("Cristian", callCenter.getCenterResources(), operadores));
        operadores.add(new Operador("Manuel", callCenter.getCenterResources(), operadores));


        callCenter.setDirectores(directores);
        callCenter.setOperadores(operadores);
        callCenter.setSupervisores(supervisores);

        Dispatcher dispatcher = new Dispatcher(callCenter.getCenterResources(), operadores, supervisores, directores, callCenter.getLlamadas());


        dispatcher.distribirLlamda();
        Assert.assertEquals(callCenter.getCenterResources().availablePermits(), 10);


        for (int i = 0; i < 14; i++) {
            Llamadas llam = new Llamadas(i, callCenter.getLlamadas());
            llam.start();
        }

        
        Assert.assertEquals(callCenter.getCenterResources().availablePermits(), 0);
    }

    @Test
    public void testOnlyOperatorsTakeCalls(){
        Center callCenter = new Center(2);

        List<Director> directores = new ArrayList<Director>();
        List<Supervisor> supervisores = new ArrayList<Supervisor>();
        List<Operador> operadores = new ArrayList<Operador>();

        directores.add(new Director("Anna",callCenter.getCenterResources(), directores));

        supervisores.add(new Supervisor("Maria", callCenter.getCenterResources(), supervisores));

        operadores.add(new Operador("Cristian", callCenter.getCenterResources(), operadores));
        operadores.add(new Operador("Manuel", callCenter.getCenterResources(), operadores));
        operadores.add(new Operador("Lucas", callCenter.getCenterResources(), operadores));

        callCenter.setDirectores(directores);
        callCenter.setOperadores(operadores);
        callCenter.setSupervisores(supervisores);

        Dispatcher dispatcher = new Dispatcher(callCenter.getCenterResources(), operadores, supervisores, directores, callCenter.getLlamadas());

        dispatcher.distribirLlamda();

        Assert.assertEquals(callCenter.getCenterResources().availablePermits(), 2);

        for (int i = 0; i < 2; i++) {
            Llamadas llam = new Llamadas(i, callCenter.getLlamadas());
            llam.start();
        }

        Assert.assertEquals(callCenter.getDirectores().size(), 1);
        Assert.assertEquals(callCenter.getSupervisores().size(), 1);
    }
}
