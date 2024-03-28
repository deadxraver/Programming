package parserPack

import exceptionsPack.NullFieldException

object IntParser {
    const val LOWER_OSCAR_BOUND: Int = 0

    /**
     * Parses the given string to an integer after removing underscores.
     *
     * @param  line  the string to be parsed
     * @return       the parsed integer value
     */
    @Throws(NumberFormatException::class, NullFieldException::class)
    fun parse(line: String): Int {
        if (line.isEmpty()) throw NullFieldException()
        return line.replace("_", "").toInt()
    }
}