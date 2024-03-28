package exceptionsPack

class IncorrectInputException : Exception() {
    override val message: String
        get() = "Incorrect input"
}