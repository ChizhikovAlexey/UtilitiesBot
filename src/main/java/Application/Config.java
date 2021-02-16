package Application;

import DataManagement.DataBase.Dao.MonthDataDao;
import DataManagement.DataBase.Dao.PlainMonthDataDao;
import DataManagement.DataBase.Dao.PlainTariffDao;
import DataManagement.DataBase.Dao.TariffDao;
import DataManagement.DataManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
}
