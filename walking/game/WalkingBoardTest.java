package walking.game;

import walking.game.util.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WalkingBoardTest {
    @ParameterizedTest
    @CsvSource({
        "0",
        "3",
        "10"
    })
    public void testSimpleInit(int size) {
        WalkingBoard wb = new WalkingBoard(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertEquals(wb.BASE_TILE_SCORE, wb.getTiles()[i][j]);
            }
        }
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 3",
        "1, 0, 3",
        "1, 2, 8",
    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] givenTiles = new int[][]{
            {1,2},
            {3,4,5,6},
            {7,8,9},
        };
        // On positions where values smaller than three were passed, the board contains the value BASE_TILE_SCORE.
        WalkingBoard wb = new WalkingBoard(givenTiles);
        assertEquals(expected, wb.getTile(x,y));

        //If you pass an array to the constructor and later modify a value in it, the respective tile retains the originally passed value.
        for (int i = 0; i < givenTiles.length; i++) {
            for (int j = 0; j<givenTiles[i].length; j++) {
                givenTiles[i][j] = givenTiles[i][j] + 1;
            }
        }

        for (int i = 0; i < wb.getTiles().length; i++) {
            for (int j = 0; j<wb.getTiles()[i].length; j++) {
                if (givenTiles[i][j] - 1 < 3)  assertEquals(wb.BASE_TILE_SCORE, wb.getTile(j,i));
                else assertEquals(givenTiles[i][j] - 1, wb.getTile(j,i));
            }
        }

        // If you modify an element in the return value of getTiles(), and get the value of the respective tile again, this newly received content has to be the originally set value.
        int[][] retTiles = wb.getTiles();
        retTiles[0][0] = 10;
        assertEquals(3, wb.getTile(0,0));
    }

    @Test
    // testMoves(): take four or five steps and check that the board’s contents are changed just right.Include a step that tries to move outside of the board. In this case, check that both the position and the board’s contents are unchanged.
    public void testMoves() {
        WalkingBoard wb = new WalkingBoard(5);
        for (int i = 0; i < wb.getTiles().length; i++) {
            assertEquals(3, wb.getTile(i,0));
        }
        wb.moveAndSet(Direction.RIGHT, 10);
        wb.moveAndSet(Direction.RIGHT, 10);
        wb.moveAndSet(Direction.RIGHT, 10);
        wb.moveAndSet(Direction.RIGHT, 10);

        assertEquals(3, wb.getTile(0,0));
        for (int i = 1; i < wb.getTiles().length; i++) {
            assertEquals(10, wb.getTile(i,0));
        }

        assertEquals(3, wb.getTile(0,4));
        wb.moveAndSet(Direction.RIGHT, 10);
        assertEquals(3, wb.getTile(0,4));
        assertArrayEquals(new int[] {4,0}, wb.getPosition());
    }
}
