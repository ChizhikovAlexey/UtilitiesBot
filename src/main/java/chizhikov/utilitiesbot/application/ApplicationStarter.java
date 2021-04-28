package chizhikov.utilitiesbot.application;

import chizhikov.utilitiesbot.bot.TelegramBot;
import chizhikov.utilitiesbot.data.DataManager;
import chizhikov.utilitiesbot.data.db.dao.HibernateMonthDataDao;
import chizhikov.utilitiesbot.data.db.entities.MonthData;
import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.LocalDate;
import java.time.YearMonth;

public class ApplicationStarter {
    public static void main(String[] args) {
        System.getenv().forEach(System::setProperty);
        Dotenv.configure().ignoreIfMissing().systemProperties().load();
        System.out.println(System.getProperty("DATABASE_URL"));
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        TelegramBot telegramBot = (TelegramBot) ctx.getBean("TelegramBot");
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
