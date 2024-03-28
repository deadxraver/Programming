package exceptions;

public class NoSuchMpaaRatingException extends Exception {
    @Override
    public String getMessage() {
        return "No such MPAA rating";
    }
}
