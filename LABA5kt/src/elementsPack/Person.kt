package elementsPack

import java.time.LocalDateTime
import kotlin.Comparable
import kotlin.Int
import kotlin.String

/**
 * Class describing operator of the movie. Can be null
 *
 *
 * name - cannot be null or empty
 *
 *
 * birthday, hairColor, nationality - CAN be null
 */
class Person(
    private var name: String,
    private var birthday: LocalDateTime,
    private var hairColor: Color,
    private var nationality: Country
) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return this.birthday.compareTo(other.birthday)
    }

    override fun toString(): String {
        return "name - $name" +
                ", birthday - $birthday" +
                ", hair color - $hairColor" +
                ", nationality - $nationality"
    }

    fun getBirthday(): LocalDateTime {
        return birthday
    }

    fun getHairColor(): Color {
        return hairColor
    }

    fun getNationality(): Country {
        return nationality
    }

    fun getName(): String {
        return name
    }
}