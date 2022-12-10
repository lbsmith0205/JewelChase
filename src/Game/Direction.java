package Game;

import java.util.Random;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;
    private static Random rnd = new Random();

    public static Direction getRandomDirection(){
        return values()[rnd.nextInt(4 )];
    }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }

    public Direction turnBack() {
        return values()[(ordinal() + 2) % 4];
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }

}
