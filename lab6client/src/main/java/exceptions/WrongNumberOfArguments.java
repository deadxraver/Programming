package exceptions;

public class WrongNumberOfArguments extends Exception {
    private final String messagePart;
    public WrongNumberOfArguments(String messagePart) {
        this.messagePart = messagePart;
    }
    @Override
    public String getMessage() {
        return "This method " + messagePart;
    }

}
