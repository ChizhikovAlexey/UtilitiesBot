package bot;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TelegramBot extends TelegramLongPollingCommandBot {
    public TelegramBot(List<BotCommand> listOfCommands) {
        listOfCommands.forEach(this::register);
    }

    @Override
    public String getBotUsername() {
        return "Utilities23Bot";
    }

    @Override
    public String getBotToken() {
        return "1645730581:AAFZQKBSz6HA74FvEIQsiU5v1jjlm6w6sAs";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        String id = msg.getChatId().toString();
        String text = "Сообщения без команд пока не поддерживаются!";
        sendMessage(text, id);
    }

    private void sendMessage(String text, String id) {
        try {
            execute(SendMessage.builder().text(text).chatId(id).build());
        } catch (TelegramApiException exc) {
            System.out.println("Error sendind message to " + id + "!\n" + exc);
        }
    }
}
