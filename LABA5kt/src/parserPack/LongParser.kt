package parserPack

import exceptionsPack.NullFieldException

object LongParser {
    /**
     * Parses a string to a long, removing underscores if present.
     *
     * @param  line  the string to be parsed
     * @return       the parsed long value
     */
    @Throws(NumberFormatException::class, NullFieldException::class)
    fun parse(line: String): Long {
        if (line.isEmpty()) throw NullFieldException()
        return line.replace("_", "").toLong()
    }
}