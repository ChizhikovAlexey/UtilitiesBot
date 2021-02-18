package application;

import bot.TelegramBot;
import bot.commands.LastReport;
import bot.commands.Start;
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
    @Bean("ListOfCommands")
    public ArrayList<BotCommand> listOfCommands(@Qualifier("DataManager") DataManager dataManager) {
        ArrayList<BotCommand> commands = new ArrayList<>();
        commands.add(new Start("start", "Начало работы с ботом"));
        commands.add(new LastReport("last_report", "Сгенерировать последний доступный отчёт", dataManager));
        return commands;
    }

    @Bean("TelegramBot")
    public TelegramBot telegramBot(@Qualifier("ListOfCommands") List<BotCommand> listOfCommands) {
        return new TelegramBot(listOfCommands);
    }
}
