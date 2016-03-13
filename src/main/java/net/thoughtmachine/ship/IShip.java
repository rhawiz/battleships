package net.thoughtmachine.ship;

import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.util.Consts.Orientation;
import net.thoughtmachine.util.Consts.Rotation;

/**
 * Generic Battleship Interface. Allows multiple ships to be added later on if required that will inherit this class.
 */
public interface IShip {

    void move();

    void rotate(Rotation rotation);

    Coordinate getCoordinates();

    Orientation getOrientation();

    void sink();

    boolean isSunk();

}
