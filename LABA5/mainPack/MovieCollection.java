package mainPack;

import elementsPack.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class MovieCollection {
    public MovieCollection() {
        this.dateOfCreation = LocalDateTime.now();
    }
    public MovieCollection(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    private LocalDateTime dateOfCreation;
    private final LinkedList<Movie> collection = new LinkedList<>();

    public void addElement(Coordinates coordinates, MovieGenre genre,
                           MpaaRating mpaaRating, String name, Person operator, int oscarsCount) {
        long id;
        do {
            id = generateId();
        } while (!validateId(id));
        LocalDate creationDate = LocalDate.now();
        collection.add(new Movie(id, name, coordinates, creationDate, oscarsCount, genre, mpaaRating, operator));
    }

    public long generateId() {
        return (long)(Math.random() * Long.MAX_VALUE);
    }

    public boolean validateId(long id) {
        for (Movie movie : collection) {
            if (movie.getId() == id) return false;
        }
        return true;
    }

    public int getNumberOfElements() {
        return collection.size();
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LinkedList<Movie> getCollection() {
        return collection;
    }
}
