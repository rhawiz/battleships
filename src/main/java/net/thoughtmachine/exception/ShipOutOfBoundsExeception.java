package net.thoughtmachine.exception;

/**
 * Exception for when a ship is attempted to be placed out of the game boards dimensions.
 */
public class ShipOutOfBoundsExeception extends InvalidShipPlacementException {

    public ShipOutOfBoundsExeception(){
        super("Battleship out of boards boundary.");
    }
}
