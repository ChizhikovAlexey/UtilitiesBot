package bot.commands;

import data.DataManager;

public abstract class DataCommand extends AbstractCommand{
    protected DataManager dataManager;

    public DataCommand(String commandIdentifier, String description, DataManager dataManager) {
        super(commandIdentifier, description);
        this.dataManager = dataManager;
    }
}
