package Game;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

    }





    public static void main(String[] args) {
        //new Thread(new GameLoop()).start();
        /*while(true)
        System.out.println(System.currentTimeMillis());*/
        System.out.println("Enter: ");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Scanner string = new Scanner(line);
        string.useDelimiter(",");
        int i = string.nextInt();
        String s = string.next();

        System.out.println(i + " " + s);
    }
}

