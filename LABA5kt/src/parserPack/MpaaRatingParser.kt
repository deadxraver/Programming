package parserPack

import elementsPack.MpaaRating
import exceptionsPack.IncorrectInputException
import java.util.*

object MpaaRatingParser {
    /**
     * A method to parse the given string and return the corresponding MpaaRating.
     *
     * @param  line  the string to be parsed
     * @return       the corresponding MpaaRating, or null if the string is empty
     */
    @Throws(IncorrectInputException::class)
    fun parse(line: String): MpaaRating? {
        if (line.replace(" ", "").isEmpty()) return null
        val mpaaRating = when (line.uppercase(Locale.getDefault()).replace("-", "_").replace(" ", "")) {
            "NC_17" -> MpaaRating.NC_17
            "PG" -> MpaaRating.PG
            "R" -> MpaaRating.R
            else -> null
        }
        if (mpaaRating == null) throw IncorrectInputException()
        return mpaaRating
    }
}