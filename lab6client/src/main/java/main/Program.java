package main;

import commands.ExecuteScript;
import commands.MovieGenerator;
import elements.Movie;
import exceptions.NullFieldException;
import exceptions.NumberOutOfBoundsException;
import parsers.IntParser;
import parsers.LongParser;
import servercommunication.CommandType;
import servercommunication.RequestPackage;
import servercommunication.ResponsePackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Program {

    public Program() {
        reader = new Scanner(System.in);
    }
    private Scanner reader;
    private int fileCallsCount = 0;

    /**
     * Executes the main logic of the program, prompting user input and processing commands.
     *
     * @throws IOException if an I/O error occurs
     */
    public void execute() throws IOException {
        System.out.println("Welcome! Type 'help' to get list of available commands");
        try {
            while (true) {
                if (!reader.hasNext()) {
                    reader = new Scanner(System.in);
                    fileCallsCount = 0;
                }
                String[] input = reader.nextLine().split(" ");
                if (input.length == 0) continue;
                CommandType commandType = parseCommandType(input[0]);
                if (commandType == null) {
                    System.err.println("No such command");
                    continue;
                }
                if (!commandType.requiresArguments && input.length > 2) {
                    System.err.println("This command doesn't require arguments");
                    continue;
                }
                if (commandType.requiresArguments && input.length != 2) {
                    System.err.println("This command requires exactly 1 argument");
                    continue;
                }
                try (
                        Socket socket = new Socket("localhost", 1841);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
                ) {
                    if (commandType.requiresArguments) {
                        try {
                            switch (commandType) {
                                case EXECUTE_SCRIPT -> {
                                    fileCallsCount++;
                                    reader = ExecuteScript.execute(input[1]);
                                }
                                case REMOVE_ALL_BY_OSCARS_COUNT -> {
                                    RequestPackage<Integer> requestPackage =
                                            new RequestPackage<>(commandType, IntParser.parse(input[1]));
                                    oos.writeObject(requestPackage);
                                    oos.flush();
                                }
                                case REMOVE_BY_ID, UPDATE -> {
                                    RequestPackage<Long> requestPackage =
                                            new RequestPackage<>(commandType, LongParser.parse(input[1]));
                                    oos.writeObject(requestPackage);
                                    oos.flush();
                                }
                            }
                        } catch (NumberOutOfBoundsException | NullFieldException e) {
                            System.err.println(e.getMessage());
                        } catch (NumberFormatException e) {
                            System.err.println("Wrong number format");
                        }
                    } else {
                        RequestPackage<?> requestPackage = new RequestPackage<>(commandType, null);
                        oos.writeObject(requestPackage);
                        oos.flush();
                    }
                    if (commandType.generatesMovie) {
                        ResponsePackage<Long> responsePackage = (ResponsePackage<Long>) ois.readObject();

                        if (!responsePackage.errorsOccurred) {
                            Movie movie = MovieGenerator.generateMovie(responsePackage.response, reader);
                            oos.writeObject(movie);
                            oos.flush();
                        } else {
                            System.err.println("Current id is not taken by anyone");
                        }
                    } else {
                        ResponsePackage<?> responsePackage = (ResponsePackage<?>) ois.readObject();
                        if (responsePackage.errorsOccurred) System.err.println(responsePackage.response);
                        else System.out.println(responsePackage.response);
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        }
        catch (NoSuchElementException ignored) {}
    }

    private CommandType parseCommandType(String line) {
        try {
            return (CommandType) CommandType.class.getDeclaredField(line.toUpperCase()).get(null);
        } catch (NoSuchFieldException e) {
            System.err.println("No such command");
        } catch (IllegalAccessException ignored) {}
        return null;
    }
}
