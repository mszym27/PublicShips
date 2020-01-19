package edu.szymkowski.szymczak.game.model.environment;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import edu.szymkowski.szymczak.game.model.ship.Ship;
import edu.szymkowski.szymczak.game.util.consts.Consts;

import java.util.ArrayList;
import java.util.List;

public class Board {


    private Table hitTable;


    public Board() {
        hitTable = HashBasedTable.create();
    }

    public Table getHitTable() {
        return hitTable;
    }

    public void setHitTable(Table hitTable) {
        this.hitTable = hitTable;
    }

    public boolean isShipsPlacementValid() {
        //TODO make private
        final int boardWidth = hitTable.rowKeySet().size();
        final int boardLength = hitTable.columnKeySet().size();

        for (int i = 0; i < boardWidth; i++) {
            for (int k = 0; k < boardLength; k++) {
                if (hitTable.get(i, k) == Consts.BOARD_TILE.SHIP_TILE) {
                    boolean isVerticalShip = true;
                    boolean isShipOrientationSet = false;
                    if (i - 1 >= 0) {
                        if (hitTable.get(i - 1, k) == Consts.BOARD_TILE.SHIP_TILE) {
                            isShipOrientationSet = true;
                        }
                    }
                    if (i - 1 >= 0 && k + 1 < boardWidth) {
                        if (hitTable.get(i - 1, k + 1) == Consts.BOARD_TILE.SHIP_TILE) {
                            return false;
                        }
                    }
                    if (k + 1 < boardWidth) {
                        if (hitTable.get(i, k + 1) == Consts.BOARD_TILE.SHIP_TILE && isVerticalShip && isShipOrientationSet) {
                            return false;
                        }
                        else if (hitTable.get(i, k + 1) == Consts.BOARD_TILE.SHIP_TILE && !isShipOrientationSet){
                            isVerticalShip = false;
                            isShipOrientationSet = true;
                        }
                    }
                    if (i + 1 < boardLength && k + 1 < boardWidth) {
                        if (hitTable.get(i + 1, k + 1) == Consts.BOARD_TILE.SHIP_TILE) {
                            return false;
                        }
                    }
                    if (i + 1 < boardLength) {
                        if (hitTable.get(i + 1, k) == Consts.BOARD_TILE.SHIP_TILE && isShipOrientationSet && !isVerticalShip) {
                            return false;
                        }
                        else if (hitTable.get(i + 1, k) == Consts.BOARD_TILE.SHIP_TILE && !isShipOrientationSet) {
                            isVerticalShip = true;
                            isShipOrientationSet = true;
                        }
                    }
                    if (i + 1 >= 0 && k - 1 >= 0) {
                        if (hitTable.get(i + 1, k - 1) == Consts.BOARD_TILE.SHIP_TILE) {
                            return false;
                        }
                    }
                    if (k - 1 >= 0) {
                        if (hitTable.get(i, k - 1) == Consts.BOARD_TILE.SHIP_TILE && isShipOrientationSet && isVerticalShip) {
                            return false;
                        }
                        else if (hitTable.get(i, k - 1) == Consts.BOARD_TILE.SHIP_TILE && !isShipOrientationSet) {
                            isVerticalShip = false;
                            isShipOrientationSet = true;
                        }
                    }
                    if (i - 1 >= 0 && k - 1 >= 0) {
                        if (hitTable.get(i - 1, k - 1) == Consts.BOARD_TILE.SHIP_TILE) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public List<Ship> getShipsList() throws Exception {
        int shipId = 1;
        List<Ship> shipList  = new ArrayList<>();
        Table<Integer, Integer, Integer> shipTable = HashBasedTable.create();
        shipTable.putAll(hitTable);
        int boardLength = shipTable.rowKeySet().size();
        int boardWidth =  shipTable.columnKeySet().size();

        for (int i = 0; i < boardLength; i++) {
            for (int k = 0; k < boardWidth; k++) {
                Ship ship = new Ship();
                if (shipTable.get(i, k).intValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                    ship.putMastAt(i, k);
                    shipTable.put(i, k, Consts.BOARD_TILE.BLANK_TILE.value);
                    if (k + 1 < boardWidth) {
                        if (shipTable.get(i, k + 1).intValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                            for (int s = k + 1; s < boardWidth; s++) {
                                if (shipTable.get(i, s).intValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                                    ship.putMastAt(i, s);
                                    shipTable.put(i, s, Consts.BOARD_TILE.BLANK_TILE.value);
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }
                    if (i + 1 < boardLength) {
                        if (shipTable.get(i + 1, k).intValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                            for (int s = i + 1; s < boardLength; s++) {
                                if (shipTable.get(s, k).intValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                                    ship.putMastAt(s, k);
                                    shipTable.put(s, k, Consts.BOARD_TILE.BLANK_TILE.value);
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }
                    if (ship.getLength() > Consts.SHIP_LENGTH.BIGGEST.value) {
                        throw new Exception(Consts.ERROR_MESSAGE.INVALID_SHIP_SIZE.value);
                    }
                    ship.setShipId(shipId);
                    shipId += 1;
                    shipList.add(ship);
                }
            }
        }
        if (shipList.size() < 1) {
            throw new Exception(Consts.ERROR_MESSAGE.INVALID_SHIP_COUNT.value);
        }
        return shipList;
    }
}