package parserPack

import elementsPack.Color
import java.util.*

object ColorParser {
    /**
     * Parses the given input string and returns the corresponding Color value.
     *
     * @param  line  the input string to be parsed
     * @return       the Color value corresponding to the input string, or null if no match is found
     */
    fun parse(line: String): Color {
        return when (line.lowercase(Locale.getDefault())) {
            "green" -> Color.GREEN
            "orange" -> Color.ORANGE
            "white" -> Color.WHITE
            else -> null!!
        }
    }
}