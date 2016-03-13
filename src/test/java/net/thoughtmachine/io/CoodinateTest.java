package net.thoughtmachine.io;

import net.thoughtmachine.datatype.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author rhawiz
 */
public class CoodinateTest {


    @Test
    public void CoordinateEqualityTest() {
        Coordinate coords1 = new Coordinate(1, 3);
        Coordinate coords2 = new Coordinate(3, 1);
        Coordinate coords3 = new Coordinate(1, 3);
        Coordinate coords4 = new Coordinate(1000, 200);
        Coordinate coords5 = new Coordinate(1000, 200);

        assertTrue(coords1.equals(coords3));
        assertTrue(coords4.equals(coords5));
        assertFalse(coords1.equals(coords2));
    }



}

