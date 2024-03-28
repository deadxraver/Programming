package elementsPack;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    public Person(String name, LocalDateTime birthday, Color hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null

    @Override
    public String toString() {
        return "name - " + name +
                ", birthday - " + birthday +
                ", hair color - " + hairColor +
                ", nationality - " + nationality;
    }
}
