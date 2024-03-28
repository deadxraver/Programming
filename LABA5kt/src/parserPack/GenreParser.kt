package parserPack

import elementsPack.MovieGenre
import exceptionsPack.NullFieldException
import java.util.*

object GenreParser {
    /**
     * Parses the given string to a MovieGenre.
     *
     * @param  line  the string to be parsed
     * @return      the parsed MovieGenre
     */
    @Throws(NullFieldException::class)
    fun parse(line: String): MovieGenre {
        if (line.isEmpty()) throw NullFieldException()
        return when (line.lowercase(Locale.getDefault())) {
            "drama" -> MovieGenre.DRAMA
            "fantasy" -> MovieGenre.FANTASY
            "tragedy" -> MovieGenre.TRAGEDY
            "western" -> MovieGenre.WESTERN
            else -> null!!
        }
    }
}