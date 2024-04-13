package localcommands;

import commandhelper.LocalCommand;

import java.util.Scanner;

public class Exit implements LocalCommand {
    @Override
    public Scanner execute(Object o) {
        System.exit(0);
        return null;
    }
}
