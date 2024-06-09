package walking.game;

import walking.game.util.Direction;
import java.util.Arrays;

public class WalkingBoard{
    private int x;
    private int y;
    private int[][] tiles;
    private int size;
    public static final int BASE_TILE_SCORE = 3;

    public WalkingBoard(int size) {
        x = 0;
        y = 0;
        this.size = size;
        this.tiles = new int[size][size];
        initializeBoard();
    }

    public int getSize(){
        return this.size;
    }

    public WalkingBoard(int[][] predefinedBoard) {
        x = 0;
        y = 0;
        this.size = predefinedBoard.length;
        this.tiles = new int[size][];
        for (int i = 0; i < size; i++) {
            this.tiles[i] = Arrays.copyOf(predefinedBoard[i], predefinedBoard[i].length);
        }
    }

    public WalkingBoard(int x, int y, int[][] predefinedBoard) {
        this.x = x;
        this.y = y;
        this.size = predefinedBoard.length;
        this.tiles = new int[size][];
        for (int i = 0; i < size; i++) {
            this.tiles[i] = Arrays.copyOf(predefinedBoard[i], predefinedBoard[i].length);
        }
    }

    public int[][] getTiles() { // getter a tiles-nak
        int rowCount = size;
        int[][] copyOfTiles = new int[rowCount][];
        for (int i = 0; i < rowCount; i++){
            copyOfTiles[i] = new int[tiles[i].length];
        }
        for (int i = 0; i < size; i++){
            for (int j = 0; j < tiles[i].length; j++){
                copyOfTiles[i][j] = tiles[i][j];
            }
        }
        return copyOfTiles;
    }

    public int[] getPosition() {
        int cell_counter = 0;
        int[] values_of_tiles = new int[200];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < tiles[i].length; j++){
                values_of_tiles[i + j] = tiles[i][j];
                cell_counter++;
            }
        }
        return values_of_tiles;
    }

    public boolean isValidPosition(int x, int y) {
        if (x < size && x > 0){
            if (y < tiles[x].length && y > 0) {
                return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    public int getTile(int x, int y) {
        if (!isValidPosition(x, y)) {
            throw new IllegalArgumentException("Invalid position");
        }
        return tiles[x][y];
    }

    public static int getXStep(Direction direction) { // itt nem kéne inputnak egy tile is?
        if ((direction == direction.UP) || (direction == direction.DOWN)){
            return 0;
        }
        if (direction == direction.RIGHT){
            return 1;
        }
        if (direction == direction.LEFT){
            return -1;
        }
        else {return 0;}
    }

    public static int getYStep(Direction direction) {
        if ((direction == direction.RIGHT) || (direction == direction.LEFT)){
            return 0;
        }
        if (direction == direction.UP){
            return 1;
        }
        if (direction == direction.DOWN){
            return -1;
        }
        else {return 0;}
    }

    public int moveAndSet(Direction direction, int newValue){
        int h = getXStep(direction);
        int v = getYStep(direction);                
        if (isValidPosition((x + h),(y + v))) {
            x = x + h;
            y = y + v;
            int oldValue = getTile(x, y);
            tiles[x][y] = newValue;
            return oldValue;
        }
        else return 0;
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = Math.max(BASE_TILE_SCORE, 0); // Ensure value is at least baseTileScore
            }
        }
    }
}