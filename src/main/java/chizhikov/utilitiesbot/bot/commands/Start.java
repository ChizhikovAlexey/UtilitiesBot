package chizhikov.utilitiesbot.bot.commands;

import chizhikov.utilitiesbot.bot.usersdata.BotState;
import chizhikov.utilitiesbot.bot.usersdata.Chats;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class Start extends AbstractCommand {

    public Start(String commandIdentifier, String description, Chats chats) {
        super(commandIdentifier, description, chats);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        chats.updateState(chat, BotState.MAIN);
        sendAnswer(absSender, chat, getCommandIdentifier(), "Привет!\nЯ – бот для заполнения данных о коммунальных расходах.");
    }
}
