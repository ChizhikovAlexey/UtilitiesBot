package data.DataBase.Dao;

import data.DataBase.Entities.MonthData;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface MonthDataDao {
    List<MonthData> findAll();

    @Nullable
    MonthData findByDate(LocalDate date);

    List<MonthData> findActualAndPreviousMonthsByDate(LocalDate date);

    void insert(MonthData monthData);
}
