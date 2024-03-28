package exceptionsPack

class NoSuchCountryException : Exception() {
    override val message: String = "There is no such country in the list"
}