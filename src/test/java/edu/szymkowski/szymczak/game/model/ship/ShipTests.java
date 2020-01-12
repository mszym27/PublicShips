package edu.szymkowski.szymczak.game.model.ship;

import edu.szymkowski.szymczak.game.util.consts.Consts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShipTests {

    @Test
    public void ShipHitTest1() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);

        Assertions.assertEquals(ship.hitAt(0,0), Consts.HIT);
    }

    @Test
    public void ShipHitTest2() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);

        Assertions.assertEquals(ship.hitAt(0,1), Consts.HIT_AND_SUNK);
    }

    @Test
    public void ShipHitTest3() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);

        Assertions.assertEquals(ship.hitAt(0,0), Consts.ALREADY_HIT);
    }

    @Test
    public void ShipHitTest4() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);

        Assertions.assertEquals(ship.hitAt(0,3), Consts.MISS);
    }

    @Test
    public void ShipHitTest5() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);
        ship.hitAt(0, 1);

        Assertions.assertEquals(ship.hitAt(0,2), Consts.MISS);
    }

    @Test
    public void ShipSunkTest1() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);
        ship.hitAt(0, 1);

        Assertions.assertEquals(ship.isSunk(), true);
    }

    @Test
    public void ShipSunkTest2() {
        Ship ship = new Ship();
        ship.putMastAt(0,0);
        ship.putMastAt(0,1);
        ship.hitAt(0, 0);
        Assertions.assertEquals(ship.isSunk(), false);
    }
}