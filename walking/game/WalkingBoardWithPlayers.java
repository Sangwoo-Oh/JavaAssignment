package walking.game;

import walking.game.player.Player;

public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int round;
    static final public int SCORE_EACH_STEP = 13;

    public WalkingBoardWithPlayers(int[][] board, int playerCount) {
        super(board);
    };
    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
    };

    private void initPlayers(int playerCount) {

    }

    public int[] walk(int... stepCounts) {
        return new int[0];
    }

}

