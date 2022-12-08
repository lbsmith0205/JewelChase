package Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Profile {

    private String profileName;
    private int highscore;
    private int highestLevel;

    public Profile(String profileName) {
        setName(profileName);
    }
    private String getName(){
        return profileName;
    }
    private void setName(String profileName) {
        this.profileName = profileName;
    }
    public int getHighscore(){
        return highscore;
    }
    public int getHighestLevel(){
        return highestLevel;
    }

    private void newProfileFile(){
        try{
            File myObj = new File("src/Profiles/" + profileName);
            if (myObj.exists()) {
                if(myObj.delete()) {
                    System.out.println("Save Deleted Successfully.");
                } else {
                    System.out.println("Failed to delete File.");
                }
            }
            if(myObj.createNewFile()) {
                System.out.println("File Created.");
            } else {
                System.out.println("File Already Exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An Error Has Occurred.");
            e.printStackTrace();
        }
    }

    public void save(){
        newProfileFile();
        try {
            FileWriter writer = new FileWriter("src/Profiles/" + profileName);
            writer.write(profileName + "\r\n" + highestLevel + "\r\n" + highscore);
            writer.close();
            System.out.println("Saved Successfully.");
        } catch (IOException e) {
            System.out.println("An Error Has Occurred.");
            e.printStackTrace();
        }
    }

    public void load(String save) throws FileNotFoundException {
            File myObj = new File("src/Profiles/" + save);
            if(!myObj.exists()) {
                return;
            }
            Scanner in = new Scanner(myObj);
            profileName = in.nextLine();
            in.nextLine();
            highestLevel = in.nextInt();
            in.nextLine();
            highscore = in.nextInt();

            in.close();
    }

    public void updateOnLevelCompletion(int level, int score) {
        if(level > highestLevel){
            this.highestLevel = level;
        }

        if(score > highscore){
            this.highscore = score;
        }
        save();
    }
}




