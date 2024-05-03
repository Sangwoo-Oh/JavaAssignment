package walking.game;

import java.util.Arrays;
import java.lang.IllegalArgumentException;
import walking.game.util.Direction;


public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    static public final int BASE_TILE_SCORE = 3;
    public WalkingBoard(int size) {
        tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = BASE_TILE_SCORE;
            }
        }
    }
    public WalkingBoard(int[][] tiles) {
        this.tiles = new int[tiles.length][];
        for (int i = 0; i < this.tiles.length; i++) {
            this.tiles[i] = new int[tiles[i].length];
            for (int j = 0; j < tiles[i].length; j++) {
                this.tiles[i][j] = tiles[i][j] < BASE_TILE_SCORE ? BASE_TILE_SCORE : tiles[i][j];
            }
        }
    }
    public int[] getPosition(){
        return new int[] {x, y};
    }
    public boolean isValidPosition(int x, int y) {
        return (0 <= y && y < tiles.length) && (0 <= x && x < tiles[y].length);
    }
    public int getTile(int x, int y) {
        if (!isValidPosition(x,y)) throw new IllegalArgumentException();
        return tiles[y][x];
    }
    public int[][] getTiles() {
        int[][] copyOfTiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            copyOfTiles[i] = new int[tiles[i].length];
            for (int j = 0; j < tiles[i].length; j++) {
                copyOfTiles[i][j] = tiles[i][j];
            }
        }
        return copyOfTiles;
    }
    public static int getXStep(Direction direction) {
        if (direction == Direction.RIGHT) return 1;
        if (direction == Direction.LEFT) return -1;
        return 0;
    }

    public static int getYStep( Direction direction) {
        if (direction == Direction.UP) return -1;
        if (direction == Direction.DOWN) return 1;
        return 0;
    }

    public int moveAndSet(Direction direction, int value) {
        if (!isValidPosition(x + getXStep(direction),y + getYStep(direction))) return 0;
        x += getXStep(direction);
        y += getYStep(direction);
        int ret = getTile(x,y);
        tiles[y][x] = value;
        return ret;
    }
}

