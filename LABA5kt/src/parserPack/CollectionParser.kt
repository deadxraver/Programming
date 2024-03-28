package parserPack

import mainPack.MovieCollection

object CollectionParser {
    /**
     * Parses the given MovieCollection, removing duplicate movies and invalid entries.
     *
     * @param  movieCollection   the original MovieCollection to be parsed
     * @return                  the parsed MovieCollection with duplicates and invalid entries removed
     */
    fun parse(movieCollection: MovieCollection): MovieCollection {
        val parsedMovieCollection = MovieCollection(movieCollection.getDateOfCreation())
        for (movie in movieCollection.getCollection()) {
            for (movie1 in movieCollection.getCollection()) {
                if (movie.getId() == movie1.getId() && movie != movie1) {
                    movieCollection.removeElement(movie)
                }
            }
        }
        for (movie in movieCollection.getCollection()) {
            var toBeRemoved = false
            if (movie.getId() <= 0) {
                toBeRemoved = true
            }
            if (movie.getOscarsCount() <= 0) {
                toBeRemoved = true
            }
            if (movie.getOperator() != null) {
                if (movie.getOperator()!!.getName().replace(" ", "").isEmpty()
                ) {
                    movie.setOperator(null)
                }
            }
            if (movie.getCoordinates() == null) {
                toBeRemoved = true
            } else {
                if (movie.getCoordinates().getX() == null) {
                    toBeRemoved = true
                }
                if (movie.getCoordinates().getY() == null || movie.getCoordinates().getY() > 274) {
                    toBeRemoved = true
                }
            }
            if (movie.getCreationDate() == null) {
                toBeRemoved = true
            }
            if (movie.getGenre() == null) {
                toBeRemoved = true
            }
            if (!toBeRemoved) {
                parsedMovieCollection.addElement(movie)
            }
        }
        return parsedMovieCollection
    }
}