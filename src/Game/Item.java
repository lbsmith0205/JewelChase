package Game;

public class Item {
    private int xCoord;
    private int yCoord;
    private boolean exist;
    private boolean contact = false;

    public Item (int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
        this.exist = true;
    }

    protected void remove() {
        exist = false;
    }

    protected boolean interact (Item item) {
        contact = true;
        return true;
    }
}
