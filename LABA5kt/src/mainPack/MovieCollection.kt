package mainPack

import elementsPack.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * Class having a collection of movies and having some basic methods to easily manipulate them
 */
class MovieCollection {
    constructor() {
        this.dateOfCreation = LocalDateTime.now()
    }

    constructor(dateOfCreation: LocalDateTime) {
        this.dateOfCreation = dateOfCreation
    }

    private val dateOfCreation: LocalDateTime
    private val collection: LinkedList<Movie> = LinkedList()

    /**
     * Adds a new movie by given parameters
     * @param coordinates coordinates of action, 2-dimensional
     * @param genre movie genre (drama, fantasy, tragedy and western)
     * @param mpaaRating rating showing who can watch the movie according to the law (NC-17, PG, R)
     * @param name movie name, cannot be null
     * @param operator operator
     * @param oscarsCount number of oscars
     */
    fun addElement(
        coordinates: Coordinates?, genre: MovieGenre?,
        mpaaRating: MpaaRating?, name: String?, operator: Person?, oscarsCount: Int
    ) {
        var id: Long
        do {
            id = generateId()
        } while (!validateId(id))
        val creationDate = LocalDate.now()
        collection.add(Movie(id, name!!, coordinates!!, creationDate, oscarsCount, genre!!, mpaaRating, operator))
    }

    /**
     * Directly adds a new movie
     * @param movie element already having its id and date of creation
     */
    fun addElement(movie: Movie) {
        collection.add(movie)
    }

    /**
     * Generates a unique ID using Math.random() and validates it until a valid ID is found.
     *
     * @return            the generated unique ID
     */
    private fun generateId(): Long {
        var id: Long
        while (true) {
            id = (Math.random() * Long.MAX_VALUE).toLong()
            if (validateId(id)) return id
        }
    }

    /**
     * Checks whether current id is valid (greater than 0 and not taken by other elements)
     * @param id long field to be taken by a movie element
     * @return true / false depending on the result of validation
     */
    private fun validateId(id: Long): Boolean {
        if (id <= 0) return false
        for (movie in collection) {
            if (movie.getId() == id) return false
        }
        return true
    }

    val numberOfElements: Int
        get() = collection.size

    /**
     * Check whether current movie is greater than all the movies from collection
     * @param movie movie to be checked
     * @return true / false depending on the result
     */
    fun checkIfMax(movie: Movie): Boolean {
        for (movie1 in collection) {
            if (movie.getOscarsCount() <= movie1.getOscarsCount()) return false
        }
        return true
    }

    val maxMpaaRatingElement: Movie?
        /**
         * Returns the movie with the highest MPAA rating from the collection.
         *
         * @return            the movie with the highest MPAA rating, or null if the collection is empty
         */
        get() {
            for (movie in collection) {
                if (movie.getMpaaRating() === MpaaRating.PG) return movie // EBATKA
            }
            for (movie in collection) {
                if (movie.getMpaaRating() === MpaaRating.R) return movie
            }
            for (movie in collection) {
                if (movie.getMpaaRating() === MpaaRating.NC_17) return movie
            }
            return null
        }

    val operatorsSorted: LinkedList<Person>
        /**
         * Retrieves and returns a sorted list of operators from the collection of movies.
         *
         * @return            the sorted list of operators
         */
        get() {
            val personLinkedList = LinkedList<Person>()
            for (movie in collection) {
                val operator: Person? = movie.getOperator()
                if (operator != null) personLinkedList.add(operator)
            }
            personLinkedList.sort()
            return personLinkedList
        }

    /**
     * Removes the specified movie from the collection.
     *
     * @param  movie   the movie to be removed
     */
    fun removeElement(movie: Movie) {
        collection.remove(movie)
    }
    fun getDateOfCreation(): LocalDateTime {
        return dateOfCreation
    }
    fun getCollection(): LinkedList<Movie> {
        return collection
    }

    override fun toString(): String {
        return "MovieCollection{" +
                "dateOfCreation=" + dateOfCreation +
                ", collection=" + collection +
                '}'
    }
}