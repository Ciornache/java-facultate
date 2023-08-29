package org.example;

import java.io.IOException;
import java.util.List;

public class CommandHandler {

    private final List<String> commandList = List.of(new String[]{"add", "save", "list", "load", "view"});

    private final AddCommand addCommand;
    private final ListCommand listCommand;
    private final SaveCommand saveCommand;
    private final LoadCommand loadCommand;

    private final ViewCommand viewCommand;

    public CommandHandler() {
        saveCommand = new SaveCommand();
        loadCommand = new LoadCommand();
        listCommand = new ListCommand();
        addCommand = new AddCommand();
        viewCommand = new ViewCommand();
    }

    public void execute(String command) throws IncorrectCommandName, IOException, InvalidDocumentName {

        boolean ok = checkCommand(command);
        if(!ok)
            throw new IncorrectCommandName("Invalid command name");

        switch(command)
        {
            case "add":
                addCommand.execute();
                break;
            case "save":
                saveCommand.execute();
                break;
            case "list":
                listCommand.execute();
                break;
            case "load":
                loadCommand.execute();
                break;
            case "view":
                viewCommand.execute();
                break;
        }
    }

    public boolean checkCommand(String command) {
        for(String com : commandList)
        {
            if(command.equals(com))
                return true;
        }
        return false;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public AddCommand getAddCommand() {
        return addCommand;
    }

    public ListCommand getListCommand() {
        return listCommand;
    }

    public SaveCommand getSaveCommand() {
        return saveCommand;
    }

    public LoadCommand getLoadCommand() {
        return loadCommand;
    }

    public ViewCommand getViewCommand() {
        return viewCommand;
    }
}
