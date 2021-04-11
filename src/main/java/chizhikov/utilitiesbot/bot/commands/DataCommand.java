package chizhikov.utilitiesbot.bot.commands;

import chizhikov.utilitiesbot.bot.usersdata.Chats;
import chizhikov.utilitiesbot.data.DataManager;

public abstract class DataCommand extends AbstractCommand {
    protected DataManager dataManager;

    public DataCommand(String commandIdentifier, String description, Chats chats, DataManager dataManager) {
        super(commandIdentifier, description, chats);
        this.dataManager = dataManager;
    }
}
