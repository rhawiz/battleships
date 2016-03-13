package net.thoughtmachine;

import net.thoughtmachine.game.Game;

public class Application {

    private static final String input = "/input.txt";

    private static final String output = "/output.txt";


    public static void main(String... args) {
        try {
            Game game = new Game(input, output);
            game.run();
            game.writeResults();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
