package application;

import bot.TelegramBot;
import bot.commands.DeleteByYearMonth;
import bot.commands.InsertMonthData;
import bot.commands.LastReport;
import bot.commands.Start;
import bot.usersdata.Chats;
import data.DataBase.Dao.MonthDataDao;
import data.DataBase.Dao.PlainMonthDataDao;
import data.DataBase.Dao.PlainTariffDao;
import data.DataBase.Dao.TariffDao;
import data.DataManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan
public class Config {
    @Bean("PlainMonthDataDao")
    public MonthDataDao monthDataDao() {
        return new PlainMonthDataDao();
    }

    @Bean("PlainTariffDao")
    public TariffDao tariffDao() {
        return new PlainTariffDao();
    }

    @Bean("DataManager")
    public DataManager dataManager(@Qualifier("PlainMonthDataDao") MonthDataDao monthDataDao,
                                   @Qualifier("PlainTariffDao") TariffDao tariffDao) {
        return new DataManager(monthDataDao, tariffDao);
    }

    @Bean("Chats")
    public Chats chats() {
        return new Chats();
    }

    @Bean("ListOfCommands")
    public ArrayList<BotCommand> listOfCommands(@Qualifier("Chats") Chats chats,
                                                @Qualifier("DataManager") DataManager dataManager) {
        ArrayList<BotCommand> commands = new ArrayList<>();
        commands.add(new Start("start", "Начало работы с ботом", chats));
        commands.add(new LastReport("last_report", "Сгенерировать последний доступный отчёт", chats, dataManager));
        commands.add(new DeleteByYearMonth("delete_by_year_month",
                "Удалить информацию о каком-то месяце", chats));
        commands.add(new InsertMonthData("insert_month_data",
                "Ввести новые показания", chats));
        return commands;
    }

    @Bean("TelegramBot")
    public TelegramBot telegramBot(@Qualifier("ListOfCommands") List<BotCommand> listOfCommands,
                                   @Qualifier("Chats") Chats chats,
                                   @Qualifier("DataManager") DataManager dataManager) {
        return new TelegramBot(listOfCommands, dataManager, chats);
    }
}
