package theater.seating;

import theater.seating.SeatType;

public class Seat {
    private final String id;
    private boolean hasGift;
    private final SeatType seatType;
    private boolean isOccupied;

    // Seat constructor
    public Seat (String id, boolean hasGift, SeatType seatType) {
        this.id = id;
        this.hasGift = hasGift;
        this.seatType = seatType;
        this.isOccupied = false;
    }

    // id getter
    public String getId() {
        return this.id;
    }

    // hasGift getter
    public boolean getHasGift() {
        return this.hasGift;
    }

    // hasGift setter
    public void setHasGift(boolean newHasGift) {
        this.hasGift = newHasGift;
    }

    // seatType getter
    public SeatType getSeatType() {
        return this.seatType;
    }

    // isOccupied getter
    public boolean getIsOccupied() {
        return this.isOccupied;
    }

    // isOccupied setter
    public void setIsOccupied(boolean newIsOccupied) {
        this.isOccupied = newIsOccupied;
    }
}