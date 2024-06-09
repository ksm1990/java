package walking.game;

import walking.game.util.Direction;
import walking.game.WalkingBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WalkingBoardTest {

    @Test
    void testSimpleInit() {
        int size = 5;
        WalkingBoard board = new WalkingBoard(size);
        
        // Inicializálás tesztelés
        assertEquals(size, board.getSize());
        
        // Sorok hosszának tesztelése
        assertEquals(size, board.getTiles().length);
        for (int i = 0; i < size; i++) {
            assertEquals(size, board.getTiles()[i].length);
        }
        
        // Base title score érték tesztelése szélső csempékre
        int baseTileScore = WalkingBoard.BASE_TILE_SCORE;
        assertEquals(baseTileScore, board.getTiles()[0][0]);
        assertEquals(baseTileScore, board.getTiles()[0][size-1]);
        assertEquals(baseTileScore, board.getTiles()[size-1][0]);
        assertEquals(baseTileScore, board.getTiles()[size-1][size-1]);
    }

    @Test // Előre soronként megadott walkingboard értékeinek tesztelése
    void testCustomInit() {
        int x = 2;
        int y = 2;
        int[][] expected = {{1, 2, 3, 4}, {4, 5, 6, 7}, {7, 7, 8, 9}, {7, 8, 9, 10}};
        WalkingBoard board = new WalkingBoard(x, y, expected);
        
        // Méret inicializálsának tesztelése
        assertEquals(4, board.getSize());
        
        // A base title score mint minimum csempe érték érvényesülésének tesztelése
        for (int i = 0; i < 4; i++) {
            assertEquals(4, board.getTiles()[i].length);
            for (int j = 0; j < 4; j++) {
                if (i < expected.length && j < expected[i].length) {
                    assertEquals(expected[i][j], board.getTiles()[i][j]);
                } else {
                    assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTiles()[i][j]);
                }
            }
        }
        
        
        // Csempe érték védettségének tesztelése
        board.getTiles()[0][0] = 999;
        assertEquals(expected[0][0], board.getTiles()[0][0]); // Eredeti érték megmaradásának tesztelése
        
        // Mezők védettségének tesztelése
        int[][] tilesCopy = board.getTiles();
        tilesCopy[1][1] = 10;
        assertEquals(expected[1][1], board.getTiles()[1][1]); // Eredeti csempeérték megmaradásának tesztelése
    }

    @Test
    void testMoves() { // bábu mozgás tesztelése
        WalkingBoard board = new WalkingBoard(5);
        int[][] initialTiles = board.getTiles();
        Direction direction0 = Direction.UP;
        Direction direction1 = Direction.LEFT;
        Direction direction2 = Direction.UP;
        Direction direction3 = Direction.LEFT;
        Direction direction4 = Direction.LEFT;
        Direction direction5 = Direction.DOWN;
        // A bábu mozgásának tesztelése a 0,0 pozicióról a tábláról kifele
        board.moveAndSet(direction0, 42);
        board.moveAndSet(direction1, 42);
        board.moveAndSet(direction2, 42);
        board.moveAndSet(direction3, 42);
        board.moveAndSet(direction4, 42);
        assertArrayEquals(initialTiles, board.getTiles()); // A csempék értéke nem változik
        
        // A bábu mozgásának tesztelése megfelelő irányba
        board.moveAndSet(direction5, 42);; // Lépés lefele, valid irányba
        assertNotEquals(initialTiles, board.getTiles()); // A board értéke különbözik az eredetitől, mivel valid irányba léptünk és új értéket állítottunk be.
    }
}
