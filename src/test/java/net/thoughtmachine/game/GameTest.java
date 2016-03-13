package net.thoughtmachine.game;

import net.thoughtmachine.exception.InvalidBattleshipPlacementException;
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
    public void TestGame1() throws InvalidBattleshipPlacementException {

        String inputFile = "/tests/input_1_test.txt";
        String expectedOutputFile = "/tests/input_1_expected.txt";


        Game game = new Game(inputFile, expectedOutputFile);
        game.run();
        FileReader fileReader = new FileReader(expectedOutputFile);
        String expectedOutputText = fileReader.read().trim();
        String actualOutputText = game.getGameResults().trim();

        assertEquals(actualOutputText, expectedOutputText);

    }

    @Test
    public void TestGame2() throws InvalidBattleshipPlacementException {

        String inputFile = "/tests/input_2_test.txt";
        String expectedOutputFile = "/tests/input_2_expected.txt";


        Game game = new Game(inputFile, expectedOutputFile);
        game.run();
        FileReader fileReader = new FileReader(expectedOutputFile);
        String expectedOutputText = fileReader.read().trim();
        String actualOutputText = game.getGameResults().trim();

        assertEquals(actualOutputText, expectedOutputText);

    }

}
