import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import trainSimulator.BootApplication;
import trainSimulator.configuration.PersistenceConfiguration;
import trainSimulator.models.Station;
import trainSimulator.models.Ticket;
import trainSimulator.models.Train;
import trainSimulator.models.User;
import trainSimulator.services.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by wojciech on 24.01.17.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class, BootApplication.class})
public class UnitTests {
    @Mock
    private Ticket ticketMock;
    @Mock
    private Train trainMock;
    @InjectMocks
    private TicketService ticketService;
    @InjectMocks
    private StationService stationService;
    @InjectMocks
    private TrainService trainService;

    @Test
    public void test_MockOwnerForTicket_shouldReturnTrue() {
        User owner = new User();
        owner.setName("Janusz");
        when(ticketMock.getUser()).thenReturn(owner);
        ticketService.setOwnerForTicket(owner, ticketMock);
        User actualOwner = ticketService.getOwnerOfTicket(ticketMock);
        assertEquals(actualOwner.getName(), "Janusz");
        assertEquals(owner, actualOwner);
    }

    @Test
    public void test_MockTrainForStation_shouldReturnTrue() {
        Station station = new Station();
        station.setName("Wroclaw");
        when(trainMock.getStation()).thenReturn(station);
        assertNotNull(station.getTrainsOnStation());
        assertEquals(station.getTrainsOnStation(), stationService.getTrainsOnStation());
    }

    //Null pointer exception, because no spring context is initialized
    @Test(expected = NullPointerException.class)
    public void test_trainServiceUnitTest_shouldReturnNullPointer() {
        Train train = new Train();
        trainService.saveTrain(train);
        assertNotNull(trainService.getAllTrains());
    }

}
