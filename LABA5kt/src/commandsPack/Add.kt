package commandsPack

import commandInterfacePack.Add
import elementsPack.*
import exceptionsPack.*
import parserPack.*
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

/**
 * Class storing method show
 */
class Add protected constructor() : Show(), Add {
    /**
     * Method to add a new movie to the collection.
     */
    override fun add() {
        var operatorInfo: Boolean
        var movieName: String
        var x: Float
        var y: Double
        var genre: MovieGenre
        var mpaaRating: MpaaRating?
        var oscarsCount: Int
        var operator: Person? = null
        reader!!.nextLine()
        while (true) {
            try {
                println("Enter movie name")
                movieName = StringParser.parse(reader!!.nextLine())
                break
            } catch (e: EmptyStringException) {
                System.err.println(e.message)
            }
        }
        while (true) {
            try {
                println("Enter x coordinate")
                x = FloatParser.parse(reader!!.nextLine())
                break
            } catch (e: NullFieldException) {
                System.err.println(e.message)
            } catch (e: NumberFormatException) {
                System.err.println("Wrong number format")
            }
        }
        while (true) {
            try {
                println("Enter y coordinate (max - 274)")
                y = DoubleParser.parse(reader!!.nextLine())
                break
            } catch (e: NumberOutOfBoundsException) {
                System.err.println(e.message)
                System.err.println("Max number is " + DoubleParser.UPPER_BOUND)
            } catch (e: NullFieldException) {
                System.err.println(e.message)
            } catch (e: NumberFormatException) {
                System.err.println("Wrong number format")
            }
        }
        while (true) {
            try {
                println("Enter movie genre (e.g. drama, fantasy, etc.)")
                genre = GenreParser.parse(reader!!.nextLine())
                if (genre == null) throw IncorrectInputException()
                break
            } catch (e: NullFieldException) {
                System.err.println(e.message)
            } catch (e: IncorrectInputException) {
                System.err.println(e.message)
            }
        }
        while (true) {
            try {
                println("Enter MPAA rating (optional)")
                mpaaRating = MpaaRatingParser.parse(reader!!.nextLine())
                break
            } catch (e: IncorrectInputException) {
                System.err.println(e.message)
            }
        }
        while (true) {
            try {
                println("Enter number of Oscars")
                oscarsCount = IntParser.parse(reader!!.nextLine())
                if (oscarsCount <= IntParser.LOWER_OSCAR_BOUND) throw NumberOutOfBoundsException()
                break
            } catch (e: NullFieldException) {
                System.err.println(e.message)
            } catch (e: NumberFormatException) {
                System.err.println("Wrong number format")
            } catch (e: NumberOutOfBoundsException) {
                System.err.println("Number of Oscars should be greater than" + IntParser.LOWER_OSCAR_BOUND)
            }
        }
        while (true) {
            try {
                println("Do you want to fill in the information about operator (yes/no)?")
                val line: String = StringParser.parse(reader!!.nextLine())
                if (line != "yes" && line != "no") throw IncorrectInputException()
                operatorInfo = line == "yes"
                break
            } catch (e: Exception) {
                System.err.println("Line should be either 'yes' or 'no'")
            }
        }
        if (operatorInfo) {
            var operatorName: String
            var birthday: LocalDateTime?
            var hairColor: Color?
            var nationality: Country?
            while (true) {
                try {
                    println("Enter operator name")
                    operatorName = StringParser.parse(reader!!.nextLine())
                    break
                } catch (e: EmptyStringException) {
                    System.err.println("Name cannot be empty!")
                }
            }
            while (true) {
                try {
                    println("Enter birthday date (optional, yyyy-mm-dd format)")
                    val temp = reader!!.nextLine().replace(" ".toRegex(), "")
                    if (temp.isEmpty()) {
                        birthday = null
                        break
                    }
                    birthday = DateTimeParser.parse(temp)
                    break
                } catch (e: DateTimeParseException) {
                    System.err.println(e.message)
                }
            }
            while (true) {
                try {
                    println("Enter hair color (optional)")
                    val temp = reader!!.nextLine()
                    if (temp.replace(" ".toRegex(), "").isEmpty()) {
                        hairColor = null
                        break
                    }
                    hairColor = ColorParser.parse(temp)
                    if (hairColor == null) throw NoSuchColorException()
                    break
                } catch (e: NoSuchColorException) {
                    System.err.println(e.message)
                }
            }
            while (true) {
                try {
                    println("Enter nationality (optional)")
                    val temp = reader!!.nextLine()
                    if (temp.replace(" ".toRegex(), "").isEmpty()) {
                        nationality = null
                        break
                    }
                    nationality = CountryParser.parse(temp)
                    if (nationality == null) throw NoSuchCountryException()
                    break
                } catch (e: NoSuchCountryException) {
                    System.err.println(e.message)
                }
            }
            operator = Person(operatorName, birthday!!, hairColor!!, nationality!!)
        }
        collection!!.addElement(Coordinates(x, y), genre, mpaaRating, movieName, operator, oscarsCount)
        println("Movie successfully added!")
    }
}