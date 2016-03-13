package net.thoughtmachine.io;

import net.thoughtmachine.battleship.Battleship;
import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.exception.InvalidBattleshipPlacementException;
import net.thoughtmachine.util.Consts.Orientation;
import net.thoughtmachine.util.Consts.Operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to handle reading and parsing input file
 *
 * @author rhawiz
 */
public class Parser {

    String filePath;
    int boardDimension;
    ArrayList<Battleship> battleshipList;
    ArrayList<String> operationsList;

    public Parser(String filePath) {
        this.filePath = filePath;
        battleshipList = new ArrayList<Battleship>();
        operationsList = new ArrayList<String>();
    }

    public void parse() {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        int count = 0;
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (count == 0) {
                    boardDimension = Integer.parseInt(line);
                } else if (count == 1) {
                    battleshipList = parseBattleships(line);
                } else {
                    operationsList.add(line);
                }

                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<Battleship> parseBattleships(String line) {
        ArrayList<Battleship> battleshipsList = new ArrayList<Battleship>();
        ArrayList<String> matches = getRegexMatches(line, "\\(([0-9]\\d*,\\s*[0-9]\\d*,\\s*[MLRNSWE])\\)");

        for (String shipInfoString : matches) {
            String[] shipInfoList = shipInfoString.split(",");
            if (shipInfoList.length == 3) {

                int x = Integer.parseInt(shipInfoList[0].trim());
                int y = Integer.parseInt(shipInfoList[1].trim());
                Orientation orientation = Orientation.getOrientation(shipInfoList[2].trim().charAt(0));
                Coordinate coords = new Coordinate(x, y);
                Battleship battleship = new Battleship(coords, orientation);
                battleshipsList.add(battleship);
            }
        }

        return battleshipsList;

    }

    public int getBoardDimension() {
        return boardDimension;
    }

    public ArrayList<Battleship> getBattleshipList() {
        return battleshipList;
    }

    public ArrayList<String> getOperationsList() {
        return operationsList;
    }


    /**
     * Get the Operation type given a line of text, otherwise return null
     *
     * @param line
     * @return Operation if valid operation, null if otherwise.
     */
    public static Operation getOperation(String line) {
        if (isMoveOp(line)) {
            return Operation.MOVE;
        } else if (isShootOp(line)) {
            return Operation.SHOOT;
        }

        return null;
    }

    /**
     * Check if a line of string is a shoot operation
     *
     * @param line
     * @return true if line is a shoot operation, false otherwise.
     */
    private static boolean isShootOp(String line) {
        char lastChar = line.trim().charAt(line.trim().length() - 1);
        char firstChar = line.trim().charAt(0);
        return firstChar == '(' && lastChar == ')';
    }

    /**
     * Check if a line of string is a move operation
     *
     * @param line
     * @return true if line is a move operation, false otherwise.
     */
    private static boolean isMoveOp(String line) {
        ArrayList<String> matches = getRegexMatches(line, "\\(([0-9]\\d*,\\s*[0-9]\\d*)\\)");
        if(matches.size()==0){
            return false;
        }
        String matchedPosition = getRegexMatches(line, "\\(([0-9]\\d*,\\s*[0-9]\\d*)\\)").get(0);
        String[] location = matchedPosition.split("\\s*,\\s*");
        String movements = line.substring(line.indexOf(')') + 1, line.length()).trim();

        return location.length == 2 && movements.length() > 0;


    }

    /**
     * Takes in a line of text and returns the Coordinates to shoot at.
     *
     * @param line
     * @return Coordinate
     */
    public static Coordinate parseShootCommand(String line) {
        String lineCSV = line.substring(1, line.length() - 1);
        String[] location = lineCSV.split("\\s*,\\s*");

        int x = Integer.parseInt(location[0]);
        int y = Integer.parseInt(location[1]);

        return new Coordinate(x, y);
    }

    /**
     * Takes in a line of text and returns the Coordinates to move to.
     *
     * @param line
     * @return Coordinates
     */
    public static Coordinate parseMoveCoordinatesCommand(String line) {
        String matchedPosition = Parser.getRegexMatches(line, "\\(([0-9]\\d*,\\s*[0-9]\\d*)\\)").get(0);
        String[] location = matchedPosition.split("\\s*,\\s*");
        int x = Integer.parseInt(location[0].trim());
        int y = Integer.parseInt(location[1].trim());
        return new Coordinate(x, y);

    }


    /**
     * Takes in a line of text and returns a series of movement instructions
     *
     * @param line
     * @return movements String of instructions
     */
    public static String parseMoveOperationsCommand(String line) {
        String movements = line.substring(line.indexOf(')') + 1, line.length()).trim();
        return movements;

    }


    /**
     * Takes in a string and a regex pattern and returns an ArrayList of all matches found.
     *
     * @param text
     * @param regexPattern
     * @return ArrayList of all regex matches found in the string
     */
    public static ArrayList<String> getRegexMatches(String text, String regexPattern) {
        ArrayList<String> matches = new ArrayList<String>();
        Matcher patternMatcher = Pattern.compile(regexPattern).matcher(text);
        while (patternMatcher.find()) {
            matches.add(patternMatcher.group(1));
        }

        return matches;
    }

    public static void main(String... args) {
        Parser parser = new Parser("/input.txt");
        parser.parse();
    }

}
