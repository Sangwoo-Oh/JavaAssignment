package walking.game;

import java.util.Arrays;

import walking.game.util.Direction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WalkingBoardWithPlayersTest {
/**
 * JUnit 5 tests: create walk1 and walk2 that walk on two separate boards that feature different numbers of players. In each one, let each players take a step at least three times. Check the resulting contents of each board and also check the final scores of the players.
 */
    @Test
    public void walk1Test() {
        WalkingBoardWithPlayers wb = new WalkingBoardWithPlayers(3, 3);
        wb.walk(1,1,1);
        /**
         *  3 3 3
         *  3 3 3
         *  3 3 3
        */
        int[][] expected = new int[][] {
            {3, 2, 3},
            {3, 3, 5},
            {8, 7, 6}
        };

        // board values
        for (int i = 0; i < wb.getTiles().length; i++) {
            for (int j = 0; j < wb.getTiles()[i].length; j++) {
                assertEquals(expected[i][j], wb.getTiles()[i][j]);
            }
        }

        // scores
        assertArrayEquals(new int[] { 3,9,6 }, wb.getScores());
    }

    @Test
    public void walk2Test() {
        int[][] passedArr = new int[][] {
            {1, 2, 3},
            {4},
            {5, 6, 7}
        };
        WalkingBoardWithPlayers wb = new WalkingBoardWithPlayers(passedArr, 2);
        wb.walk(1,2,1);
        int[][] expected = new int[][] {
            {8, 7, 3},
            {4},
            {5, 6, 7}
        };
        // board values
        for (int i = 0; i < wb.getTiles().length; i++) {
            for (int j = 0; j < wb.getTiles()[i].length; j++) {
                assertEquals(expected[i][j], wb.getTiles()[i][j]);
            }
        }

        // scores
        assertArrayEquals(new int[] { 5, 6 }, wb.getScores());
    }

}
