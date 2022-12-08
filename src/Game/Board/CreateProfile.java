package Game.Board;

import java.io.IOException;

public class CreateProfile {

    private static final String[] UNUSABLE_CHARACTERS = {"#","%","&","{","}","\\","<",">","*","?","/"," ","$","!","'","\"",":",";","@","+","`","|","=",",","."};

    public String name;

    // I will convert this to javafx when I can be arsed
    public void createNewProfile() throws IOException {
        // Checks to see if name field is empty
        if(name.isEmpty()) {
            System.out.println("Name Is Empty.");
        }

        // Checks for characters that could cause problems
        for(String i : UNUSABLE_CHARACTERS){
            if(name.contains(i)){
                System.out.println("Name Contains Illegal Characters.");
            }
        }
        // creates new profile with said name
        Profile player = new Profile(name);
        player.save();

    }

    // Will do a switch from initial profile menu to main menu here
}
