package za.ac.cput.service.Civilian;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.cput.EDocketSystem;
import za.ac.cput.domain.Civilian.Witness;
import za.ac.cput.factory.Civilian.WitnessFactory;
import za.ac.cput.service.Civilian.implementation.WitnessServiceImplementation;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = EDocketSystem.class)
@RunWith(SpringRunner.class)
public class WitnessServiceImplementationTest {

    WitnessServiceImplementation service;
    Witness witness;

    @Before
    public void setUp() throws Exception {
        service = WitnessServiceImplementation.getWitnessService();
        witness = WitnessFactory.getWitness("8888", "Ryan", "Petersen", "Mugged");
    }

    @Test
    public void getService() {
        assertNotNull(service);
        System.out.println(service);
    }

    @Test
    public void getAll() {
        service.create(witness);
        assertNotNull(service.getWitnessSet());
        System.out.println("Get All\n" + service.getWitnessSet());
    }

    @Test
    public void create() {
        service.create(witness);
        assertNotNull(service.read("8888"));
        System.out.println("Created\n" + service.read("8888"));
    }

    @Test
    public void read() {
        assertNotNull(service.read("8888"));
        System.out.println("Read\n" + service.read("8888"));
    }

    @Test
    public void update() {
        service.create(witness);
        System.out.println(service.read("8888"));

        Witness witnessUpdated = WitnessFactory.getWitness("8888", "Ty", "Petersen", "Mugged");
        service.update(witnessUpdated);

        Witness wit = service.read("8888");
        Assert.assertNotEquals(witness.getWitnessName(), wit.getWitnessName());
        System.out.println("Updated\n" + service.read("8888"));
    }

    @Test
    public void delete() {
        service.delete("8888");
        assertNull(service.read(witness.getWitnessID()));
        System.out.println("Delete\n" + service.read(witness.getWitnessID()));
    }

}