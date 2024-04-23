package walking.game;

import walking.game.util.Direction;

public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    static public final int BASE_TILE_SCORE = 3;
    public WalkingBoard(int size) {}
    public WalkingBoard(int[][] tiles) {}
    public int[][] getTiles() { return tiles; }
    public int[] getPosition(){
        return new int[0];
    }
    public boolean isValidPosition(int x, int y) {
        return false;
    }
    public int getTile(int x, int y) {
        return 0;
    }
    public static int getXStep( Direction direction) {
        return 0;
    }

    public static int getYStep( Direction direction) {
        return 0;
    }

    public int moveAndSet(Direction TODOname, int value) {
        return 0;
    }
}

