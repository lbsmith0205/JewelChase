package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Levels {
    private ArrayList<Character> characters = new ArrayList<Character>();
    //private ArrayList<Item>Items = new ArrayList<Item>();
    private Tile [] tiles;

    private static ArrayList<Character> readLineByLine(Scanner in) {
        ArrayListy<ClosedShape> shapeQueue = new ArrayList<ClosedShape>();
        while (in.hasNextLine()) {
            String curLine = in.nextLine();
            Scanner line = new Scanner(curLine);
            String type = line.next();

        }
    }


    public Levels(){}

}
