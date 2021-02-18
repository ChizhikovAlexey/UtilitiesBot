package bot.commands;

import data.DataManager;
import data.TextReport;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Calendar;

public class LastReport extends DataCommand {
    public LastReport(String commandIdentifier, String description, DataManager dataManager) {
        super(commandIdentifier, description, dataManager);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        TextReport report = dataManager.getReportByDate(Calendar.getInstance().getTime());
        String text = (report != null) ? report.toString() : "Нет информации";
        sendAnswer(absSender, chat, getCommandIdentifier(),text);
    }
}
