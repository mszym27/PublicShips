package edu.szymkowski.szymczak.game.model.environment;

import edu.szymkowski.szymczak.game.controller.StageController;
import edu.szymkowski.szymczak.game.flow.Game;

import edu.szymkowski.szymczak.game.model.ship.Ship;
import javafx.scene.layout.GridPane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class BoardTests {

    private final static int BOARD_LENGTH = 5;

    private final static int BOARD_WIDTH = 5;

    private static Game game;

    @BeforeAll
    public static void setup() {
        game = new Game();
    }

    @Test
    public void ShipsPlacementTest1() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 0, 1);
        Game.placeShipTile(gridPane, 0, 2);
        Game.placeShipTile(gridPane, 2, 0);
        Game.placeShipTile(gridPane, 2, 1);

        game = new Game(gridPane);
        Board initializedBoard = game.getBoard();

        assert(((int)initializedBoard.getHitTable().get(0, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(0, 1)) == 1 &&
                ((int)initializedBoard.getHitTable().get(0, 2)) == 1 &&
                ((int)initializedBoard.getHitTable().get(2, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(2, 1)) == 1 );
    }

    @Test
    public void ShipsPlacementTest2() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 0, 1);
        Game.placeShipTile(gridPane, 0, 2);
        Game.placeShipTile(gridPane, 0, 3);
        Game.placeShipTile(gridPane, 0, 4);

        game = new Game(gridPane);
        Board initializedBoard = game.getBoard();

        assert(((int)initializedBoard.getHitTable().get(0, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(0, 1)) == 1 &&
                ((int)initializedBoard.getHitTable().get(0, 2)) == 1 &&
                ((int)initializedBoard.getHitTable().get(0, 3)) == 1 &&
                ((int)initializedBoard.getHitTable().get(0, 4)) == 1 );
    }

    @Test
    public void ShipsPlacementTest3() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 1, 0);
        Game.placeShipTile(gridPane, 2, 0);
        Game.placeShipTile(gridPane, 3, 0);
        Game.placeShipTile(gridPane, 4, 0);

        game = new Game(gridPane);
        Board initializedBoard = game.getBoard();

        assert(((int)initializedBoard.getHitTable().get(0, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(1, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(2, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(3, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(4, 0)) == 1 );
    }

    @Test
    public void ShipsPlacementTest4() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 4, 0);
        Game.placeShipTile(gridPane, 4, 1);
        Game.placeShipTile(gridPane, 4, 2);
        Game.placeShipTile(gridPane, 4, 3);
        Game.placeShipTile(gridPane, 4, 4);

        game = new Game(gridPane);
        Board initializedBoard = game.getBoard();

        assert(((int)initializedBoard.getHitTable().get(4, 0)) == 1 &&
                ((int)initializedBoard.getHitTable().get(4, 1)) == 1 &&
                ((int)initializedBoard.getHitTable().get(4, 2)) == 1 &&
                ((int)initializedBoard.getHitTable().get(4, 3)) == 1 &&
                ((int)initializedBoard.getHitTable().get(4, 4)) == 1 );
    }

    @Test
    public void ShipsPlacementTest5() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 4);
        Game.placeShipTile(gridPane, 1, 4);
        Game.placeShipTile(gridPane, 2, 4);
        Game.placeShipTile(gridPane, 3, 4);
        Game.placeShipTile(gridPane, 4, 4);

        game = new Game(gridPane);
        Board initializedBoard = game.getBoard();

        assert(((int)initializedBoard.getHitTable().get(0, 4)) == 1 &&
                ((int)initializedBoard.getHitTable().get(1, 4)) == 1 &&
                ((int)initializedBoard.getHitTable().get(2, 4)) == 1 &&
                ((int)initializedBoard.getHitTable().get(3, 4)) == 1 &&
                ((int)initializedBoard.getHitTable().get(4, 4)) == 1 );
    }

    @Test
    public void ShipsDoublePlacementTest1() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 4, 4);
        Game.placeShipTile(gridPane, 4, 4);

        game = new Game(gridPane);
        Board initializedBoard = game.getBoard();

        assert(((int)initializedBoard.getHitTable().get(4, 4)) == 1 );
    }

    @Test
    public void ShipsInvalidPlacementTest1() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 1, 1);

        game = new Game(gridPane);

        assert(!game.getBoard().isShipsPlacementValid());
    }

    @Test
    public void ShipsInvalidPlacementTest2() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 0, 1);
        Game.placeShipTile(gridPane, 0, 2);
        Game.placeShipTile(gridPane, 1, 1);

        game = new Game(gridPane);

        assert(!game.getBoard().isShipsPlacementValid());
    }

    @Test
    public void ShipsInvalidPlacementTest3() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 4, 4);
        Game.placeShipTile(gridPane, 4, 3);
        Game.placeShipTile(gridPane, 3, 4);

        game = new Game(gridPane);

        assert(!game.getBoard().isShipsPlacementValid());
    }

    @Test
    public void ShipsValidPlacementTest1() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 1, 0);
        Game.placeShipTile(gridPane, 2, 0);
        Game.placeShipTile(gridPane, 0, 2);

        game = new Game(gridPane);

        assert(game.getBoard().isShipsPlacementValid());
    }

    @Test
    public void ShipsValidPlacementTest2() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 1, 0);
        Game.placeShipTile(gridPane, 2, 0);
        Game.placeShipTile(gridPane, 1, 2);
        Game.placeShipTile(gridPane, 1, 3);
        Game.placeShipTile(gridPane, 1, 4);

        game = new Game(gridPane);

        assert(game.getBoard().isShipsPlacementValid());
    }

    @Test
    public void ShipsValidPlacementTest3() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 4, 2);
        Game.placeShipTile(gridPane, 4, 3);
        Game.placeShipTile(gridPane, 4, 4);
        Game.placeShipTile(gridPane, 0, 4);
        Game.placeShipTile(gridPane, 1, 4);
        Game.placeShipTile(gridPane, 2, 4);

        game = new Game(gridPane);

        assert(game.getBoard().isShipsPlacementValid());
    }

    @Test
    public void ShipsListValidTest1() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 4, 2);
        Game.placeShipTile(gridPane, 4, 3);
        Game.placeShipTile(gridPane, 4, 4);
        Game.placeShipTile(gridPane, 0, 4);
        Game.placeShipTile(gridPane, 1, 4);
        Game.placeShipTile(gridPane, 2, 4);

        boolean isValid = false;
        List<Ship> shipList;
        game = new Game(gridPane);
        try {
            shipList = game.getBoard().getShipsList();
            if (shipList.size() == 2) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }

        assert(isValid);
    }

    @Test
    public void ShipsListValidTest2() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 1);
        Game.placeShipTile(gridPane, 0, 2);
        Game.placeShipTile(gridPane, 0, 3);
        Game.placeShipTile(gridPane, 2, 0);
        Game.placeShipTile(gridPane, 4, 0);
        Game.placeShipTile(gridPane, 4, 4);

        boolean isValid = false;
        List<Ship> shipList;
        game = new Game(gridPane);
        try {
            shipList = game.getBoard().getShipsList();
            if (shipList.size() == 4) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }

        assert(isValid);
    }

    @Test
    public void ShipsListValidTest3() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 0, 2);
        Game.placeShipTile(gridPane, 4, 4);
        Game.placeShipTile(gridPane, 4, 2);
        Game.placeShipTile(gridPane, 2, 4);

        boolean isValid = false;
        List<Ship> shipList;
        game = new Game(gridPane);
        try {
            shipList = game.getBoard().getShipsList();
            if (shipList.size() == 5) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }

        assert(isValid);
    }

    @Test
    public void ShipsListValidTest4() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 1, 2);
        Game.placeShipTile(gridPane, 2, 4);
        Game.placeShipTile(gridPane, 3, 0);
        Game.placeShipTile(gridPane, 4, 2);

        boolean isValid = false;
        List<Ship> shipList;
        game = new Game(gridPane);
        try {
            shipList = game.getBoard().getShipsList();
            if (shipList.size() == 5) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert(false);
        }

        assert(isValid);
    }

    @Test
    public void ShipsListInvalidTest1() {
        GridPane gridPane = new GridPane();
        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);

        game = new Game(gridPane);

        Assertions.assertThrows(Exception.class, () -> game.getBoard().getShipsList());
    }
}