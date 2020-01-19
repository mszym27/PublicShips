package edu.szymkowski.szymczak.game.model.ship;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import edu.szymkowski.szymczak.game.util.consts.Consts;

import java.util.Set;

public class Ship {

    private Table<Integer, Integer, Integer> masts;

    private int shipId;

    public Ship () {
        masts = HashBasedTable.create();
    }

    public int getLength() {
        return  masts.size();
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getShipType() {
        int mastsSize = masts.size();

        if(mastsSize == Consts.SHIP_LENGTH.SMALL.value)
        {
            return Consts.SMALL_SHIP;
        }
            else if (mastsSize == Consts.SHIP_LENGTH.MEDIUM.value) {
                return Consts.MEDIUM_SHIP;
            }
                else if (mastsSize == Consts.SHIP_LENGTH.BIG.value) {
                    return Consts.BIG_SHIP;
                }
                    else if (mastsSize == Consts.SHIP_LENGTH.BIGGEST.value) {
                        return Consts.BIGGEST_SHIP;
                    }

        return Consts.INVALID_SHIP;
    }

    public void putMastAt(int x, int y) {
        masts.put(x , y, Consts.BOARD_TILE.SHIP_TILE.value);
    }

    /**
     *
     * @param x - współrzędna X kafelka statku.
     * @param y - współrzędna Y kafelka statku.
     * @return 3 - trafiony już trafiony, 2 - trafiony/zatopiony, 1 - trafiony, 0 - pudło.
     */
    public Consts.HIT_RESULT hitAt(int x, int y) {
        Set<Table.Cell<Integer, Integer, Integer>> cellSet = masts.cellSet();
        for(Table.Cell tableCell : cellSet) {
            int cellX = Integer.parseInt(String.valueOf(tableCell.getRowKey()));
            int cellY = Integer.parseInt(String.valueOf(tableCell.getColumnKey()));

            if (cellX == x && cellY == y) {
                if ((int) tableCell.getValue() == Consts.BOARD_TILE.BLANK_TILE.value) {
                    return Consts.HIT_RESULT.MISS;
                }
                if ((int) tableCell.getValue() == Consts.BOARD_TILE.HIT_TILE.value) {
                    return Consts.HIT_RESULT.ALREADY_HIT;
                }
                if ((int) tableCell.getValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                    masts.remove(x, y);
                    masts.put(x, y, Consts.BOARD_TILE.HIT_TILE.value);
                    Set<Table.Cell<Integer, Integer, Integer>> cellSet2 = masts.cellSet();
                    for(Table.Cell tableCell2 : cellSet2) {
                        if ((int) tableCell2.getValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                            return Consts.HIT_RESULT.HIT;
                        }
                    }
                    return Consts.HIT_RESULT.HIT_AND_SUNK;
                }
            }
        }

        return Consts.HIT_RESULT.MISS;
    }

    public boolean isSunk() {
        for (Table.Cell<Integer, Integer, Integer> cell : masts.cellSet()) {
            if (cell.getValue() == Consts.BOARD_TILE.SHIP_TILE.value) {
                return false;
            }
        }

        return true;
    }
}
