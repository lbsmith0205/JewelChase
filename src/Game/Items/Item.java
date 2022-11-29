package Game.Items;

public abstract class Item {
    protected final int xCoord;
    protected final int yCoord;
    protected boolean exist;
    protected boolean contact = false;

    public Item (int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
        this.exist = true;
    }

    protected void remove() {
        exist = false;
    }

    public abstract void interact ();
}
