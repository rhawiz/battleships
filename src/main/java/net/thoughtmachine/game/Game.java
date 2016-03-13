package net.thoughtmachine.game;

import net.thoughtmachine.board.Board;
import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.exception.InvalidShipPlacementException;
import net.thoughtmachine.io.*;
import net.thoughtmachine.io.FileWriter;
import net.thoughtmachine.util.Consts.Operation;

import java.io.*;
import java.util.ArrayList;

/**
 * Core Game class to initialise the board, parse input file and run the Game
 */
public class Game {

    private Parser parser;
    private String output;
    private Board board;

    public Game(String inputFile, String output) throws InvalidShipPlacementException {
        parser = new Parser(inputFile);
        parser.parse();
        this.output = output;
        board = new Board(parser.getBoardDimension());
        board.populate(parser.getShipList());
    }


    /**
     * Run the Game with the given text instructions and write the output to the provided location
     *
     * @throws InvalidShipPlacementException
     * @throws IOException
     */
    public void run() throws InvalidShipPlacementException {
        ArrayList<String> operations = parser.getOperationsList();
        for (String operationLine : operations) {
            Operation operation = Parser.getOperation(operationLine);
            if (operation == Operation.MOVE) {
                Coordinate moveCoords = Parser.parseMoveCoordinatesCommand(operationLine);
                String moveOperations = Parser.parseMoveOperationsCommand(operationLine);
                board.moveShip(moveCoords, moveOperations);
            } else if (operation == Operation.SHOOT) {
                Coordinate shootCoords = Parser.parseShootCommand(operationLine);
                board.shoot(shootCoords);
            }
        }

    }

    public void writeResults() throws IOException {
        FileWriter fileWriter = new FileWriter(output, getGameResults());
        fileWriter.write();
    }

    public String getGameResults() {
        return board.getTextOutput();
    }

}
