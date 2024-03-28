package parserPack

import java.time.LocalDateTime
import java.time.format.DateTimeParseException

object DateTimeParser {
    /**
     * Parses the given string to create a LocalDateTime object.
     *
     * @param  line  the string to be parsed
     * @return       the parsed LocalDateTime object
     */
    @Throws(DateTimeParseException::class)
    fun parse(line: String?): LocalDateTime {
        return LocalDateTime.parse(line + "T00:00:00.0000000")
    }
}