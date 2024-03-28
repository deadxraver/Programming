package exceptionsPack

class NullFieldException : Exception() {
    override val message: String
        get() = "Field cannot be null"
}