package ui;

import model.Deck;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            new BlackjackPlayer();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
