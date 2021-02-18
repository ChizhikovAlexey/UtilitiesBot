package data.DataBase.Dao;

import data.DataBase.Entities.MonthData;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

public interface MonthDataDao {
    List<MonthData> findAll();

    @Nullable
    MonthData findByDate(Date date);

    List<MonthData> findActualAndPreviousMonthsByDate (Date date);

    void insert(MonthData monthData);
}
