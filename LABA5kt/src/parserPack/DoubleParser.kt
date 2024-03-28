package parserPack

import exceptionsPack.NullFieldException
import exceptionsPack.NumberOutOfBoundsException

object DoubleParser {
    const val UPPER_BOUND: Int = 274

    /**
     * Parses the input string and returns a Double value, while handling specific exceptions.
     *
     * @param  line  the input string to be parsed
     * @return       the parsed Double value
     */
    @Throws(NullFieldException::class, NumberOutOfBoundsException::class)
    fun parse(line: String?): Double {
        val n = FloatParser.parse(line!!).toDouble()
        if (n > UPPER_BOUND) throw NumberOutOfBoundsException()
        return n
    }
}