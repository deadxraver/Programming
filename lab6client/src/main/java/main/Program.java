package main;

import java.util.Scanner;

/**
 * Key class storing collection and key run method
 */
public class Program {

    public Program() {
        reader = new Scanner(System.in);
    }
    private Scanner reader;

    /**
     * Key method that starts the program
     */
    public void run() {
        System.out.println("Welcome! Type 'help' to get info about available commands");
        while (true) {
            String[] input = reader.nextLine().split(" ");

        }
    }
}
