package commandsPack;


import mainPack.MovieCollection;

import java.util.Scanner;

public abstract class Command {
    protected Scanner reader;
    protected MovieCollection collection;
    protected boolean readingFromFile = false;

    public void execute() {

    }

    public Scanner getReader() {
        return reader;
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    public void setCollection(MovieCollection collection) {
        this.collection = collection;
    }
}
