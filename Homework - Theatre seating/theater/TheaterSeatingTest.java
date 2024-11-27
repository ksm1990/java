package theater;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import theater.*;
import theater.seating.*;

public class TheaterSeatingTest {

/*     @BeforeAll */

    @Test
    public void testInitialization() {
        TheaterSeating seating = new TheaterSeating(4, 5);
        // testing theater dimensions
        assertEquals(seating.getSeats().length, 4);
        assertEquals(seating.getSeats()[0].length, 5);
        int rowCount = seating.getSeats().length;
        int colCount = seating.getSeats()[0].length;
        // testing theater seating types
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                boolean otCondition = false;
                boolean mtCondition = false;
                boolean itCondition = false;
                // check OT condition seats
                if ((i == 0 || i == rowCount-1) || (j == 0 || j == colCount-1)) {
                    otCondition = true;
                    assertEquals(seating.getSeats()[i][j].getSeatType(), theater.seating.SeatType.OT);
                }
                // check MT condition seats
                if (colCount % 2 == 1 && j == (colCount-1) / 2 && i != 0 && i != rowCount - 1) {
                    mtCondition = true;
                    assertEquals(seating.getSeats()[i][j].getSeatType(), theater.seating.SeatType.MT);
                }
                // check IT condition seats
                if (otCondition == false && mtCondition == false) {
                    assertEquals(seating.getSeats()[i][j].getSeatType(), theater.seating.SeatType.IT);
                }
            }
        }
    }

    public void testGiftsInitialization(int rows, int cols, int expectedGifts) {
        // testing theater gifts arrangement
        TheaterSeating seating = new TheaterSeating(rows, cols);
        assertEquals(seating.getAmountOfGifts(), expectedGifts);
    }

    @Test
    public void testGiftsInitialization() {
        testGiftsInitialization(6, 7, 21);
        testGiftsInitialization(10, 10, 50);
    }

    @Test
    public void testBookSeat() {
        TheaterSeating seating = new TheaterSeating(10, 10);
        assertEquals(seating.getSeats()[0][0].getIsOccupied(), false);
        seating.bookSeat();
        assertEquals(seating.getSeats()[0][0].getIsOccupied(), true);
        assertThrows(IllegalArgumentException.class, () -> {
            seating.bookSeat(0,0
            );}
        );
    }

    @Test
    public void testBookTailoredEmptySeat() {
        TheaterSeating seating = new TheaterSeating(7, 7);
        assertEquals(seating.getSeats()[0][0].getIsOccupied(), false);
        assertEquals(seating.getSeats()[1][1].getIsOccupied(), false);
        assertEquals(seating.getSeats()[1][3].getIsOccupied(), false);
        // test OT
        seating.bookTailoredEmptySeat(SeatType.OT, true);
        assertEquals(seating.getSeats()[0][0].getIsOccupied(), true);
        // test IT
        seating.bookTailoredEmptySeat(SeatType.IT, true);
        assertEquals(seating.getSeats()[1][1].getIsOccupied(), true);
        // test MT
        seating.bookTailoredEmptySeat(SeatType.MT, true);
        assertEquals(seating.getSeats()[1][3].getIsOccupied(), true);
    }

    @Test
    public void testText() {
        TheaterSeating seating = new TheaterSeating(4, 4);
        String expectedResultString = "[A] [A] [A] [A] \n[A] [A] [A] [A] \n[A] [A] [A] [A] \n[A] [A] [A] [A] ";
        assertEquals(seating.toString(), expectedResultString);
    }
}