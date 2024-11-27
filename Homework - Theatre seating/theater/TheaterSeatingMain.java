package theater;

import theater.seating.Seat;
import theater.seating.SeatType;
import theater.TheaterSeating;

public class TheaterSeatingMain{
    public static void main(String[] args) {
        TheaterSeating theater = new TheaterSeating(7, 7);

        String theaterString = theater.toString();
        String theaterSeatTypeString = theater.toSeatTypeString();
        String theaterSeatGiftString = theater.toSeatGiftString();
        int initialAmountOfGifts = theater.getAmountOfGifts();
        String theaterSeatIdString = theater.toSeatIdtString();

        System.out.print(theaterString);
        System.out.println("");
        System.out.print(theaterSeatTypeString);
        System.out.println("");
        System.out.print(theaterSeatGiftString);
        System.out.println("");
        System.out.println("Total number of gifts:");
        System.out.print(initialAmountOfGifts);
        System.out.println("");

/*         Seat bookedSeat = theater.bookSeat();
        theater.bookSeat();
        theater.bookSeat();
        theater.bookSeat(2, 2); */
        System.out.println("Booked seat: ");
        // System.out.println(bookedSeat.getId());
        System.out.println("");
        System.out.print(theaterString);
        System.out.println("");
        theater.bookTailoredEmptySeat(SeatType.MT, true);
        System.out.println("\nBooked seat: ");
        System.out.print(theater.toString());
        theater.bookTailoredEmptySeat(SeatType.IT, true);
        System.out.println("\nBooked seat: ");
        System.out.print(theater.toString());
        theater.bookTailoredEmptySeat(SeatType.OT, true);
        System.out.println("\nBooked seat: ");
        System.out.print(theater.toString());
        theater.bookTailoredEmptySeat(SeatType.MT, false);
        System.out.println("\nBooked seat: ");
        System.out.print(theater.toString());
        theater.bookTailoredEmptySeat(SeatType.IT, false);
        System.out.println("\nBooked seat: ");
        System.out.print(theater.toString());
        theater.bookTailoredEmptySeat(SeatType.OT, false);
        System.out.println("\nBooked seat: ");
        System.out.print(theater.toString());
        System.out.println("");
        System.out.println("");
        System.out.println(theaterSeatIdString);
        System.out.println("");
        System.out.println(theater.toString());
        System.out.println("");
    }
}
