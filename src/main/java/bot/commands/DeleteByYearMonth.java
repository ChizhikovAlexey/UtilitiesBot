package bot.commands;

import bot.usersdata.BotState;
import bot.usersdata.Chats;
import data.DataManager;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class DeleteByYearMonth extends AbstractCommand {

    public DeleteByYearMonth(String commandIdentifier, String description, Chats chats) {
        super(commandIdentifier, description, chats);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        chats.updateState(chat, BotState.DELETE_BY_YEAR_MONTH);
        String text = "Напиши год и месяц, которые нужно удалить, в формате ГГГГ-ММ!";
        sendAnswer(absSender, chat, getCommandIdentifier(), text);
    }
}
