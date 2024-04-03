package commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript {
    public static Scanner execute(String fileName) throws FileNotFoundException {
        return new Scanner(new FileInputStream(fileName));
    }
}
