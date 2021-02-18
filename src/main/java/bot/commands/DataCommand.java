package bot.commands;

import bot.usersdata.Chats;
import data.DataManager;

public abstract class DataCommand extends AbstractCommand {
    protected DataManager dataManager;

    public DataCommand(String commandIdentifier, String description, Chats chats, DataManager dataManager) {
        super(commandIdentifier, description, chats);
        this.dataManager = dataManager;
    }
}
