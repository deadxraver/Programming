package exceptionsPack

class EmptyStringException : Exception() {
    override val message: String
        get() = "String cannot be empty"
}