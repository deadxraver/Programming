package parserPack

import exceptionsPack.NullFieldException

object FloatParser {
    /**
     * Parses a string to a Float after replacing spaces, underscores, and commas with dots.
     *
     * @param  line  the string to be parsed
     * @return      the parsed Float value
     */
    @Suppress("NAME_SHADOWING")
    @Throws(NumberFormatException::class, NullFieldException::class)
    fun parse(line: String): Float {
        var line = line
        line = line.replace(" ".toRegex(), "").replace("_".toRegex(), "").replace(",".toRegex(), ".")
        if (line.isEmpty()) throw NullFieldException()
        return line.toFloat()
    }
}