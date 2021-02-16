package DataManagement.DataBase.Dao;

import DataManagement.DataBase.Entities.Tariff;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

public interface TariffDao {
    List<Tariff> findAll();

    @Nullable
    Tariff findByDate(Date month);
}
