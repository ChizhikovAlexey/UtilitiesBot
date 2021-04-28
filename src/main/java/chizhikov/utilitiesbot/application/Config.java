package chizhikov.utilitiesbot.application;

import chizhikov.utilitiesbot.bot.TelegramBot;
import chizhikov.utilitiesbot.bot.commands.*;
import chizhikov.utilitiesbot.bot.usersdata.Chats;
import chizhikov.utilitiesbot.data.DataManager;
import chizhikov.utilitiesbot.data.ods.OdsManager;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("chizhikov.utilitiesbot")
public class Config {
    @Bean("HibernateSessionFactory")
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().
                setProperty("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL")).
                configure("hibernate.cfg.xml").
                buildSessionFactory();
    }

    @Bean("ListOfCommands")
    public ArrayList<BotCommand> listOfCommands(Chats chats,
                                                DataManager dataManager,
                                                OdsManager odsManager) {
        ArrayList<BotCommand> commands = new ArrayList<>();
        commands.add(new Start("start", "Начало работы с ботом", chats));
        commands.add(new LastReport("last_report", "Сгенерировать последний доступный отчёт", chats, dataManager));
        commands.add(new DeleteByYearMonth("delete_by_year_month",
                "Удалить информацию о каком-то месяце", chats));
        commands.add(new InsertMonthData("insert_month_data",
                "Ввести новые показания", chats));
        commands.add(new GetByMonth("get_by_month",
                "Получить отчёт за конкретный месяц", chats));
        commands.add(new CreateOds("create_ods",
                "Создать .ods (Excel) файл", chats, odsManager));
        return commands;
    }

    @Bean("TelegramBot")
    public TelegramBot telegramBot(List<BotCommand> listOfCommands,
                                   Chats chats,
                                   DataManager dataManager) {
        return new TelegramBot(
                System.getProperty("BotUsername"),
                System.getProperty("BotToken"),
                listOfCommands,
                dataManager,
                chats);
    }
}
