package net.thoughtmachine.game;

import net.thoughtmachine.exception.InvalidShipPlacementException;
import net.thoughtmachine.io.FileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the Game using test inputs and comparing the output with the associated expected output file
 *
 */
public class GameTest {
    @Test
    public void TestGame1() throws InvalidShipPlacementException {

        String inputFile = "/tests/input_1_test.txt";
        String expectedOutputFile = "/tests/input_1_expected.txt";


        Game game = new Game(inputFile, expectedOutputFile);
        game.run();
        FileReader fileReader = new FileReader(expectedOutputFile);
        String expectedOutputText = fileReader.read().trim();
        String actualOutputText = game.getGameResults().trim();
        actualOutputText = actualOutputText.replaceAll("\\r?\\n","").trim();
        expectedOutputText = expectedOutputText.replaceAll("\\r?\\n","").trim();

        assertEquals(actualOutputText, expectedOutputText);

    }

    @Test
    public void TestGame2() throws InvalidShipPlacementException {

        String inputFile = "/tests/input_2_test.txt";
        String expectedOutputFile = "/tests/input_2_expected.txt";


        Game game = new Game(inputFile, expectedOutputFile);
        game.run();
        FileReader fileReader = new FileReader(expectedOutputFile);
        String expectedOutputText = fileReader.read().trim();
        String actualOutputText = game.getGameResults().trim();
        actualOutputText = actualOutputText.replaceAll("\\r?\\n","").trim();
        expectedOutputText = expectedOutputText.replaceAll("\\r?\\n","").trim();

        assertEquals(actualOutputText, expectedOutputText);

    }



    public static void main(String... args) {
        GameTest test = new GameTest();

        try {
            test.TestGame2();
        } catch (InvalidShipPlacementException e) {
            e.printStackTrace();
        }

    }

}
