package exceptions;

public class DateIsNotReachedException extends Exception {
    @Override
    public String getMessage() {
        return "Date is not yet reached";
    }
}
