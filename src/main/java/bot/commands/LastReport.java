package bot.commands;

import bot.usersdata.BotState;
import bot.usersdata.Chats;
import data.DataManager;
import data.TextReport;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.LocalDate;

public class LastReport extends DataCommand {


    public LastReport(String commandIdentifier, String description, Chats chats, DataManager dataManager) {
        super(commandIdentifier, description, chats, dataManager);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        chats.updateState(chat, BotState.MAIN);
        TextReport report = dataManager.getReportByDate(LocalDate.now());
        String text = (report != null) ? report.toString() : "Нет информации";
        sendAnswer(absSender, chat, getCommandIdentifier(), text);
    }
}
