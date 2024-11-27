package theater;

import theater.seating.Seat;
import theater.seating.SeatType;

public class TheaterSeating {
    private Seat[][] seats;
    private int giftsTotal;

    // constructor
    public TheaterSeating (int rowCount, int colCount) {
        if (rowCount < 0 || colCount < 0) {
            throw new IllegalArgumentException("Row and column count must be positive whole numbers.");
        }
        this.seats = new Seat[rowCount][colCount];
        this.giftsTotal = 0;
        initSeating(rowCount, colCount);
    }

    // getSeats
    public Seat[][] getSeats() {
        return this.seats;
    }

    private void initSeating( int rowCount, int colCount) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {

                String id = Integer.toString(i) + "-" + Integer.toString(j);
                boolean hasGift = false;
                boolean otCondition = false;
                boolean mtCondition = false;
                boolean itCondition = false;
                SeatType seatType = SeatType.IT;

                // gift condition
                if ((i + j) % 2 == 1) {
                    hasGift = true;
                    this.giftsTotal ++;
                } else hasGift = false;

                // OT condition
                if ((i == 0 || i == rowCount-1) || (j == 0 || j == colCount-1)) {
                        otCondition = true;
                        seatType = SeatType.OT;
                } else otCondition = false;

                // MT condition
                if (colCount % 2 == 1 && j == (colCount-1) / 2 && i != 0 && i != rowCount - 1) {
                    mtCondition = true;
                    seatType = SeatType.MT;
                } else mtCondition = false;
                
                // IT condition
                if (otCondition == false && mtCondition == false) {
                    itCondition = true;
                    seatType = SeatType.IT;
                } else itCondition = false;
                
                this.seats[i][j] = new Seat(id, hasGift, seatType);

            }
        }
    }

    public Seat bookSeat() {
        Seat resultSeat = null;
        outer:
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                if (this.seats[i][j].getIsOccupied() == false) {
                    this.seats[i][j].setIsOccupied(true);
                    resultSeat = this.seats[i][j];
                    break outer;
                }
            }
        }
        return resultSeat;
    }

    public Seat bookSeat(int row, int col) {
        if ( row > this.seats.length || col > this.seats[0].length) {
            throw new IllegalArgumentException("Seat does not exist.");
        }
        if ( row < 0 || col < 0) {
            throw new IllegalArgumentException("Seat row and col must be a positive whole number.");
        }
        if (this.seats[row][col].getIsOccupied() == true) {
            throw new IllegalArgumentException("Seat is already booked.");
        } else {
            this.seats[row][col].setIsOccupied(true);
            return this.seats[row][col];
        }
    }

    public Seat bookTailoredEmptySeat(SeatType type, boolean leftPreferance) {
        int maxColIndex = 0;
        int minColIndex = 0;
        Seat resultSeat = null;
        if (leftPreferance == true) {
            maxColIndex = this.seats[0].length/2 + 1;
            minColIndex = 0;
            outer:
            for (int i = 0; i < this.seats.length; i++) {
                for (int j = minColIndex; j < maxColIndex; j++) {
                    if (this.seats[i][j].getSeatType() == type) {
                        if (this.seats[i][j].getIsOccupied() == false){
                            this.seats[i][j].setIsOccupied(true);
                            resultSeat = this.seats[i][j];
                            break outer;
                        }
                    }
                }
            }
        } else {
            minColIndex = this.seats[0].length/2 + 1;
            maxColIndex = this.seats[0].length - 1;
            outer:
            for (int i = 0; i < this.seats.length; i++) {
                for (int j = maxColIndex; j > minColIndex; j--) {
                    if (this.seats[i][j].getSeatType() == type) {
                        if (this.seats[i][j].getIsOccupied() == false){
                            this.seats[i][j].setIsOccupied(true);
                            resultSeat = this.seats[i][j];
                            break outer;
                        }
                    }
                }
            }
        }

        
        return resultSeat;
    }

    public void decreaseGifts() {
        if (this.giftsTotal > 0) {
            this.giftsTotal--;
        }
    }

    public int getAmountOfGifts() {
        int giftCounter = 0;
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                if (this.seats[i][j].getHasGift() == true) {
                    giftCounter = giftCounter + 1;
                }
            }
        }
        this.giftsTotal = giftCounter;
        return giftCounter;
    }

    public int totalTakenGifts() { // returns the number of remaining gifts in the theater
        int giftCounter = 0;
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                if (this.seats[i][j].getHasGift() == true) {
                    giftCounter = giftCounter + 1;
                }
            }
        }
        return (this.giftsTotal - giftCounter);
    }

    //@Override
    public String toSeatTypeString() {
        String result = "";
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                result = result + "[" + seats[i][j].getSeatType() + "]" + " ";
            }
            if (i < this.seats.length -1 ) {
                result = result + "\n";
            }
        }
        return result;
    }

    public String toSeatGiftString() {
        String result = "";
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                result = result + "[" + seats[i][j].getHasGift() + "]" + " ";
            }
            if (i < this.seats.length -1 ) {
                result = result + "\n";
            }
        }
        return result;
    }
    
    public String toSeatIdtString() {
        String result = "";
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                result = result + "[ " + seats[i][j].getId() + " ]" + " ";
            }
            if (i < this.seats.length -1 ) {
                result = result + "\n";
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.seats.length; i++) {
            for (int j = 0; j < this.seats[i].length; j++) {
                if (seats[i][j].getIsOccupied() == false) {
                    result = result + "[A]" + " ";
                } else {result = result + "[B]" + " ";}                
            }
            if (i < this.seats.length -1 ) {
                result = result + "\n";
            }
        }
        return result;
    }

}