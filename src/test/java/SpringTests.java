import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import trainSimulator.BootApplication;
import trainSimulator.configuration.PersistenceConfiguration;
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
@ContextConfiguration(classes = {PersistenceConfiguration.class, BootApplication.class})
public class SpringTests
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

    @Before
    public void prepareTrainsForTests() {
        timetableGeneratorService.generateTimetable();
    }

    @Test
    public void test_userServiceBeanIsInstantiated_shouldReturnTrue() {
        Passenger janusz = passengerService.findById(1);
        assertThat(passengerService, instanceOf(PassengerService.class));
        assertThat(janusz, is(Passenger.class));
        System.out.println("Passenger's name: " + janusz.getName() + ", and id: " + janusz.getId() + "\n");
    }

    @Test
    public void test_trainServiceBeanIsInstantiated_shouldReturnTrue() {
        assertThat(trainService, instanceOf(TrainService.class));
        List<Train> trains = trainService.getAllTrains();
        assertNotNull(trains);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void test_creatingTwoUsersWithSameUsername_ShouldReturnException() {
        User firstUser = new User();
        firstUser.setName("Janusz");
        firstUser.setPassword("Janusz");
        //The list of users is not initialized with the start of the application, thus I expect this exception
        userService.saveUser(firstUser);
    }
}