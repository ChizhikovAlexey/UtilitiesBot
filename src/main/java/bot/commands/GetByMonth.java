package bot.commands;

import bot.usersdata.BotState;
import bot.usersdata.Chats;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class GetByMonth extends AbstractCommand {

    public GetByMonth(String commandIdentifier, String description, Chats chats) {
        super(commandIdentifier, description, chats);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        chats.updateState(chat, BotState.GET_BY_MONTH);
        String text = "Напиши год и месяц, информацию по которым нужно получить, в формате ГГГГ-ММ!";
        sendAnswer(absSender, chat, getCommandIdentifier(), text);
    }
}
