package exceptions;

public class NoSuchGenreException extends Exception {
    @Override
    public String getMessage() {
        return "No such genre";
    }
}
