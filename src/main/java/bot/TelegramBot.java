package bot;

import bot.usersdata.BotState;
import bot.usersdata.Chats;
import data.DataBase.Entities.MonthData;
import data.DataManager;
import data.TextReport;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TelegramBot extends TelegramLongPollingCommandBot {
    private final Chats chats;
    private final DataManager dataManager;
    private final String username;
    private final String token;

    public TelegramBot(String username, String token, List<BotCommand> listOfCommands, DataManager dataManager, Chats chats) {
        this.username = username;
        this.token= token;
        this.chats = chats;
        this.dataManager = dataManager;
        listOfCommands.forEach(this::register);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();
        BotState state = chats.getState(chatId);
        switch (state) {
            case MAIN -> sendMessage("Используй команды для управления ботом!", chatId);
            case DELETE_BY_YEAR_MONTH -> {
                chats.updateState(chatId, BotState.MAIN);
                try {
                    dataManager.deleteDataByYearMonth(YearMonth.parse(message.getText()));
                    sendMessage("Готово!", chatId);
                } catch (DateTimeParseException exc) {
                    sendMessage("Error parsing YearMonth!", chatId);
                }
            }
            case GET_BY_MONTH -> {
                chats.updateState(chatId, BotState.MAIN);
                try {
                    TextReport report = dataManager.getReportByYearMonth(YearMonth.parse(message.getText()));
                    sendMessage(report.toString(), chatId);
                } catch (DateTimeParseException exc) {
                    sendMessage("Error parsing YearMonth!", chatId);
                }
            }
            case INSERT_MONTH_DATA -> {
                chats.updateState(chatId, BotState.MAIN);
                try {
                    dataManager.insertMonthData(MonthData.parse(message.getText()));
                    sendMessage("Готово!", chatId);
                } catch (Exception exc) {
                    sendMessage("Error parsing MonthData: " + exc, chatId);
                }
            }
            default -> sendMessage("Unknown state???", chatId);
        }
    }

    private void sendMessage(String text, String id) {
        try {
            execute(SendMessage.builder().text(text).chatId(id).build());
        } catch (TelegramApiException exc) {
            System.out.println("Error sending message to " + id + "!\n" + exc);
        }
    }
}
