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
        switch (masts.size()) {
            case Consts.SMALL_SHIP_LENGTH:
                return Consts.SMALL_SHIP;
            case Consts.MEDIUM_SHIP_LENGTH:
                return Consts.MEDIUM_SHIP;
            case Consts.BIG_SHIP_LENGTH:
                return Consts.BIG_SHIP;
            case Consts.BIGGEST_SHIP_LENGTH:
                return Consts.BIGGEST_SHIP;
        }

        return Consts.INVALID_SHIP;
    }

    public void putMastAt(int x, int y) {
        masts.put(x , y, Consts.SHIP_TILE);
    }

    /**
     *
     * @param x - współrzędna X kafelka statku.
     * @param y - współrzędna Y kafelka statku.
     * @return 3 - trafiony już trafiony, 2 - trafiony/zatopiony, 1 - trafiony, 0 - pudło.
     */
    public int hitAt(int x, int y) {
        Set<Table.Cell<Integer, Integer, Integer>> cellSet = masts.cellSet();
        for(Table.Cell tableCell : cellSet) {
            int cellX = Integer.parseInt(String.valueOf(tableCell.getRowKey()));
            int cellY = Integer.parseInt(String.valueOf(tableCell.getColumnKey()));

            if (cellX == x && cellY == y) {
                if (tableCell.getValue() == Consts.BLANK_TILE) {
                    return Consts.MISS;
                }
                if (tableCell.getValue() == Consts.HIT_TILE) {
                    return Consts.ALREADY_HIT;
                }
                if (tableCell.getValue() == Consts.SHIP_TILE) {
                    masts.remove(x, y);
                    masts.put(x, y, Consts.HIT_TILE);
                    Set<Table.Cell<Integer, Integer, Integer>> cellSet2 = masts.cellSet();
                    for(Table.Cell tableCell2 : cellSet2) {
                        if (tableCell2.getValue() == Consts.SHIP_TILE) {
                            return Consts.HIT;
                        }
                    }
                    return Consts.HIT_AND_SUNK;
                }
            }
        }

        return Consts.MISS;
    }

    public boolean isSunk() {
        for (Table.Cell<Integer, Integer, Integer> cell : masts.cellSet()) {
            if (cell.getValue() == Consts.SHIP_TILE) {
                return false;
            }
        }

        return true;
    }
}
