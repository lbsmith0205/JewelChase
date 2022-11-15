package Game;

import java.io.IOException;

public class Menu {

    private static final String MOTD_GET_URL = "http://cswebcat.swansea.ac.uk/puzzle";
    private static final String MOTD_SOL_GET_URL = "http://cswebcat.swansea.ac.uk/message";
    private static final int MOTD_ALPHABET_LENGTH = 26;
    private static final int MOTD_INT_CONVERSION = -1;
    private static final String MOTD_APPEND = "CS-230";



























    private void updateMOTD()  {
        try{

        }

    }

    private String puzzleSolution(String puzzle){
        String possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int moveLetter = -1;
        StringBuilder solution = new StringBuilder();
        for (int i = 0 ; i < puzzle.length() ; i++){
            int alphabetPos = possibleLetters.indexOf(puzzle.charAt(i));
            solution.append(possibleLetters.charAt(Math.floorMod((alphabetPos + ((i + 1) * moveLetter)), MOTD_ALPHABET_LENGTH)));
            moveLetter = moveLetter * MOTD_INT_CONVERSION;

        }
        solution.append(MOTD_APPEND);
        String length = String.valueOf(solution.length());
        solution = new StringBuilder(length + solution);
        return String.valueOf(solution);
    }
}
