package main;

import commandhelper.Command;
import commandhelper.LocalCommand;
import commandhelper.MovieGenerator;
import commands.*;
import elements.Movie;
import exceptions.NullFieldException;
import exceptions.NumberOutOfBoundsException;
import localcommands.ExecuteScript;
import localcommands.Exit;
import parsers.IntParser;
import parsers.LongParser;
import servercommunication.RequestPackage;
import servercommunication.ResponsePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Program {

    public Program() {
        reader = new Scanner(System.in);
        generateHashMaps();
    }
    private Scanner reader;
    private int fileCallsCount = 0;
    private HashMap<String, Command> commandHashMap;
    private HashMap<String, LocalCommand> localCommandHashMap;
    private static final String[] noArgsCommands = {
            "add",
            "add_if_max",
            "clear",
            "help",
            "info",
            "max_by_mpaa_rating",
            "print_field_ascending_operator",
            "remove_head",
            "remove_lower",
            "show",
            "exit"
    };
    private static final String[] oneArgCommands = {
            "remove_all_by_oscars_count",
            "remove_by_id",
            "update",
            "execute_script"
    };
    private static final String[] localCommands = {
            "exit",
            "execute_script"
    };
    private static final String[] generatesMovie = {
            "add_if_max",
            "add",
            "update",
            "remove_lower"
    };
    /**
     * Executes the main logic of the program, prompting user input and processing commands.
     *
     * @throws IOException if an I/O error occurs
     */
    public void execute() throws IOException {
        System.out.println("Welcome! Type 'help' to get list of available commands");
        while (true) {
            if (!reader.hasNext() || fileCallsCount >= 100) {
                reader = new Scanner(System.in);
                fileCallsCount = 0;
            }
            String[] input = reader.nextLine().split(" ");
            if (input.length == 0) continue;
            int numberOfArgs;
            if (Arrays.binarySearch(noArgsCommands, input[0]) != -1) numberOfArgs = 0;
            else if (Arrays.binarySearch(oneArgCommands, input[0]) != -1) numberOfArgs = 1;
            else {
                System.err.println("No such command");
                continue;
            }
            if (Arrays.binarySearch(localCommands, input[0]) != -1) {
                reader = localCommandHashMap.get(input[0]).execute(numberOfArgs == 1 ? input[1] : null);
                fileCallsCount++;
            } else if (Arrays.binarySearch(generatesMovie, input[0]) != -1) {
                Movie movie = MovieGenerator.generateMovie(0, reader);
                try (
                        Socket socket = new Socket();
                        ObjectOutputStream oos = (ObjectOutputStream) socket.getOutputStream();
                        ObjectInputStream ois = (ObjectInputStream) socket.getInputStream();
                        ) {
                    oos.flush();
                    oos.writeObject(
                            new RequestPackage<>(
                                    commandHashMap.get(input[0]),
                                    movie
                            )
                    );
                    ResponsePackage responsePackage = (ResponsePackage) ois.readObject();
                    if (responsePackage.response().errorsOccurred()) System.err.println(responsePackage.response().message());
                    else System.out.println(responsePackage.response().message());
                } catch (ClassNotFoundException ignored) {}
            } else if (input[0].equals("update")) {
                try (
                        Socket socket = new Socket();
                        ObjectOutputStream oos = (ObjectOutputStream) socket.getOutputStream();
                        ObjectInputStream ois = (ObjectInputStream) socket.getInputStream()
                ) {
                    oos.flush();
                    oos.writeObject(
                            new RequestPackage<>(
                                    commandHashMap.get("update"),
                                    LongParser.parse(input[1])
                            )
                    );
                    ResponsePackage responsePackage = (ResponsePackage) ois.readObject();
                    if (responsePackage.response().errorsOccurred()) {
                        System.err.println(responsePackage.response().message());
                        continue;
                    }
                    oos.flush();
                    oos.writeObject(MovieGenerator.generateMovie(
                            LongParser.parse(input[1]),
                            reader
                    ));
                    responsePackage = (ResponsePackage) ois.readObject();
                    if (responsePackage.response().errorsOccurred()) System.err.println(responsePackage.response().message());
                    else System.out.println(responsePackage.response().message());
                } catch (NullFieldException | NumberFormatException e) {
                    System.err.println(e.getMessage());
                } catch (ClassNotFoundException ignored) {}
            } else {
                try (
                        Socket socket = new Socket();
                        ObjectOutputStream oos = (ObjectOutputStream) socket.getOutputStream();
                        ObjectInputStream ois = (ObjectInputStream) socket.getInputStream();
                        ) {
                    oos.flush();
                    oos.writeObject(
                            new RequestPackage<>(
                                    commandHashMap.get(input[0]),
                                    numberOfArgs == 0 ? null : IntParser.parse(input[1])
                            )
                    );
                    ResponsePackage responsePackage = (ResponsePackage) ois.readObject();
                    if (responsePackage.response().errorsOccurred()) System.err.println(responsePackage.response().message());
                    else System.out.println(responsePackage.response().message());
                } catch (NumberOutOfBoundsException | NullFieldException | NumberFormatException e) {
                    System.err.println(e.getMessage());
                } catch (ClassNotFoundException ignored) {}
            }
        }
    }

    private void generateHashMaps() {
        this.commandHashMap = new HashMap<>();
        this.localCommandHashMap = new HashMap<>();
        commandHashMap.put("help", new Help());
        commandHashMap.put("add", new Add());
        commandHashMap.put("add_if_max", new AddIfMax());
        commandHashMap.put("clear", new Clear());
        commandHashMap.put("info", new Info());
        commandHashMap.put("max_by_mpaa_rating", new MaxByMpaaRating());
        commandHashMap.put("print_field_ascending_operator", new PrintFieldAscendingOperator());
        commandHashMap.put("remove_all_by_oscars_count", new RemoveAllByOscarsCount());
        commandHashMap.put("remove_by_id", new RemoveById());
        commandHashMap.put("remove_head", new RemoveHead());
        commandHashMap.put("remove_lower", new RemoveLower());
        commandHashMap.put("show", new Show());
        commandHashMap.put("update", new Update());
        localCommandHashMap.put("exit", new Exit());
        localCommandHashMap.put("execute_script", new ExecuteScript());
    }
}
