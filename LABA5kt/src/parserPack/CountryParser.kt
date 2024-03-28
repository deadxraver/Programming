package parserPack

import elementsPack.Country
import java.util.*

object CountryParser {
    /**
     * Parses the input line to determine the corresponding Country enum value.
     *
     * @param  line  the input line to be parsed
     * @return      the corresponding Country enum value, or null if not found
     */
    fun parse(line: String): Country {
        return when (line.lowercase(Locale.getDefault())) {
            "china" -> Country.CHINA
            "germany" -> Country.GERMANY
            "japan" -> Country.JAPAN
            "spain" -> Country.SPAIN
            "vatican" -> Country.VATICAN
            else -> null!!
        }
    }
}