package edu.szymkowski.szymczak.game.controller;

import edu.szymkowski.szymczak.game.flow.Game;
import edu.szymkowski.szymczak.game.util.consts.Consts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextFlow;

public class StageController {


    private Game game;

    @FXML
    private GridPane board;

    @FXML
    private TextArea messageBox;

    @FXML
    private Button applyPlacementButton;

    @FXML
    private Button cancelPlacementButton;

    @FXML
    private Spinner smallShipSpinner;

    @FXML
    private Spinner mediumShipSpinner;

    @FXML
    private Spinner bigShipSpinner;

    @FXML
    private Spinner biggestShipSpinner;

    @FXML
    private Button applyShipSizeButton;

    @FXML
    private Button cancelShipSizeButton;

    @FXML
    private Spinner boardLengthSpinner;

    @FXML
    private Spinner boardWidthSpinner;

    @FXML
    private Button applyBoardSizeButton;

    @FXML
    private Button cancelBoardSizeButton;

    @FXML
    private CheckBox autoCheckBox;

    @FXML
    private CheckBox manualCheckBox;

    @FXML
    private Button applyPlacementModeButton;

    @FXML
    private Button cancelPlacementModeButton;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button statisticsButton;

    private boolean isBattle = false;


    public StageController() {}

    @FXML
    public void initialize() {
        this.game = new Game();
        initSpinners();
    }

    private void initSpinners() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Consts.MAX_SMALL_SHIP_AMOUNT, 0);
        smallShipSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Consts.MAX_MEDIUM_SHIP_AMOUNT, 0);
        mediumShipSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Consts.MAX_BIG_SHIP_AMOUNT, 0);
        bigShipSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Consts.MAX_BIGGEST_SHIP_AMOUNT, 0);
        biggestShipSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(Consts.MIN_BOARD_LENGTH, Consts.MAX_BOARD_LENGTH, 0);
        boardLengthSpinner.setValueFactory(valueFactory);
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(Consts.MIN_BOARD_WIDTH, Consts.MAX_BOARD_WIDTH, 0);
        boardWidthSpinner.setValueFactory(valueFactory);
    }

    public void handlePlayButtonAction(ActionEvent actionEvent) {
        game = new Game(board);
        int smallShipCount = Integer.parseInt(String.valueOf(smallShipSpinner.getValue()));
        int mediumShipCount = Integer.parseInt(String.valueOf(mediumShipSpinner.getValue()));
        int bigShipCount = Integer.parseInt(String.valueOf(bigShipSpinner.getValue()));
        int biggestShipCount = Integer.parseInt(String.valueOf(biggestShipSpinner.getValue()));

        if (game.areShipsValid(smallShipCount, mediumShipCount, bigShipCount, biggestShipCount)) {
            disableSettingsControls();
            exitButton.setDisable(false);
            board.setDisable(false);
            isBattle = true;
            messageBox.setText(Consts.GAME_START);
            printBoard();
        }
        else {
            messageBox.appendText(Consts.ERROR_MESSAGE.SHIP_PLACEMENT_INVALID.value);
        }

        System.out.println(actionEvent.toString());
    }

    public void handleExitButtonAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.toString());
        System.exit(0);
    }

    private void disableSettingsControls() {
        cancelShipSizeButton.setDisable(true);
        cancelBoardSizeButton.setDisable(true);
        cancelPlacementModeButton.setDisable(true);
        cancelPlacementButton.setDisable(true);
        statisticsButton.setDisable(false);
        playButton.setDisable(true);
    }

    public void handleStatisticsButtonAction(ActionEvent actionEvent) {
        game.getScore().openScore();
        System.out.println(actionEvent.toString());
    }

    public void handleApplyPlacementModeButtonAction(ActionEvent actionEvent) {
        autoCheckBox.setDisable(true);
        manualCheckBox.setDisable(true);
        cancelPlacementModeButton.setDisable(false);
        applyPlacementModeButton.setDisable(true);
        checkBoardAvailability();
        System.out.println(actionEvent.toString());
    }

    public void handleCancelPlacementModeButtonAction(ActionEvent actionEvent) {
        autoCheckBox.setDisable(false);
        manualCheckBox.setDisable(false);
        applyPlacementModeButton.setDisable(false);
        cancelPlacementModeButton.setDisable(true);
        checkBoardAvailability();
        System.out.println(actionEvent.toString());
    }

    private void checkBoardAvailability() {
        if (applyShipSizeButton.isDisable() && applyBoardSizeButton.isDisable() &&
        applyPlacementModeButton.isDisable()) {
            ShowBoard();
        }
        else {
            board.setDisable(true);
            applyPlacementButton.setDisable(true);
        }
    }

    private void ShowBoard() {
        applyPlacementButton.setDisable(false);
        cancelPlacementButton.setDisable(true);
        printBoard();
    }

    private void printBoard() {
        final int boardLength = Integer.parseInt(String.valueOf(boardLengthSpinner.getValue()));
        final int boardWidth = Integer.parseInt(String.valueOf(boardWidthSpinner.getValue()));

        cleanBoard();
        fillEmptyBoard(board, boardLength, boardWidth);
        board.setDisable(false);
    }

    public static void fillEmptyBoard(GridPane board, int boardLength, int boardWidth) {
        for (int i = 0; i < boardLength; i++) {
            board.getRowConstraints().add(new RowConstraints(Consts.PREF_TILE_HEIGHT, Consts.PREF_TILE_WIDTH, Double.MAX_VALUE));
        }

        for (int k = 0; k < boardWidth; k++) {
            board.getColumnConstraints().add(new ColumnConstraints(Consts.PREF_TILE_HEIGHT, Consts.PREF_TILE_WIDTH, Double.MAX_VALUE));
        }

        for (int i = 0; i < boardLength; i++) {
            for (int k = 0; k < boardWidth; k++) {
                //TextFlow do przechowywania koloru oraz rodzaju kafelka.
                TextFlow textFlow = new TextFlow();
                textFlow.setAccessibleText(String.valueOf(Consts.BOARD_TILE.BLANK_TILE));
                board.add(textFlow, k , i);
            }
        }
    }

    private void cleanBoard() {
        int rowLength = board.getRowConstraints().size();
        int columnLength = board.getColumnConstraints().size();

        for (int i = 0; i < rowLength; i++) {
            board.getRowConstraints().remove(0);
        }

        for (int k = 0; k < columnLength; k++) {
            board.getColumnConstraints().remove(0);
        }

        Node node = new Group();
        for (Node nod : board.getChildren()) {
            if (nod instanceof Group) {
                node = nod;
                break;
            }
        }
        board.getChildren().clear();
        board.getChildren().add(node);
    }

    public void handleApplyBoardSizeButtonAction(ActionEvent actionEvent) {
        applyBoardSizeButton.setDisable(true);
        cancelBoardSizeButton.setDisable(false);
        boardLengthSpinner.setDisable(true);
        boardWidthSpinner.setDisable(true);
        checkBoardAvailability();
        System.out.println(actionEvent.toString());
    }

    public void handleCancelBoardSizeButtonAction(ActionEvent actionEvent) {
        cancelBoardSizeButton.setDisable(true);
        applyBoardSizeButton.setDisable(false);
        boardLengthSpinner.setDisable(false);
        boardWidthSpinner.setDisable(false);
        checkBoardAvailability();
        System.out.println(actionEvent.toString());
    }

    public void handleAutoCheckBoxAction(ActionEvent actionEvent) {
        if (autoCheckBox.isSelected()) {
            manualCheckBox.setSelected(false);
        }
        else {
            manualCheckBox.setSelected(true);
        }
        System.out.println(actionEvent.toString());
    }

    public void handleManualCheckBoxAction(ActionEvent actionEvent) {
        if (manualCheckBox.isSelected()) {
            autoCheckBox.setSelected(false);
        }
        else {
            autoCheckBox.setSelected(true);
        }
        System.out.println(actionEvent.toString());
    }

    public void handleApplyShipSizeButtonAction(ActionEvent actionEvent) {
        applyShipSizeButton.setDisable(true);
        cancelShipSizeButton.setDisable(false);
        disableShipSizeSpinners();
        checkBoardAvailability();
        System.out.println(actionEvent.toString());
    }

    private void disableShipSizeSpinners() {
        smallShipSpinner.setDisable(true);
        mediumShipSpinner.setDisable(true);
        bigShipSpinner.setDisable(true);
        biggestShipSpinner.setDisable(true);
    }

    public void handleCancelShipSizeButtonAction(ActionEvent actionEvent) {
        cancelShipSizeButton.setDisable(true);
        applyShipSizeButton.setDisable(false);
        enableShipSizeSpinners();
        checkBoardAvailability();
        System.out.println(actionEvent.toString());
    }

    private void enableShipSizeSpinners() {
        smallShipSpinner.setDisable(false);
        mediumShipSpinner.setDisable(false);
        bigShipSpinner.setDisable(false);
        biggestShipSpinner.setDisable(false);
    }

    public void handleApplyPlacementButtonAction(ActionEvent actionEvent) {
        cancelShipSizeButton.setDisable(true);
        cancelBoardSizeButton.setDisable(true);
        cancelPlacementModeButton.setDisable(true);
        applyPlacementButton.setDisable(true);
        cancelPlacementButton.setDisable(false);
        playButton.setDisable(false);
        board.setDisable(true);
        System.out.println(actionEvent.toString());
    }

    public void handleCancelPlacementButtonAction(ActionEvent actionEvent) {
        cancelShipSizeButton.setDisable(false);
        cancelBoardSizeButton.setDisable(false);
        cancelPlacementModeButton.setDisable(false);
        cancelPlacementButton.setDisable(true);
        applyPlacementButton.setDisable(false);
        playButton.setDisable(true);
        board.setDisable(false);
        System.out.println(actionEvent.toString());
    }

    public void handleBoardMouseClicked(MouseEvent mouseEvent) {
        if (!board.isDisabled()) {
            try {
                if (isBattle) {
                    TextFlow tileNode = getTile(mouseEvent);
                    Integer X = getX(mouseEvent);
                    Integer Y = getY(mouseEvent);
                    switch (game.shotAt(X, Y)) {
                        case HIT:
                            tileNode.setStyle(Consts.HIT_TILE_COLOR);
                            break;
                        case HIT_AND_SUNK:
                            tileNode.setStyle(Consts.HIT_TILE_COLOR);
                            break;
                        case MISS:
                            tileNode.setStyle(Consts.MISS_TILE_COLOR);
                            break;
                        case ALREADY_HIT:
                            break;
                    }
                    messageBox.appendText("\n" + game.getScore().getHistory().get(game.getScore().getHistory().size() - 1));
                    if (game.isEnd()) {
                        board.setDisable(true);
                        statisticsButton.setDisable(false);
                        messageBox.appendText("\n" + Consts.GAME_END);
                    }
                }
                else {
                    //JavaFx jest tak wygodna, że nie możemy się odwołać do kafelków GridPane za pomocą koordynatów,
                    TextFlow tileNode = getTile(mouseEvent);
                    if (tileNode.getAccessibleText().equals(String.valueOf(Consts.BOARD_TILE.SHIP_TILE))) {
                        tileNode.setAccessibleText(String.valueOf(Consts.BOARD_TILE.BLANK_TILE));
                        tileNode.setStyle("");
                    }
                    else {
                        tileNode.setAccessibleText(String.valueOf(Consts.BOARD_TILE.SHIP_TILE));
                        tileNode.setStyle(Consts.PLACE_SHIP_TILE_COLOR);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(mouseEvent.toString());
    }

    private TextFlow getTile(MouseEvent mouseEvent) throws Exception {
        Integer X = getX(mouseEvent);
        Integer Y = getY(mouseEvent);

        for (Node node : board.getChildren()) {
            if (node instanceof TextFlow) { //TODO Skąd tam Group?
                if (GridPane.getRowIndex(node).equals(X) && GridPane.getColumnIndex(node).equals(Y)) {
                    return (TextFlow) node;
                }
            }
        }

        throw new Exception(Consts.ERROR_MESSAGE.TILE_NOT_FOUND.value);
    }

    private int getY(MouseEvent mouseEvent) throws Exception {
        Integer y = (int) mouseEvent.getX()/ Consts.PREF_TILE_HEIGHT;

        if (y > ((int) boardWidthSpinner.getValue() - 1)) {
            throw new Exception(Consts.ERROR_MESSAGE.BOARD_SIZE_EXCEEDED.value);
        }

        return y;
    }

    private int getX(MouseEvent mouseEvent) throws Exception {
        Integer x = (int) mouseEvent.getY()/ Consts.PREF_TILE_WIDTH;

        if (x > ((int) boardLengthSpinner.getValue() - 1)) {
            throw new Exception(Consts.ERROR_MESSAGE.BOARD_SIZE_EXCEEDED.value);
        }

        return x;
    }
}