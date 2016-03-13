package net.thoughtmachine.board;

import net.thoughtmachine.ship.Battleship;
import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.exception.ShipExistsInCoordsException;
import net.thoughtmachine.exception.ShipOutOfBoundsExeception;
import net.thoughtmachine.exception.InvalidShipPlacementException;
import net.thoughtmachine.ship.IShip;
import net.thoughtmachine.util.Consts;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test multiple aspects of the board
 *
 * @author rhawiz
 */
public class BoardTest {

    @Test
    public void PopulateBoardTest() throws InvalidShipPlacementException {
        Board board = new Board(10);

        List<IShip> battleshipList = new ArrayList<IShip>();
        Battleship battleship1 = new Battleship(new Coordinate(1, 2), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(2, 2), Consts.Orientation.EAST);
        Battleship battleship3 = new Battleship(new Coordinate(4, 2), Consts.Orientation.SOUTH);
        Battleship battleship4 = new Battleship(new Coordinate(1, 5), Consts.Orientation.WEST);
        Battleship battleship5 = new Battleship(new Coordinate(9, 9), Consts.Orientation.WEST);

        battleshipList.add(battleship1);
        battleshipList.add(battleship2);
        battleshipList.add(battleship3);
        battleshipList.add(battleship4);
        battleshipList.add(battleship5);

        board.populate(battleshipList);

        assertEquals(battleship1, board.getShip(new Coordinate(1, 2)));
        assertEquals(battleship2, board.getShip(new Coordinate(2, 2)));
        assertEquals(battleship3, board.getShip(new Coordinate(4, 2)));
        assertEquals(battleship4, board.getShip(new Coordinate(1, 5)));
        assertEquals(battleship5, board.getShip(new Coordinate(9, 9)));

    }

    @Test(expected = ShipOutOfBoundsExeception.class)
    public void BattleshipOutOfBoundsTest() throws InvalidShipPlacementException {
        Board board = new Board(5);
        Battleship battleship1 = new Battleship(new Coordinate(5, 3), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(1, 5), Consts.Orientation.NORTH);
        board.addShip(battleship1);
        board.addShip(battleship2);
    }

    @Test(expected = ShipExistsInCoordsException.class)
    public void BattleshipExistsInCoordsTest() throws InvalidShipPlacementException {
        Board board = new Board(5);
        Battleship battleship1 = new Battleship(new Coordinate(3, 3), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(3, 3), Consts.Orientation.NORTH);
        board.addShip(battleship1);
        board.addShip(battleship2);
    }

    @Test
    public void ShootBattleshipTest() throws InvalidShipPlacementException {
        Board board = new Board(5);
        Battleship battleship1 = new Battleship(new Coordinate(2, 3), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(1, 3), Consts.Orientation.SOUTH);
        board.addShip(battleship1);
        board.addShip(battleship2);
        board.shoot(new Coordinate(2, 3));

        assertNull(board.getShip(new Coordinate(2, 3)));
        assertTrue(battleship1.isSunk());
    }

    @Test
    public void MoveBattleshipTest() throws InvalidShipPlacementException {
        Board board = new Board(10);
        Battleship battleship1 = new Battleship(new Coordinate(2, 2), Consts.Orientation.SOUTH);
        Battleship battleship2 = new Battleship(new Coordinate(3, 1), Consts.Orientation.NORTH);
        board.addShip(battleship1);
        board.addShip(battleship2);
        board.moveShip(new Coordinate(2, 2), "MLMMRM");
        board.moveShip(new Coordinate(3, 1), "MRMMRMMLM");


        assertEquals(battleship1.getCoordinates(), new Coordinate(4, 0));
        assertEquals(battleship1.getOrientation(), Consts.Orientation.SOUTH);

        assertEquals(battleship2.getCoordinates(), new Coordinate(6, 0));
        assertEquals(battleship2.getOrientation(), Consts.Orientation.EAST);
    }


    public static void main(String... args) {
        BoardTest test = new BoardTest();
        try {
            test.PopulateBoardTest();
        } catch (InvalidShipPlacementException e) {
            e.printStackTrace();
        }

    }
}
