import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import trainSimulator.BootApplication;
import trainSimulator.models.Train;
import trainSimulator.repositories.TrainsDaoInterface;
import trainSimulator.services.TrainService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wojciech on 24.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BootApplication.class)
public class UnitTests {
    private TrainService trainService;
    private TrainsDaoInterface trainsDaoInterfaceMock;
    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() {
        trainsDaoInterfaceMock = Mockito.mock(TrainsDaoInterface.class);
        trainService = new TrainService(trainsDaoInterfaceMock);
    }

    @Test
    public void test_setTrainName_shouldReturnTrue() {
	Train train = new Train();
        train.setName("Komancz");
	assertEquals(train.getName(), "Komancz");
    }

    @Test
    public void test_createTrainSuccessfully() {
        Train train = new Train();
        trainService.createTrain(train);
        assertNotNull(trainService.getAllTrains());
    }

    @Test
    public void test_expectingNotWorkingSpringService() {
        Train train = new Train();
        train.setName("Janusz");
        trainService.createTrain(train);
        train.setName("B");
        trainService.saveTrain(train);
        //No trains added, because spring context is not l
        assertEquals(trainService.getAllTrains().size(), 0);
    }

}
