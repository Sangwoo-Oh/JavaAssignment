package walking.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        "1, 0, 10",
    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] original = new int[][]{
            new int[]{1, 4, 5},
            new int[]{4, 5, 2, 4},
            new int[]{2, 4, 1, 0},
            new int[]{2, 3}
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

        // If you pass an array to the constructor and later modify a value in it, the respective tile retains the originally passed value.

        // assertEquals(original, actual);
        // for (int i = 0; i < original.length; i++) {
        //     for (int j = 0; j < original[i].length; j++) {
        //         original[i][j] = original[i][j] + expected;
        //     }
        // }
        // original[y][x] += expected;
        // assertEquals(original[y][x] - expected, actual[y][x]);


        // actual[y][x] = expected;
        // int[][] actual2 = wb.getTiles();
        // assertNotEquals(actual[y][x], actual2[y][x]);
    }
}
