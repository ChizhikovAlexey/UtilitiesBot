package application;

import bot.TelegramBot;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;

public class ApplicationStarter {
    public static void main(String[] args) {
        if (new File(".env").isFile()) {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
        }
        System.getenv().forEach(System::setProperty);
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        TelegramBot telegramBot = (TelegramBot) ctx.getBean("TelegramBot");
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        ctx.close();
    }
}
