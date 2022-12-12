package Game.Board;

import Game.Items.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A Tile holds Characters and Items, build the foundation for the Board.
 *
 * @author Luke Smith, Daniel Baxter, Khoi Nguyen Cao.
 */
public class Tile {
    private final Color[] tileColours;
    private final int yPosition;
    private final int xPosition;

    private ArrayList<Object> objectsOnTile;

    /**
     * Create an instance of the Tile.
     *
     * @param xPosition x coordinates of the Tile.
     * @param yPosition y coordinates of the Tile.
     * @param tileColours Colours that made up the Tile.
     */
    public Tile(int xPosition, int yPosition, Color[] tileColours) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tileColours = tileColours;
        this.objectsOnTile = new ArrayList<>();
    }

    /**
     * Get the y coordinate of the Tile.
     *
     * @return y coordinate of the Tile.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Get the x coordinate of the Tile.
     *
     * @return x coordinate of the Tile.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Get the Colours of the Tile.
     *
     * @return Colours of the Tile.
     */
    public Color[] getTileColours() {
        return tileColours;
    }

    /**
     * Get the List of Items and Characters currently on the Tile.
     *
     * @return list of Items and Characters.
     */
    public ArrayList<Object> getObjectsOnTile() {
        return objectsOnTile;
    }

    /**
     * Add an Item or Character into the list in Tile.
     *
     * @param newObject Item/Character to add.
     */
    public void addObjectToTile(Object newObject) {
        objectsOnTile.add(newObject);
    }

    /**
     * Remove an Item or Character from the list in Tile.
     *
     * @param targetObject Item/Character needed to be removed.
     */
    public void removeObjectFromTile(Object targetObject) {
        objectsOnTile.remove(targetObject);
    }

    /**
     * Check if the Tile contain an Item.
     *
     * @return true if there is an Item is present, false if not.
     */
    public boolean hasLoot() {
        for (Object o : objectsOnTile) {
            if (o instanceof Loot) {
                return true;
            }
        }
        return false;
    }

    public Loot getLoot() {
        for (Object o : objectsOnTile) {
            if (o instanceof Loot) {
                return (Loot) o;
            }
        }
        return null;
    }

    /**
     * Convert the Tile colours into a String.
     *
     * @return Tile colours in String.
     */
    public String getTileInString() {
        StringBuilder colour = new StringBuilder();
        for (Color tileColour : tileColours) {
            if (tileColour == Color.INDIANRED) {
                colour.append("R");
            } else if (tileColour == Color.SPRINGGREEN) {
                colour.append("G");
            } else if (tileColour == Color.ROYALBLUE) {
                colour.append("B");
            } else if (tileColour == Color.KHAKI) {
                colour.append("Y");
            } else if (tileColour == Color.CYAN) {
                colour.append("C");
            } else if (tileColour == Color.MEDIUMPURPLE) {
                colour.append("M");
            }
        }

        return colour.toString();
    }

    /**
     * Check if the Tile has a Bomb.
     *
     * @return true if a Bomb is present, false if not.
     */
    public boolean hasBomb() {
        for (Object object : objectsOnTile) {
            if (object instanceof Bomb) {
                return true;
            }
        }
        return false;
    }

    /**
     * Causes explosions on all Tiles horizontally and vertically from the Bombs.
     */
    public void explode() {
        ArrayList<Object> remnants = new ArrayList<>();

        for (Object object : objectsOnTile) {
            ArrayList<Tile> toExplode = new ArrayList<>();
            if (object instanceof Bomb) {
                if (!((Bomb) object).getIsActive()) {
                    toExplode.addAll(((Bomb) object).getBlastZone());
                }
            }
            for (Tile tile : toExplode) {
                tile.explode();
            }
            if (object instanceof Door || object instanceof Gate) {
                remnants.add(object);
            }
        }

        objectsOnTile = remnants;
        if (objectsOnTile.isEmpty()) {
            objectsOnTile.add(new Explosion(this));
        }
    }
}