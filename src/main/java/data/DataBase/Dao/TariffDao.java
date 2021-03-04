package data.DataBase.Dao;

import data.DataBase.Entities.Tariff;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface TariffDao {
    List<Tariff> findAll();

    Tariff findByDate(LocalDate date);
}
