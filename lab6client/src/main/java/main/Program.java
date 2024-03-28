package main;

import commands.MovieGenerator;
import elements.Movie;
import exceptions.NullFieldException;
import exceptions.NumberOutOfBoundsException;
import parsers.IntParser;
import parsers.LongParser;
import servercommunication.CommandType;
import servercommunication.RequestPackage;
import servercommunication.ResponsePackage;

import java.util.Scanner;

/**
 * Key class storing collection and key run method
 */
public class Program {

    public Program() {
        reader = new Scanner(System.in);
    }
    private Scanner reader;

    public void execute() {
        System.out.println("Welcome! Type 'help' to get list of available commands");
        while (true) {
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

            if (commandType.requiresArguments) {
                try {
                    if (commandType.equals(CommandType.EXECUTE_SCRIPT)) {
                        // TODO
                        break;
                    }
                    if (commandType.equals(CommandType.REMOVE_ALL_BY_OSCARS_COUNT)) {
                        RequestPackage<Integer> requestPackage =
                                new RequestPackage<>(commandType, IntParser.parse(input[1]));
                        // todo (send)
                        break;
                    }
                    if (commandType.equals(CommandType.REMOVE_BY_ID) || commandType.equals(CommandType.UPDATE)) {
                        RequestPackage<Long> requestPackage =
                                new RequestPackage<>(commandType, LongParser.parse(input[1]));
                        // todo (send)
                        break;
                    }
                } catch (NumberOutOfBoundsException | NullFieldException e) {
                    System.err.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Wrong number format");
                }
            }
            else {
                RequestPackage<?> requestPackage = new RequestPackage<>(commandType, null);
                // todo (send)
            }
            if (commandType.generatesMovie) {
                ResponsePackage<Long> responsePackage = null; // TODO (wait for response)
                if (!responsePackage.errorsOccurred) {
                    Movie movie = MovieGenerator.generateMovie(responsePackage.response, reader);
                    // TODO (send)
                }
                else {
                    System.err.println("Current id is not taken by anyone");
                }
            }
            else {
                ResponsePackage<?> responsePackage = null; // TODO (wait for response)
                if (responsePackage.errorsOccurred) System.err.println(responsePackage.response);
                else System.out.println(responsePackage.response);
            }
        }
    }

    private CommandType parseCommandType(String line) {
        try {
            return (CommandType) CommandType.class.getDeclaredField(line).get(null);
        } catch (NoSuchFieldException e) {
            System.err.println("No such command");
        } catch (IllegalAccessException ignored) {}
        return null;
    }
}
