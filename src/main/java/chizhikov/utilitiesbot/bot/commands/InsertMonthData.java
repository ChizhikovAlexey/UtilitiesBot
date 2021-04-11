package chizhikov.utilitiesbot.bot.commands;

import chizhikov.utilitiesbot.bot.usersdata.BotState;
import chizhikov.utilitiesbot.bot.usersdata.Chats;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class InsertMonthData extends AbstractCommand {
    public InsertMonthData(String commandIdentifier, String description, Chats chats) {
        super(commandIdentifier, description, chats);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        chats.updateState(chat, BotState.INSERT_MONTH_DATA);
        String text = "Напиши сообщение следующего вида:";
        sendAnswer(absSender, chat, getCommandIdentifier(), text);
        text = """
                электричество
                горячая вода 848497 (санузел)
                холодная вода 848198 (санузел)
                горячая  вода 848565 (кухня)
                холодная вода 858292 (кухня)
                """;
        sendAnswer(absSender, chat, getCommandIdentifier(), text);
        text = "(числа можно разделять пробелами или переходами на следующую строку)";
        sendAnswer(absSender, chat, getCommandIdentifier(), text);
    }
}
