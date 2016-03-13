package net.thoughtmachine.exception;


/**
 * Exception for when a ship already exists in coordinates that it was attempted to be placed in
 */
public class ShipExistsInCoordsException extends InvalidShipPlacementException {

    public ShipExistsInCoordsException() {
        super("Battleship already exists in the given coordinates.");
    }
}
