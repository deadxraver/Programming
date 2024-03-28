package parserPack

import exceptionsPack.EmptyStringException

object StringParser {
    /**
     * Parses the input string by removing leading and trailing whitespaces,
     * and collapsing multiple consecutive whitespaces into a single space.
     *
     * @param  line  the input string to be parsed
     * @return       the parsed string
     */
    @Throws(EmptyStringException::class)
    fun parse(line: String): String {
        var line1 = line.trim { it <= ' ' }
        if (line1.isEmpty()) throw EmptyStringException()
        while (line1.contains("  ")) line1 = line1.replace(" {2}".toRegex(), " ")
        return line1
    }
}