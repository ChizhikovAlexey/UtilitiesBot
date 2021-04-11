package chizhikov.utilitiesbot.data.db.dao;

import chizhikov.utilitiesbot.data.db.entities.MonthData;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public interface MonthDataDao {
    List<MonthData> findAll();

    @Nullable
    MonthData findByDate(LocalDate date);

    List<MonthData> findActualAndPreviousMonthsByDate(LocalDate date);

    List<MonthData> findActualAndPreviousMonthsByYearMonth(YearMonth yearMonth);

    void insert(MonthData monthData);

    void deleteByDate(LocalDate date);

    void deleteByYearMonth(YearMonth yearMonth);

    @Nullable
    MonthData findByYearMonth(YearMonth yearMonth);
}
