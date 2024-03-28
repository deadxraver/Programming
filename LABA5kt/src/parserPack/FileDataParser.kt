package parserPack

import exceptionsPack.IncorrectInputException
import exceptionsPack.NullFieldException
import exceptionsPack.NumberOutOfBoundsException
import parserPack.ColorParser.parse
import parserPack.CountryParser.parse
import parserPack.DoubleParser.parse
import parserPack.FloatParser.parse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

object FileDataParser {
    /**
     * Parses the given big line to extract specific fields and return the modified big line.
     *
     * @param  bigLine   the big line to be parsed
     * @return          the modified big line after parsing
     */
    @Suppress("NAME_SHADOWING")
    fun parse(bigLine: String): String {
        var bigLine = bigLine
        while (bigLine.contains("> ")) bigLine = bigLine.replace("> ".toRegex(), ">")
        val allLines = bigLine.split("</movie><movie>".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        for (i in allLines.indices) {
            var line = allLines[i]
            try {
                if (line.contains("<id>")) LongParser.parse(
                    line.substring(
                        line.indexOf("<id>") + 4,
                        line.indexOf("</id>")
                    )
                )
            } catch (e: NullFieldException) {
                line = line.replace("<id>.*</id>".toRegex(), "")
            } catch (e: NumberFormatException) {
                line = line.replace("<id>.*</id>".toRegex(), "")
            }
            try {
                if (line.contains("<x>")) FloatParser.parse(
                    line.substring(
                        line.indexOf("<x>") + 3,
                        line.indexOf("</x>")
                    )
                )
            } catch (e: NullFieldException) {
                line = line.replace("<x>.*</x>".toRegex(), "")
            } catch (e: NumberFormatException) {
                line = line.replace("<x>.*</x>".toRegex(), "")
            }
            try {
                if (line.contains("<y>")) DoubleParser.parse(
                    line.substring(
                        line.indexOf("<y>") + 3,
                        line.indexOf("</y>")
                    )
                )
            } catch (e: NumberOutOfBoundsException) {
                line = line.replace("<y>.*</y>".toRegex(), "")
            } catch (e: NullFieldException) {
                line = line.replace("<y>.*</y>".toRegex(), "")
            } catch (e: NumberFormatException) {
                line = line.replace("<y>.*</y>".toRegex(), "")
            }
            try {
                if (line.contains("<genre>")) GenreParser.parse(
                    line.substring(
                        line.indexOf("<genre>") + 7,
                        line.indexOf("</genre>")
                    )
                )
            } catch (e: NullFieldException) {
                line = line.replace("<genre>.*</genre>".toRegex(), "")
            }
            try {
                if (line.contains("<creationDate>")) LocalDate.parse(
                    line.substring(
                        line.indexOf("<creationDate>") + 14,
                        line.indexOf("</creationDate>")
                    )
                )
            } catch (e: DateTimeParseException) {
                line = line.replace("<creationDate>.*</creationDate>".toRegex(), "")
            }
            try {
                if (line.contains("<oscarsCount>")) IntParser.parse(
                    line.substring(
                        line.indexOf("<oscarsCount>") + 13,
                        line.indexOf("</oscarsCount>")
                    )
                )
            } catch (e: NullFieldException) {
                line = line.replace("<oscarsCount>.*</oscarsCount>".toRegex(), "")
            } catch (e: NumberFormatException) {
                line = line.replace("<oscarsCount>.*</oscarsCount>".toRegex(), "")
            }
            try {
                if (line.contains("<mpaaRating>")) MpaaRatingParser.parse(
                    line.substring(
                        line.indexOf("<mpaaRating>") + 12,
                        line.indexOf("</mpaaRating>")
                    )
                )
            } catch (e: IncorrectInputException) {
                line = line.replace("<mpaaRating>.*</mpaaRating>".toRegex(), "")
            }
            try {
                if (line.contains("<birthday>")) LocalDateTime.parse(
                    line.substring(
                        line.indexOf("<birthday>") + 10,
                        line.indexOf("</birthday>")
                    )
                )
            } catch (e: DateTimeParseException) {
                line = line.replace("<birthday>.*</birthday>".toRegex(), "")
            }
            if (line.contains("<hairColor>")) {
                val color =
                    ColorParser.parse(line.substring(line.indexOf("<hairColor>") + 11, line.indexOf("</hairColor>")))
                if (color == null) line = line.replace("<hairColor>.*</hairColor>".toRegex(), "")
            }
            if (line.contains("<nationality>")) {
                val country = CountryParser.parse(
                    line.substring(
                        line.indexOf("<nationality>") + 13,
                        line.indexOf("</nationality>")
                    )
                )
                if (country == null) line = line.replace("<nationality>.*</nationality>".toRegex(), "")
            }
            allLines[i] = line
        }
        return java.lang.String.join("</movie><movie>", *allLines)
    }
}