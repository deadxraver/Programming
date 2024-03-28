package commandsPack;

import elementsPack.*;
import exceptionsPack.EmptyStringException;
import exceptionsPack.IncorrectInputException;
import exceptionsPack.NullFieldException;
import exceptionsPack.NumberOutOfBoundsException;
import mainPack.MovieCollection;
import parserPack.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Add extends Command {
    @Override
    public void execute() {
        boolean operatorInfo;
        String movieName;
        Float x;
        Double y;
        LocalDate creationDate;
        int day, month, year;
        MovieGenre genre;
        long id;
        MpaaRating mpaaRating;
        int oscarsCount;
        Person operator = null;
        while (true) {
            try {
                System.out.print("Enter movie name: ");
                movieName = StringParser.parse(reader.nextLine());
                break;
            } catch (EmptyStringException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter x coordinate: ");
                x = FloatParser.parse(reader.nextLine());
                break;
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter y coordinate (max - 274): ");
                y = DoubleParser.parse(reader.nextLine());
                break;
            } catch (NumberOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Max number is " + DoubleParser.UPPER_BOUND);
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter day: ");
                day = IntParser.parse(reader.nextLine());
                if (day > 31) throw new NumberOutOfBoundsException();
                break;
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (NumberOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Day should not be greater than 31");
            }
        }
        while (true) {
            try {
                System.out.print("Enter month: ");
                month = IntParser.parse(reader.nextLine());
                if (month > 12) throw new NumberOutOfBoundsException();
                break;
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (NumberOutOfBoundsException e) {
                System.out.println(e.getMessage());
                System.out.println("Month should be not greater than 12");
            }
        }
        while (true) {
            try {
                System.out.print("Enter year: ");
                year = IntParser.parse(reader.nextLine());
                break;
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter movie genre (e.g. drama, fantasy, etc.): ");
                genre = GenreParser.parse(reader.nextLine());
                if (genre == null) throw new IncorrectInputException();
                break;
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }
        do {
            id = collection.generateId();
        } while (!collection.validateId(id));
        while (true) {
            try {
                System.out.print("Enter MPAA rating (optional): ");
                mpaaRating = MpaaRatingParser.parse(reader.nextLine());
                break;
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Enter number of Oscars: ");
                oscarsCount = IntParser.parse(reader.nextLine());
                break;
            } catch (NullFieldException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.print("Do you want to fill in the information about operator (yes/no)? ");
                String line = StringParser.parse(reader.nextLine());
                if (!line.equals("yes") && !line.equals("no")) throw new IncorrectInputException();
                operatorInfo = line.equals("yes");
                break;
            } catch (Exception e) {
                System.out.println("Line should be either 'yes' or 'no'");
            }
        }
        if (operatorInfo) {
            String operatorName;
            LocalDateTime birthday;
            Color hairColor;
            Country nationality;
        }
        //TODO
    }
}
