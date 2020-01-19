package edu.szymkowski.szymczak.game.model.ship;

import edu.szymkowski.szymczak.game.util.consts.Consts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShipTests {

    @Test
    public void ShipHitUpperLeftTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);

        Assertions.assertEquals(ship.hitAt(0,0), Consts.HIT_RESULT.HIT);
    }

    @Test
    public void ShipHitUpperLeftAndMiddleHitAndSunkTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);

        Assertions.assertEquals(ship.hitAt(0,1), Consts.HIT_RESULT.HIT_AND_SUNK);
    }

    @Test
    public void ShipHitUpperLeftAndMiddleAlreadyHitTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);

        Assertions.assertEquals(ship.hitAt(0,0), Consts.HIT_RESULT.ALREADY_HIT);
    }

    @Test
    public void ShipHitUpperLeftAndMiddleMissTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);

        Assertions.assertEquals(ship.hitAt(0,3), Consts.HIT_RESULT.MISS);
    }

    @Test
    public void ShipHitTestUpperLeftAndRightMissTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);
        ship.hitAt(0, 1);

        Assertions.assertEquals(ship.hitAt(0,2), Consts.HIT_RESULT.MISS);
    }

    @Test
    public void ShipSunkUpperLeftAndRightTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);
        ship.hitAt(0, 1);

        Assertions.assertEquals(ship.isSunk(), true);
    }

    @Test
    public void ShipSunkMiddleTest() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);
        Assertions.assertEquals(ship.isSunk(), false);
    }
}