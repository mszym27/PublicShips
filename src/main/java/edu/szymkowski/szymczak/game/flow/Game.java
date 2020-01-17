package edu.szymkowski.szymczak.game.flow;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import edu.szymkowski.szymczak.game.model.environment.Board;
import edu.szymkowski.szymczak.game.model.ship.Ship;
import edu.szymkowski.szymczak.game.util.consts.Consts;
import edu.szymkowski.szymczak.game.util.score.Score;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Game {


    private Board board;

    private List<Ship> ships;

    private Score score;


    public Game() {
        score = new Score();
    }
    
    public Game(GridPane gridPane) {
        this.board = convertGridPaneToBoard(gridPane);
        score = new Score();
    }

    private Board convertGridPaneToBoard(GridPane gridPane) {
        Board board = new Board();

        Table<Integer, Integer, Integer> boardTable = HashBasedTable.create();

        for (int i = 0; i < gridPane.getRowConstraints().size(); i++) {
            for (int k = 0; k < gridPane.getRowConstraints().size(); k++) {
                boardTable.put(i, k, getShipTypeAt(gridPane, i, k));
            }
        }
        board.setHitTable(boardTable);

        return board;
    }

    private Integer getShipTypeAt(GridPane gridPane, int i, int k) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof TextFlow) {
                if (GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == k) {
                    return Integer.parseInt(node.getAccessibleText());
                }
            }
        }
        return -1;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public boolean areShipsValid(int smallShipCount, int mediumShipCount, int bigShipCount, int biggestShipCount) {
        boolean isValid = false;

        if (board.isShipsPlacementValid()) {
            try {
                ships = board.getShipsList();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (isShipCountValid(smallShipCount, mediumShipCount, bigShipCount, biggestShipCount)) {
            isValid = true;
        }

        return isValid;
    }

    private boolean isShipCountValid(int smallShipCount, int mediumShipCount, int bigShipCount, int biggestShipCount) {
        int smallCount = 0;
        int mediumCount = 0;
        int bigCount = 0;
        int biggestCount = 0;

        for (Ship ship : ships) {
            if (ship.getLength() == 1) {
                smallCount += 1;
            }
            if (ship.getLength() == 2) {
                mediumCount += 1;
            }
            if (ship.getLength() == 3) {
                bigCount += 1;
            }
            if (ship.getLength() == 4) {
                biggestCount += 1;
            }
        }

        if (smallCount == smallShipCount && mediumCount == mediumShipCount && bigCount == bigShipCount && biggestCount == biggestShipCount) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void replaceGridPaneChildAt(GridPane gridPane, Node node, int x, int y) {
        for (Node gridPaneNode : gridPane.getChildren()) {
            if (GridPane.getRowIndex(gridPaneNode) == x && GridPane.getColumnIndex(gridPaneNode) == y) {
                gridPane.getChildren().remove(gridPaneNode);
                break;
            }
        }
        gridPane.add(node, y, x);
    }

    public static Node getGridPaneChildAt(GridPane gridPane, int x, int y) {
        for (Node gridPaneNode : gridPane.getChildren()) {
            if (GridPane.getRowIndex(gridPaneNode) == x && GridPane.getColumnIndex(gridPaneNode) == y) {
                return gridPaneNode;
            }
        }
        return null;
    }

    public static void printTable(Table<Integer, Integer, Integer> boardTable) {
        for (int x = 0; x < boardTable.rowKeySet().size(); x++) {
            System.out.println();
            for (int y = 0; y < boardTable.columnKeySet().size(); y++) {
                System.out.print(boardTable.get(x, y));
            }
        }
        System.out.println();
    }

    public static void placeShipTile(GridPane gridPane, int x, int y) {
        TextFlow textFlow = new TextFlow();
        textFlow.setAccessibleText(String.valueOf(Consts.BOARD_TILE.SHIP_TILE));
        Game.replaceGridPaneChildAt(gridPane, textFlow, x , y);
    }

    /**
     *
     * @param x - wspolrzedna X.
     * @param y - wspolrzedna Y.
     * @return -1 - w razie błędu, 0 - pudło, 1 - trafienie, 2 - trafienie i zatopienie, 3 - trafienie w trafiony.
     */
    public Consts.HIT_RESULT shotAt(Integer x, Integer y) {

        for (Ship ship: ships) {
            var hitCode = ship.hitAt(x, y);
            if (hitCode == Consts.HIT_RESULT.ALREADY_HIT) {
                score.addEventRecord(LocalDate.now()+ " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-m-s-n")) + ": " + "Ship number: " + ship.getShipId() + " of type: " + ship.getShipType()+ " was hit again on X: " + x + " and Y: " + y + ".");
                return Consts.HIT_RESULT.ALREADY_HIT;
            }
            if (hitCode == Consts.HIT_RESULT.MISS) {
                score.addEventRecord(LocalDate.now()+ " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-m-s-n")) + ": " + "Missed shot on X: " + x + " and Y: " + y + ".");
                return Consts.HIT_RESULT.MISS;
            }
            if (hitCode == Consts.HIT_RESULT.HIT_AND_SUNK) {
                score.addEventRecord(LocalDate.now()+ " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-m-s-n")) + ": " + "Hit and sunk! Ship number: " + ship.getShipId() + " of type: " + ship.getShipType()+ " was hit and sunk on X: " + x + " and Y: " + y + ".");
                return Consts.HIT_RESULT.HIT_AND_SUNK;
            }
            if (hitCode == Consts.HIT_RESULT.HIT) {
                score.addEventRecord(LocalDate.now()+ " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-m-s-n")) + ": " + "Hit! Ship number: " + ship.getShipId() + " of type: " + ship.getShipType()+ " was hit on X: " + x + " and Y: " + y + ".");
                return Consts.HIT_RESULT.HIT;
            }
        }

        return Consts.HIT_RESULT.UNKNOWN;
    }

    public boolean isEnd() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
               return false;
            }
        }
        score.addEventRecord(Consts.GAME_END);
        return true;
    }
}