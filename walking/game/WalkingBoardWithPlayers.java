package walking.game;

import walking.game.player.Player;
import walking.game.player.MadlyRotatingBuccaneer;
import java.lang.IllegalArgumentException;

public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int round;
    static final public int SCORE_EACH_STEP = 13;

    private int[] scores;
    public int[] getScores() { return scores; }

    public WalkingBoardWithPlayers(int[][] board, int playerCount) {
        super(board);
        initPlayers(playerCount);
    };
    public WalkingBoardWithPlayers(int size, int playerCount) {
        super(size);
        initPlayers(playerCount);
    };

    private void initPlayers(int playerCount) {
        if (playerCount < 2) throw new IllegalArgumentException();
        players = new Player[playerCount];
        for (int i = 0; i<playerCount; i++) {
            players[i] = i == 0 ? new MadlyRotatingBuccaneer() : new Player();
        }
    }

    public int[] walk(int... stepCounts) {
        scores = new int[players.length];
        int total = 0;
        for (int i = 0; i < stepCounts.length; i++) {
            for (int j = 0; j < players.length; j++) {
                players[j].turn();
                for (int k = 0; k < stepCounts[i]; k++) {
                    total++;
                    // moveAndSet(players[j].getDirection(), total < SCORE_EACH_STEP ? total: SCORE_EACH_STEP);
                    // scores[j] += getTile(getPosition()[0],getPosition()[1]);
                    scores[j] += moveAndSet(players[j].getDirection(), total < SCORE_EACH_STEP ? total: SCORE_EACH_STEP);
                }
            }
        }
        return scores;
    }

}

