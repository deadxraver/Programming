package clientcommunication;

public enum CommandType {
    ADD(false, true),
    ADD_IF_MAX(false, true),
    CLEAR(false, false),
    EXECUTE_SCRIPT(true, false),
    EXIT(false, false),
    HELP(false, false),
    INFO(false, false),
    MAX_BY_MPAA_RATING(false, false),
    PRINT_FIELD_ASCENDING_OPERATOR(false, false),
    REMOVE_ALL_BY_OSCARS_COUNT(true, false),
    REMOVE_BY_ID(true, false),
    REMOVE_HEAD(false, false),
    REMOVE_LOWER(false, true),
    // SAVE(false, false),
    SHOW(false, false),
    UPDATE(true, true);
    CommandType(boolean requiresArguments, boolean generatesMovie) {
        this.requiresArguments = requiresArguments;
        this.generatesMovie = generatesMovie;
    }
    public final boolean requiresArguments;
    public final boolean generatesMovie;
}
