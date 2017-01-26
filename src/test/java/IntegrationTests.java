import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import trainSimulator.BootApplication;
import trainSimulator.models.Passenger;
import trainSimulator.models.Train;
import trainSimulator.models.User;
import trainSimulator.services.PassengerService;
import trainSimulator.services.TimetableGeneratorService;
import trainSimulator.services.TrainService;
import trainSimulator.services.UserService;

import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class IntegrationTests
{
    //DEPENDENCY INJECTION
    @Autowired
    @Qualifier("passengerService")
    private PassengerService passengerService;
    @Autowired
    @Qualifier("trainService")
    private TrainService trainService;
    @Autowired
    @Qualifier("timetableGeneratorService")
    private TimetableGeneratorService timetableGeneratorService;
    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Mock
    private Train trainMock;

    @Test
    public void test_userServiceBeanIsInstantiated_shouldReturnTrue() {
        Passenger janusz = passengerService.findById(1);
        assertThat(passengerService, instanceOf(PassengerService.class));
        assertThat(janusz, is(Passenger.class));
        System.out.println("Passenger's name: " + janusz.getName() + ", and id: " + janusz.getId() + "\n");
    }

    @Test
    public void test_trainServiceBeanIsInstantiated_shouldReturnTrue() {
        timetableGeneratorService.generateTimetable();
        assertThat(trainService, instanceOf(TrainService.class));
        List<Train> trains = trainService.getAllTrains();
        assertNotNull(trains);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_creatingTwoUsersWithSameUsername_shouldReturnException() {
        User firstUser = new User();
        firstUser.setName("Janusz");
        firstUser.setPassword("Janusz");
        //The list of users is not initialized with the start of the application, thus I expect this exception
        userService.saveUser(firstUser);
    }

    @Test
    public void test_addMockTrain_shouldReturnTrue() {
        trainService.saveTrain(trainMock);
        assertNotNull(trainService.getAllTrains());
    }


}