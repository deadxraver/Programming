package main;

import annotations.MainMethod;
import commandhelper.Command;
import commandhelper.LocalCommand;
import commandhelper.MovieGenerator;
import commands.*;
import elements.Movie;
import exceptions.NoSuchCommandException;
import exceptions.NullFieldException;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongNumberOfArguments;
import localcommands.ExecuteScript;
import localcommands.Exit;
import parsers.IntParser;
import parsers.LongParser;
import communication.RequestPackage;
import communication.ResponsePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Scanner;


public class Program {

    public Program(String host, int port) {
        this.port = port;
        this.host = host;
        reader = new Scanner(System.in);
        generateHashMaps();
    }
    private Scanner reader;
    private SocketChannel sc;
    private int fileCallsCount = 0;
    private HashMap<String, Command> commandHashMap;
    private HashMap<String, LocalCommand> localCommandHashMap;
    private final String host;
    private final int port;
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
            "remove_lower",
    };
    /**
     * Executes the main logic of the program, prompting user input and processing commands.
     */
    @MainMethod
    public void execute() {
        System.out.println("Welcome! Type 'help' to get info about available commands");
        while (true) {
            if (reader == null || !reader.hasNext() || fileCallsCount > 100) {
                reader = new Scanner(System.in);
                if (fileCallsCount > 100) System.err.println("Too many file calls. execute_script terminated.");
            }
            String[] input = reader.nextLine().split(" ");
            int numberOfArgs;
            boolean isLocal;
            input[0] = input[0].trim();
            if (input[0].isEmpty()) continue;
            try {
                numberOfArgs = getNumberOfArgs(input);
                isLocal = isIn(localCommands, input[0]);
            } catch (NoSuchCommandException | WrongNumberOfArguments e) {
                System.err.println(e.getMessage());
                continue;
            }
            if (isLocal) {
                reader = localCommandHashMap.get(input[0]).execute(numberOfArgs == 0 ? null : input[1]);
                fileCallsCount++;
            }
            else {
                try {
                    sc = SocketChannel.open(new InetSocketAddress(host, port));
                } catch (IOException e) {
                    System.err.println("Failed to connect");
                    continue;
                }
                try (Socket socket = sc.socket()) {
                    int id = 0;
                    if (isIn(generatesMovie, input[0])) {
                        if (input[0].equals("update")) id = IntParser.parse(input[1]);
                        Movie movie = MovieGenerator.generateMovie(id, reader);
                        sendObject(
                                new RequestPackage<>(
                                        commandHashMap.get(input[0]),
                                        movie
                                ),
                                socket
                        );
                    }
                    else if (numberOfArgs == 0) {
                        sendObject(
                                new RequestPackage<>(
                                        commandHashMap.get(input[0]),
                                        null
                                ),
                                socket
                        );
                    }
                    else if (numberOfArgs == 1) {
                        sendObject(
                                new RequestPackage<>(
                                        commandHashMap.get(input[0]),
                                        LongParser.parse(input[1])
                                ),
                                socket
                        );
                    }

                    ResponsePackage rp = getResponse(socket);
                    if (rp.response().errorsOccurred()) System.err.println(rp.response().message() == null ? "" : rp.response().message());
                    else System.out.println(rp.response().message() == null ? "" : rp.response().message());
                } catch (UnknownHostException e) {
                    System.err.println("Unknown host");
                } catch (IOException | NumberOutOfBoundsException | NullFieldException e) {
                    System.err.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Wrong number format");
                } catch (ClassNotFoundException e) {
                    System.err.println("????");
                }
            }

        }
    }

    private boolean isIn(String[] arr, String str) {
        for (String c : arr) {
            if (str.equals(c)) return true;
        }
        return false;
    }

    private int getNumberOfArgs(String[] input) throws NoSuchCommandException, WrongNumberOfArguments {
        String inputWord = input[0];
        if (isIn(noArgsCommands, inputWord)) {
            if (input.length != 1) throw new WrongNumberOfArguments("does not require any arguments");
            return 0;
        }
        else if (isIn(oneArgCommands, inputWord)) {
            if (input.length != 2) throw new WrongNumberOfArguments("requires exactly 1 argument");
            return 1;
        }
        else {
            System.err.println("No such command");
            throw new NoSuchCommandException();
        }
    }

    private void sendObject(RequestPackage<?> rp, Socket socket) throws IOException {
//        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//        ) {
            oos.writeObject(rp);
            oos.flush();
//        }
    }

    private ResponsePackage getResponse(Socket socket) throws IOException, ClassNotFoundException {
//        try (
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//        ) {
            return (ResponsePackage) ois.readObject();
//        }
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
