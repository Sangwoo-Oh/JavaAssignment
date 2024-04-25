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
        "1",
        "2",
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
        "1, 0, 4",
        "1, 2, 4",
    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] original = new int[][]{
            {1, 4, 5},
            {4, 5, 2, 4},
            {2, 4, 1, 0},
            {2, 3}
        };

        WalkingBoard wb = new WalkingBoard(original);

        int[][] actual = wb.getTiles();
        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[i].length; j++) {
                if (original[i][j] < 3) {
                    assertEquals(wb.BASE_TILE_SCORE, actual[i][j]);
                } else {
                    assertEquals(original[i][j], actual[i][j]);
                }
            }
        }

        original[y][x] = 10;
        assertEquals(expected, wb.getTile(x,y));

        // If you modify an element in the return value of getTiles(), and get the value of the respective tile again, this newly received content has to be the originally set value.

        actual[0][0] = 10;
        assertNotEquals(1, wb.getTiles()[0][0]);
    }

    @Test
    public void testMoves() {
        WalkingBoard wb = new WalkingBoard(5);
        for (int i = 0; i < wb.getTiles().length; i++) {
            assertEquals(3, wb.getTile(i,0));
        }
        wb.moveAndSet(Direction.RIGHT, 10);
        wb.moveAndSet(Direction.RIGHT, 10);
        wb.moveAndSet(Direction.RIGHT, 10);
        wb.moveAndSet(Direction.RIGHT, 10);

        //Include a step that tries to move outside of the board.
        //In this case, check that both the position and the board’s contents are unchanged.

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
