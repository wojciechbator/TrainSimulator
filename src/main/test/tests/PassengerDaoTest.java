package tests;

import org.junit.Before;
import org.junit.Test;
import trainSimulator.models.Passenger;
import trainSimulator.models.Ticket;
import trainSimulator.repository.dao.GenericDao;
import trainSimulator.repository.dao.InMemoryDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public class PassengerDaoTest {

    private GenericDao<Passenger, Integer> userDao = new InMemoryDao<>();

    @Before
    public void setupSomeUsers() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(0, new Ticket());
        tickets.add(1, new Ticket());
        for (int i = 0; i < 10; i++) {
            Passenger passenger = new Passenger();
            userDao.add(passenger);
        }
    }

    @Test
    public void testAdd() {
        int oldSize = userDao.list().size();
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(0, new Ticket());
        Passenger passenger = new Passenger();
        userDao.add(passenger);
        int newSize = userDao.list().size();
        assertFalse(oldSize == newSize);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testList() {
        List<Passenger> passengers = userDao.list();
        assertNotNull(passengers);
        assertFalse(passengers.isEmpty());
    }
}
