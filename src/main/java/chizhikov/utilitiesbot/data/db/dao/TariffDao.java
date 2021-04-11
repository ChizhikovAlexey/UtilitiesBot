package chizhikov.utilitiesbot.data.db.dao;

import chizhikov.utilitiesbot.data.db.entities.Tariff;

import java.time.LocalDate;
import java.util.List;

public interface TariffDao {
    List<Tariff> findAll();

    Tariff findByDate(LocalDate date);
}
