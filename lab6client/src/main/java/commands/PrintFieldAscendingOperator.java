package commands;

import commandhelper.Command;
import commandhelper.Message;
import elements.MovieCollection;
import elements.Person;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class PrintFieldAscendingOperator implements Command, Serializable {

    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        StringBuilder builder = new StringBuilder();
        Person[] people = movieCollection.getOperatorList();
        Arrays.sort(people);
        for (Person person : people) {
            builder.append(person);
        }
        return new Message(
                false,
                builder.toString()
        );
    }

    @Serial
    private static final long serialVersionUID = 3073911489917437176L;
}
