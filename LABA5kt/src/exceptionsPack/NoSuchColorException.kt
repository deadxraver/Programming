package exceptionsPack

class NoSuchColorException : Exception() {
    override val message: String
        get() = "There is no such color"
}