package commands;

import commandhelper.Command;
import servercommunication.Message;
import elements.MovieCollection;

import java.io.Serial;
import java.io.Serializable;

public class Help implements Command, Serializable {
    @Override
    public Message execute(MovieCollection movieCollection, Object object) {
        return new Message(false,
                """
                help : get info about commands
                info : get info about collection
                show : get all collection elements
                add : add a new element to the collection
                update id : update element by its id
                remove_by_id id : remove element by its id
                clear : clear collection
                execute_script file_name : execute script
                exit : disconnect from the server and stop the program
                remove_head : get first collection element and remove it from collection
                add_if_max : add a new element to the collection if it is greater then max element
                remove_lower : remove all elements lower than current
                remove_all_by_oscars_count oscarsCount : remove all elements having the same oscarsCount field
                max_by_mpaa_rating : get an element having max mpaaRating field
                print_field_ascending_operator : get all operators ascending
                """
        );
    }

    @Serial
    private static final long serialVersionUID = -7718441333535198685L;
}
