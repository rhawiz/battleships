package net.thoughtmachine.ship;

import net.thoughtmachine.datatype.Coordinate;
import net.thoughtmachine.util.Consts;
import net.thoughtmachine.util.Consts.Rotation;
import net.thoughtmachine.util.Consts.Orientation;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Battleship Test
 *
 * @author rhawiz
 */
public class BattleshipTest {
    @Test
    public void BattleshipRotateLeftTest() {
        Battleship battleship1 = new Battleship(new Coordinate(1, 1), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(1, 1), Consts.Orientation.EAST);
        Battleship battleship3 = new Battleship(new Coordinate(1, 1), Consts.Orientation.SOUTH);
        Battleship battleship4 = new Battleship(new Coordinate(1, 1), Consts.Orientation.WEST);

        battleship1.rotate(Rotation.LEFT);
        battleship2.rotate(Rotation.LEFT);
        battleship3.rotate(Rotation.LEFT);
        battleship4.rotate(Rotation.LEFT);

        assertEquals(battleship1.getOrientation(), Orientation.WEST);
        assertEquals(battleship2.getOrientation(), Orientation.NORTH);
        assertEquals(battleship3.getOrientation(), Orientation.EAST);
        assertEquals(battleship4.getOrientation(), Orientation.SOUTH);

    }

    @Test
    public void BattleshipRotateRightTest() {
        Battleship battleship1 = new Battleship(new Coordinate(1, 1), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(1, 1), Consts.Orientation.EAST);
        Battleship battleship3 = new Battleship(new Coordinate(1, 1), Consts.Orientation.SOUTH);
        Battleship battleship4 = new Battleship(new Coordinate(1, 1), Consts.Orientation.WEST);

        battleship1.rotate(Rotation.RIGHT);
        battleship2.rotate(Rotation.RIGHT);
        battleship3.rotate(Rotation.RIGHT);
        battleship4.rotate(Rotation.RIGHT);

        assertEquals(battleship1.getOrientation(), Orientation.EAST);
        assertEquals(battleship2.getOrientation(), Orientation.SOUTH);
        assertEquals(battleship3.getOrientation(), Orientation.WEST);
        assertEquals(battleship4.getOrientation(), Orientation.NORTH);

    }

    @Test
    public void BattleshipMoveCoordinateTest() {
        Battleship battleship1 = new Battleship(new Coordinate(1, 1), Consts.Orientation.NORTH);
        Battleship battleship2 = new Battleship(new Coordinate(1, 1), Consts.Orientation.EAST);
        Battleship battleship3 = new Battleship(new Coordinate(1, 1), Consts.Orientation.SOUTH);
        Battleship battleship4 = new Battleship(new Coordinate(1, 1), Consts.Orientation.WEST);

        battleship1.move();
        battleship2.move();
        battleship3.move();
        battleship4.move();

        assertEquals(battleship1.getCoordinates(), new Coordinate(1, 2));
        assertEquals(battleship2.getCoordinates(), new Coordinate(2, 1));
        assertEquals(battleship3.getCoordinates(), new Coordinate(1, 0));
        assertEquals(battleship4.getCoordinates(), new Coordinate(0, 1));


    }


}
