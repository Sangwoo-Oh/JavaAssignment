package walking.game.player;

import walking.game.util.Direction;

public class Player {
    private int score;
    public int getScore(){ return score; }
    protected Direction direction = Direction.UP;
    public Direction getDirection(){return direction;}

    public Player() {}

    public void addToScore(int score) {}

    public void turn(){}

}

