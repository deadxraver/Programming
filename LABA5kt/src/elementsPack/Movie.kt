package elementsPack

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * Key element user works with
 *
 *
 * id should be greater than 0, should be unique and be generated automatically
 *
 *
 * name cannot be null or empty
 *
 *
 * coordinates cannot be null
 *
 *
 * creationDate cannot be null and should be generated automatically
 *
 *
 * oscarsCount should be greater than 0
 *
 *
 * genre cannot be null
 *
 *
 * mpaaRating CAN be null
 *
 *
 * operator CAN be null
 */
class Movie(
    private var id: Long,
    private var name: String,
    private var coordinates: Coordinates,
    private var creationDate: LocalDate,
    private var oscarsCount: Int,
    private var genre: MovieGenre,
    private var mpaaRating: MpaaRating?,
    private var operator: Person?
) {
    fun getId(): Long {
        return id
    }

    fun getOscarsCount(): Int {
        return oscarsCount
    }

    fun getGenre(): MovieGenre {
        return genre
    }

    fun getMpaaRating(): MpaaRating? {
        return mpaaRating
    }

    fun getOperator(): Person? {
        return operator
    }

    fun getCreationDate(): LocalDate {
        return creationDate
    }

    fun getCoordinates(): Coordinates {
        return coordinates
    }

    fun getName(): String {
        return name
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setCoordinates(coordinates: Coordinates) {
        this.coordinates = coordinates
    }

    fun setCreationDate(creationDate: LocalDate) {
        this.creationDate = creationDate
    }

    fun setCreationDate(creationDate: LocalDateTime) {
        this.creationDate = creationDate.toLocalDate()
    }

    fun setOscarsCount(oscarsCount: Int) {
        this.oscarsCount = oscarsCount
    }

    fun setGenre(genre: MovieGenre) {
        this.genre = genre
    }

    fun setMpaaRating(mpaaRating: MpaaRating?) {
        this.mpaaRating = mpaaRating
    }

    fun setOperator(operator: Person?) {
        this.operator = operator
    }

    override fun toString(): String {
        return """
            id: $id
            Name: $name
            Coordinates: $coordinates
            Creation date: $creationDate
            Number of oscars: $oscarsCount
            Genre: $genre
            MPAA rating: ${if (mpaaRating == null) "no info" else mpaaRating}
            Operator: ${if (operator == null) "no info" else operator}
            """.trimIndent()
    }
}