package localcommands;

import commandhelper.LocalCommand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript implements LocalCommand {
    @Override
    public Scanner execute(Object o) {
        String fileName = (String) o;
        try {
            return new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
