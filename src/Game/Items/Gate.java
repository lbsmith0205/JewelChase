package Game.Items;

import javafx.scene.paint.Color;

public class Gate extends Item{
    private final Color colorCode;
    private final Lever linkedLever;

    public Gate (int x, int y, Lever lever) {
        super(x,y);
        this.linkedLever = lever;
        this.colorCode = lever.getColorCode();
    }


    @Override
    public void interact() {

    }
}
