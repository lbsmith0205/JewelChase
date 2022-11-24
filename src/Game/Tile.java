package Game;

<<<<<<< HEAD
import javafx.scene.paint.Color;

public abstract class Tile {

    private final Color[] tileColours;
    private final int yPosition;
    private final int xPosition;

    public Tile(int xPosition, int yPosition, Color[] tileColours){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tileColours = tileColours;
=======
import java.util.ArrayList;

public abstract class Tile {

    private final Object tileColours;
    private final int yPosition;
    private final int xPosition;

    public Tile(int xPosition, int yPosition, ArrayList<String> tileColours){
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.tileColours = tileColours;
>>>>>>> origin/Luke
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public Object getTileColours() {
        return tileColours;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/Luke
