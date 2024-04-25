package walking.game.player;

import walking.game.util.Direction;

public class Player {
    private int score;
    public int getScore(){ return score; }
    protected Direction direction = Direction.UP;
    public Direction getDirection(){ return direction; }

    public Player() {}

    public void addToScore(int score) {}

    public void turn(){
        Direction curDirection = direction;
        switch(curDirection){
            case Direction.UP:
                direction = Direction.RIGHT;
                break;
            case Direction.RIGHT:
                direction = Direction.DOWN;
                break;
            case Direction.DOWN:
                direction = Direction.LEFT;
                break;
            default:
                direction = Direction.UP;
                break;
        }
    }

}

