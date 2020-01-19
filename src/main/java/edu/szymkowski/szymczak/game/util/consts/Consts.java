package edu.szymkowski.szymczak.game.util.consts;

public final class Consts {

    public final static int MIN_BOARD_LENGTH = 3;

    public final static int MIN_BOARD_WIDTH = 2;

    public static final int MAX_SMALL_SHIP_AMOUNT = 10;

    public static final int MAX_MEDIUM_SHIP_AMOUNT = 10;

    public static final int MAX_BIG_SHIP_AMOUNT = 10;

    public static final int MAX_BIGGEST_SHIP_AMOUNT = 10;

    public static final int MAX_BOARD_LENGTH = 10;

    public static final int MAX_BOARD_WIDTH = 10;

    public static final int PREF_TILE_HEIGHT = 20;

    public static final int PREF_TILE_WIDTH = 20;

    public enum BOARD_TILE
    {


        BLANK_TILE(0),
        SHIP_TILE(1),
        MISS_TILE(2),
        HIT_TILE(3);

        public final Integer value;


        public String toString()
        {
            return this.value.toString();
        }

        private BOARD_TILE(Integer value) {
            this.value = value;
        }
    }

    public enum SHIP_LENGTH
    {


        SMALL(1),
        MEDIUM(2),
        BIG(3),
        BIGGEST(4);

        public final int value;


        private SHIP_LENGTH(int value) {
            this.value = value;
        }
    }

    public enum SHIP_NAME
    {


        SMALL("Pojedyńczy"),
        MEDIUM("Podwójny"),
        BIG("Potrójny"),
        BIGGEST("Poczwórny"),
        INVALID("Nieprawidłowy");

        public final String value;


        private SHIP_NAME(String value) {
            this.value = value;
        }
    }

    public enum ERROR_MESSAGE
    {


        SHIP_PLACEMENT_INVALID("\nZłe rozmieszczenie statków. Proszę skorygować rozmieszczenie bądź ilość statków."),
        BOARD_SIZE_EXCEEDED("Przekroczono granice planszy."),
        TILE_NOT_FOUND("Nie odnaleziono kafelka."),
        INVALID_SHIP_SIZE("Wprowadzono statek niezgodny z maksymalną długością, wynoszącą:" + SHIP_LENGTH.BIGGEST + "."),
        INVALID_SHIP_COUNT("Niewłaściwa ilość statków.");

        public final String value;


        private ERROR_MESSAGE(String value) {
            this.value = value;
        }
    }

    public enum HIT_RESULT
    {
        MISS,
        HIT,
        HIT_AND_SUNK,
        ALREADY_HIT,
        UNKNOWN
    }

    public static final String BLANK_TILE_COLOR = "-fx-background-color: white;";

    public static final String PLACE_SHIP_TILE_COLOR = "-fx-background-color: blue;";

    public static final String MISS_TILE_COLOR = "-fx-background-color: yellow;";

    public static final String HIT_TILE_COLOR = "-fx-background-color: red;";

    public static final String GAME_START = "Rozpoczęcie gry!";

    public static final String GAME_END = "Koniec gry!";
}
