package edu.szymkowski.szymczak.game.flow;

import edu.szymkowski.szymczak.game.controller.StageController;
import edu.szymkowski.szymczak.game.util.consts.Consts;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTests {

    private final static int BOARD_LENGTH = 5;

    private final static int BOARD_WIDTH = 5;

    private static Game game;

    @BeforeEach
    public void setup() {
        GridPane gridPane = new GridPane();

        StageController.fillEmptyBoard(gridPane, BOARD_LENGTH, BOARD_WIDTH);
        Game.placeShipTile(gridPane, 0, 0);
        Game.placeShipTile(gridPane, 0, 1);
        Game.placeShipTile(gridPane, 0, 2);
        Game.placeShipTile(gridPane, 2, 0);
        Game.placeShipTile(gridPane, 2, 1);

        game = new Game(gridPane);
        game.areShipsValid(0, 1, 1, 0);
    }

    @Test
    public void GameFlowHitCenterTest() {
        Assertions.assertEquals(game.shotAt(0, 0), Consts.HIT_RESULT.HIT);
    }

    @Test
    public void GameFlowHitAndSunkCenterUpperMiddleTest() {
        game.shotAt(0, 0);
        game.shotAt(0, 1);
        Assertions.assertEquals(game.shotAt(0, 2), Consts.HIT_RESULT.HIT_AND_SUNK);
    }

    @Test
    public void GameFlowMissUpperRightTest() {
        Assertions.assertEquals(game.shotAt(0, 3), Consts.HIT_RESULT.MISS);
    }

    @Test
    public void GameFlowAlreadyHitCenterTest() {
        game.shotAt(0, 0);
        Assertions.assertEquals(game.shotAt(0, 0), Consts.HIT_RESULT.ALREADY_HIT);
    }

    @Test
    public void GameFlowBigBoardMissTest() {
        Assertions.assertEquals(game.shotAt(10, 0), Consts.HIT_RESULT.MISS);
    }
}