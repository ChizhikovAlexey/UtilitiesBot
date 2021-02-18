package data;

import data.DataBase.Dao.MonthDataDao;
import data.DataBase.Dao.TariffDao;
import data.DataBase.Entities.MonthData;
import data.DataBase.Entities.Tariff;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

public class DataManager {

    private final MonthDataDao monthDataDao;
    private final TariffDao tariffDao;

    public DataManager(MonthDataDao monthDataDao, TariffDao tariffDao) {
        this.monthDataDao = monthDataDao;
        this.tariffDao = tariffDao;
    }

    @Nullable
    public TextReport getReportByDate(Date date) {
        List<MonthData> list = monthDataDao.findActualAndPreviousMonthsByDate(date);
        MonthData previousMonth = list.get(1);
        MonthData actualMonth = list.get(0);

        if (previousMonth == null || actualMonth == null) {
            return null;
        }

        Tariff tariff = tariffDao.findByDate(actualMonth.getDate());

        if (tariff == null) {
            return null;
        }

        return new TextReport(previousMonth, actualMonth, tariff);
    }

    public void updateData(MonthData monthData) {
        monthDataDao.insert(monthData);
    }
}
