package Game.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A Profile is used to store the information of a user, high score and max level unlocked
 *
 * @author Luke Smith.
 */
public class Profile {

    private String profileName;
    private int highscore;
    private int highestLevel;

    /**
     * Create an instance of Profile.
     *
     * @param profileName name of the user,
     */
    public Profile(String profileName) {
        setName(profileName);
    }

    /**
     * Get the name of the Profile.
     *
     * @return name of the Profile.
     */
    private String getName() {
        return profileName;
    }

    /**
     * Set the name of the Profile.
     *
     * @param profileName new name for the Profile.
     */
    private void setName(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Get the high score of the Profile.
     *
     * @return high score of Profile.
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     * Get the maximum level that the Profile unlocked.
     *
     * @return the maximum unlocked Level.
     */
    public int getHighestLevel() {
        return highestLevel;
    }

    /**
     * Create a new Profile if it doesn't exist.
     *
     * @throws IOException if the input is wrong.
     */
    public void newProfileFile() throws IOException {
        File saveDirectory = new File("src/Profiles/");
        File myObj = new File(saveDirectory, profileName);
        if(!saveDirectory.exists()) {
            saveDirectory.mkdir();
        }

        if (myObj.exists()) {
            if (myObj.delete()) {
                System.out.println("Save Deleted Successfully.");
            } else {
                System.out.println("Failed to delete File.");
            }
        }
        if (myObj.createNewFile()) {
            System.out.println("File Created.");
        } else {
            System.out.println("File Already Exists.");
        }
    }


    /**
     *  Save the Profile to the Profile folder.
     *
     * @throws IOException if the input is wrong.
     */
    public void save() throws IOException {
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

    /**
     * Load the Profile created in the Profile folder.
     *
     * @param save name of the saved Profile.
     * @throws FileNotFoundException
     */
    public void load(String save) throws FileNotFoundException {
        File myObj = new File("src/Profiles/" + save);
        if (!myObj.exists()) {
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

    /**
     * Update the high score and the max level unlocked.
     *
     * @param level level unlocked.
     * @param score new score.
     * @throws IOException if input is wrong.
     */
    public void updateOnLevelCompletion(int level, int score) throws IOException {
        if (level > highestLevel) {
            this.highestLevel = level;
        }

        if (score > highscore) {
            this.highscore = score;
        }
        save();
    }
}




