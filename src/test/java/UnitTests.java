import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trainSimulator.models.Ticket;
import trainSimulator.models.User;
import trainSimulator.services.TicketService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by wojciech on 24.01.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTests {
    @Mock
    private Ticket ticketMock;

    @InjectMocks
    private TicketService ticketService;

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
}
