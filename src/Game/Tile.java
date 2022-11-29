package Game;

import javafx.scene.paint.Color;

public class Tile {

    private final Color[] tileColours;
    private final int yPosition;
    private final int xPosition;

    public Tile(int xPosition, int yPosition, Color[] tileColours) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tileColours = tileColours;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public Color[] getTileColours() {
        return tileColours;
    }
}