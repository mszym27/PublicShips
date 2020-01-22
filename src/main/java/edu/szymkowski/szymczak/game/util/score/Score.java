package edu.szymkowski.szymczak.game.util.score;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Score {

    List<String> history;

    private String fileName;

    private final Logger logger = LoggerFactory.getLogger(Score.class);

    public Score() {
        history = new LinkedList<>();
        history.add("Nowa gra: " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-m-s-n")));
    }

    private void createFile() {
        fileName = "history-" + LocalDate.now() + "-" + LocalTime.now().format(DateTimeFormatter.ofPattern("hh-m-s-n"));
        saveContentToFile();
    }

    public void addEventRecord(String msg) {
        history.add(msg);
        if (fileName == null || fileName.equals("")) {
            createFile();
        }
        saveContentToFile();
    }

    private void saveContentToFile() {
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
            for (String record : history) {
                printWriter.println(record);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getStackTrace().toString());
        }
    }

    public List<String> getHistory() {
        LinkedList<String> historyList = new LinkedList<String>();
        historyList.addAll(history);

        return  historyList;
    }

    public void openScore() {
        if (fileName == null || fileName.equals("")) {
            createFile();
        }

        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", fileName);

        try {
            pb.start();
        } catch (IOException e) {
            logger.error(e.getStackTrace().toString());
        }
    }
}
