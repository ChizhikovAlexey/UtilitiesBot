package bot.commands;

import bot.usersdata.Chats;
import data.ods.OdsManager;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.io.File;

public class CreateOds extends AbstractCommand{

    private final OdsManager odsManager;

    public CreateOds(String commandIdentifier, String description, Chats chats, OdsManager odsManager) {
        super(commandIdentifier, description, chats);
        this.odsManager = odsManager;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        System.out.println("BEGINNING OF CreateOds execute()");
        System.out.println("STARTING CREATING FILE");
        File file = odsManager.getOds();
        System.out.println("FILE CREATED! SENDING!");
        sendFile(absSender, chat, getCommandIdentifier(), file);
        System.out.println("ENDING OF CreateOds execute()");
    }
}
