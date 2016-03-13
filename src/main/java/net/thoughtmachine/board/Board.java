package net.thoughtmachine.board;

import net.thoughtmachine.battleship.Battleship;
import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.exception.BattleshipExistsInCoordsException;
import net.thoughtmachine.exception.BattleshipOutOfBoundsExeception;
import net.thoughtmachine.exception.InvalidBattleshipPlacementException;
import net.thoughtmachine.io.Parser;
import net.thoughtmachine.util.Consts.Rotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to store information regarding a board. i.e. its dimension and position of the battleships and any sunken
 * battleships
 *
 * @author rhawiz
 */
public class Board {

    private int dimension;
    private HashMap<Coordinate, Battleship> board;
    private ArrayList<Battleship> battleships;

    /**
     * Constructor
     *
     * @param dimension The x and y dimension of the square board.
     */
    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new HashMap<Coordinate, Battleship>();
        this.battleships = new ArrayList<Battleship>();
    }


    /**
     * Populate the board with an arraylist of battleships.
     *
     * @param battleshipArrayList
     * @throws InvalidBattleshipPlacementException
     */
    public void populate(List<Battleship> battleshipArrayList) throws InvalidBattleshipPlacementException {

        for (Battleship battleship : battleshipArrayList) {

            addBattleship(battleship);

        }

    }

    /**
     * Get the Battleship at a provided Coordinate
     *
     * @param coords x, y position of the battleship
     * @return Battleship at the coordinate provided or null if empty.
     */
    public Battleship getBattleship(Coordinate coords) {
        return board.get(coords);
    }

    /**
     * Removes a battleship provided a Coordinate
     *
     * @param coords x, y position of the battleship
     */
    public void removeBattleship(Coordinate coords) {
        board.remove(coords);

    }

    /**
     * Adds a battleship provided a location. Will override any other battleship if one already exists in that position.
     *
     * @param battleship the battleship to be added onto the board
     */
    public void addBattleship(Battleship battleship) throws InvalidBattleshipPlacementException {
        int x = battleship.getX();
        int y = battleship.getY();
        if (x >= dimension || x < 0 || y >= dimension || y < 0) {
            throw new BattleshipOutOfBoundsExeception();
        }

        Coordinate coords = new Coordinate(x, y);

        // If the key exists, it means there is already a battleship in that position, we'll throw an error.
        if (board.containsKey(coords)) {
            throw new BattleshipExistsInCoordsException();
        }

        if (!battleships.contains(battleship)) {
            battleships.add(battleship);
        }
        board.put(coords, battleship);

    }

    /**
     * Move a battleship at a given coordinate and series of movement operations. Will ignore any unknown commands.
     *
     * @param coords Coordinates
     * @throws InvalidBattleshipPlacementException
     */
    public void moveBattleship(Coordinate coords, String operations) throws InvalidBattleshipPlacementException {
        Battleship battleship = getBattleship(coords);

        if (battleship == null) {
            return;
        }

        Coordinate oldCoords = battleship.getCoordinates();
        int oldX = oldCoords.getX();
        int oldY = oldCoords.getY();

        for (int i = 0; i < operations.length(); i++) {
            char operation = operations.charAt(i);
            if (operation == 'M') {
                battleship.move();
            } else {
                Rotation rotation = Rotation.getRotation(operation);

                if (rotation != null) {
                    battleship.rotate(rotation);
                }
            }

        }

        Coordinate newCoords = battleship.getCoordinates();

        if (getBattleship(newCoords) == null) {
            removeBattleship(oldCoords);
            addBattleship(battleship);
        } else {
            battleship.setX(oldX);
            battleship.setY(oldY);
        }

    }


    /**
     * Shoot at the given x and y coordinates and sink the ship if there is one.
     *
     * @param shootCoords the Coordinates with the location to fire at
     */
    public void shoot(Coordinate shootCoords) {

        Battleship battleship = getBattleship(shootCoords);

        if (battleship != null) {
            battleship.sink();
            removeBattleship(shootCoords);
        }


    }


    public String getTextOutput() {
        String output = "";
        for (Battleship battleship : battleships) {
            output += battleship + "\n";
        }

        return output.trim();
    }
}
