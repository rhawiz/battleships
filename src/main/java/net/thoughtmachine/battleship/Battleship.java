package net.thoughtmachine.battleship;


import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.util.Consts.Rotation;
import net.thoughtmachine.util.Consts.Orientation;

import static net.thoughtmachine.util.Consts.Rotation.LEFT;

/**
 * Battleship class to store its current state. i.e. (x, y) coordinates, orientation and whether it's been sunk or not.
 *
 * @author rhawiz
 */
public class Battleship {
    private Orientation orientation;
    private Coordinate coordinates;
    private boolean sunk;

    public Battleship(Coordinate coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
        this.sunk = false;
    }

    public void setX(int x) {
        coordinates.setX(x);
    }

    public void setY(int y) {
        coordinates.setY(y);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getX() {
        return coordinates.getX();
    }

    public int getY() {
        return coordinates.getY();
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void sink() {
        sunk = true;
    }

    public boolean isSunk() {
        return sunk;
    }


    /**
     * Move the coordinates of the battleship one place in the direction of where its facing
     */
    public void move() {
        switch (orientation) {
            case NORTH:
                setY(getY() + 1);
                break;
            case EAST:
                setX(getX() + 1);
                break;
            case SOUTH:
                setY(getY() - 1);
                break;
            case WEST:
                setX(getX() - 1);
                break;
        }
    }

    /**
     * Set rotation accordingly depending on whether it's L or R
     *
     * @param rotation
     */
    public void rotate(Rotation rotation) {
        switch (rotation) {
            case LEFT:
                switch (orientation) {
                    case NORTH:
                        orientation = Orientation.WEST;
                        break;
                    case EAST:
                        orientation = Orientation.NORTH;
                        break;
                    case SOUTH:
                        orientation = Orientation.EAST;
                        break;
                    case WEST:
                        orientation = Orientation.SOUTH;
                        break;
                }
                break;
            case RIGHT:
                switch (orientation) {
                    case NORTH:
                        orientation = Orientation.EAST;
                        break;
                    case EAST:
                        orientation = Orientation.SOUTH;
                        break;
                    case SOUTH:
                        orientation = Orientation.WEST;
                        break;
                    case WEST:
                        orientation = Orientation.NORTH;
                        break;
                }
                break;
        }
    }

    public String toString() {
        String str = "(" + getX() + ", " + getY() + ", " + orientation.toChar() + ")";
        if(isSunk()){
            str+= " SUNK";
        }
        return str.trim();

    }


}
