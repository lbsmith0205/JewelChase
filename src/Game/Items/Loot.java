package Game.Items;

public class Loot extends Item {

    private static final int CENT_COIN_VALUE = 1;
    private static final int DOLLAR_COIN_VALUE = 2;
    private static final int RUBY_VALUE = 5;
    private static final int DIAMOND_VALUE = 10;

    private int value;
    private String type;

    public Loot (int x, int y, String lootType) {
        super(x, y);
        switch (lootType){
            case "¢":
                value = CENT_COIN_VALUE;
                break;
            case "$":
                value = DOLLAR_COIN_VALUE;
                break;
            case "Ru":
                value = RUBY_VALUE;
                break;
            case "Di":
                value = DIAMOND_VALUE;
                break;
        }
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }


}
