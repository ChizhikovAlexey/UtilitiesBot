package chizhikov.utilitiesbot.data;

import chizhikov.utilitiesbot.data.db.dao.MonthDataDao;
import chizhikov.utilitiesbot.data.db.dao.TariffDao;
import chizhikov.utilitiesbot.data.db.entities.MonthData;
import chizhikov.utilitiesbot.data.db.entities.Tariff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Component("DataManager")
public class DataManager {
    private final MonthDataDao monthDataDao;
    private final TariffDao tariffDao;

    @Autowired
    public DataManager(MonthDataDao monthDataDao, TariffDao tariffDao) {
        this.monthDataDao = monthDataDao;
        this.tariffDao = tariffDao;
    }

    @Nullable
    public TextReport getReportByDate(LocalDate date) {
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

    public void insertMonthData(MonthData monthData) {
        monthDataDao.insert(monthData);
    }

    public void deleteDataByYearMonth(YearMonth yearMonth) {
        monthDataDao.deleteByYearMonth(yearMonth);
    }

    public List<MonthData> getAllMonths() {
        return monthDataDao.findAll();
    }

    public TextReport getReportByYearMonth(YearMonth yearMonth) {
        List<MonthData> list = monthDataDao.findActualAndPreviousMonthsByYearMonth(yearMonth);
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

    public Tariff getTariffByDate(LocalDate date) {
        return tariffDao.findByDate(date);
    }
}
