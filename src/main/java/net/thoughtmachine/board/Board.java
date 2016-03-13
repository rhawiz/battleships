package net.thoughtmachine.board;

import net.thoughtmachine.ship.*;
import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.exception.ShipExistsInCoordsException;
import net.thoughtmachine.exception.ShipOutOfBoundsExeception;
import net.thoughtmachine.exception.InvalidShipPlacementException;
import net.thoughtmachine.util.Consts.Rotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to store information regarding a board. i.e. its dimension and position of the shipList and any sunken
 * shipList
 *
 * @author rhawiz
 */
public class Board {

    private int dimension;
    private HashMap<Coordinate, IShip> board;
    private ArrayList<IShip> shipList;

    /**
     * Constructor
     *
     * @param dimension The x and y dimension of the square board.
     */
    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new HashMap<Coordinate, IShip>();
        this.shipList = new ArrayList<>();
    }


    /**
     * Populate the board with an arraylist of shipList.
     *
     * @param shipList
     * @throws InvalidShipPlacementException
     */
    public void populate(List<IShip> shipList) throws InvalidShipPlacementException {

        for (IShip ship : shipList) {

            addShip(ship);

        }

    }

    /**
     * Get the ship at a provided Coordinate
     *
     * @param coords x, y position of the ship
     * @return ship at the coordinate provided or null if empty.
     */
    public IShip getShip(Coordinate coords) {
        return board.get(coords);
    }

    /**
     * Removes a ship provided a Coordinate
     *
     * @param coords x, y position of the ship
     */
    public void removeShip(Coordinate coords) {
        board.remove(coords);

    }

    /**
     * Adds a ship provided a location. Will override any other ship if one already exists in that position.
     *
     * @param ship the ship to be added onto the board
     */
    public void addShip(IShip ship) throws InvalidShipPlacementException {
        int x = ship.getCoordinates().getX();
        int y = ship.getCoordinates().getY();
        if (x >= dimension || x < 0 || y >= dimension || y < 0) {
            throw new ShipOutOfBoundsExeception();
        }

        Coordinate coords = new Coordinate(x, y);

        // If the key exists, it means there is already a ship in that position, we'll throw an error.
        if (board.containsKey(coords)) {
            throw new ShipExistsInCoordsException();
        }

        if (!shipList.contains(ship)) {
            shipList.add(ship);
        }
        board.put(coords, ship);

    }

    /**
     * Move a ship at a given coordinate and series of movement operations. Will ignore any unknown commands.
     *
     * @param coords Coordinates
     * @throws InvalidShipPlacementException
     */
    public void moveShip(Coordinate coords, String operations) throws InvalidShipPlacementException {
        IShip ship = getShip(coords);

        if (ship == null) {
            return;
        }

        Coordinate oldCoords = ship.getCoordinates();
        int oldX = oldCoords.getX();
        int oldY = oldCoords.getY();

        for (int i = 0; i < operations.length(); i++) {
            char operation = operations.charAt(i);
            if (operation == 'M') {
                ship.move();
            } else {
                Rotation rotation = Rotation.getRotation(operation);

                if (rotation != null) {
                    ship.rotate(rotation);
                }
            }

        }

        Coordinate newCoords = ship.getCoordinates();

        if (getShip(newCoords) == null) {
            removeShip(oldCoords);
            addShip(ship);
        } else {
            ship.getCoordinates().setX(oldX);
            ship.getCoordinates().setY(oldY);
        }

    }


    /**
     * Shoot at the given x and y coordinates and sink the ship if there is one.
     *
     * @param shootCoords the Coordinates with the location to fire at
     */
    public void shoot(Coordinate shootCoords) {

        IShip ship = getShip(shootCoords);

        if (ship != null) {
            ship.sink();
            removeShip(shootCoords);
        }


    }


    public String getTextOutput() {
        String output = "";
        for (IShip ship : shipList) {
            output += ship + "\n";
        }

        return output.trim();
    }
}
