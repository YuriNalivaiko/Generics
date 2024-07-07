import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class AviaSoulsTest {

    private AviaSouls aviaSouls;

    @BeforeEach
    public void setUp() {
        aviaSouls = new AviaSouls();
        aviaSouls.add(new Ticket("MOW", "NYC", 1000, 800, 1200));
        aviaSouls.add(new Ticket("MOW", "NYC", 1500, 900, 1300));
        aviaSouls.add(new Ticket("MOW", "PAR", 2000, 700, 1100));
        aviaSouls.add(new Ticket("MOW", "NYC", 800, 600, 1000));
    }

    @Test
    public void testSearchAndSortByPrice() {
        Ticket[] tickets = aviaSouls.search("MOW", "NYC");
        Assertions.assertEquals(3, tickets.length);
        Assertions.assertTrue(tickets[0].getPrice() < tickets[1].getPrice());
        Assertions.assertTrue(tickets[1].getPrice() < tickets[2].getPrice());
    }

    @Test
    public void testSearchAndSortByTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] tickets = aviaSouls.searchAndSortBy("MOW", "NYC", comparator);
        Assertions.assertEquals(3, tickets.length);
        Assertions.assertTrue(
                (tickets[0].getTimeTo() - tickets[0].getTimeFrom()) <=
                        (tickets[1].getTimeTo() - tickets[1].getTimeFrom())
        );
        Assertions.assertTrue(
                (tickets[1].getTimeTo() - tickets[1].getTimeFrom()) <=
                        (tickets[2].getTimeTo() - tickets[2].getTimeFrom())
        );
    }
}