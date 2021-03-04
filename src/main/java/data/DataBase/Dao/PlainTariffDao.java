package data.DataBase.Dao;

import data.DataBase.Entities.Tariff;
import org.springframework.lang.Nullable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlainTariffDao implements TariffDao {
    private Connection getConnection() throws SQLException {
        String DATABASE_URL = System.getenv("JDBC_DATABASE_URL");
        if (DATABASE_URL == null){
            DATABASE_URL = "jdbc:postgresql://localhost:5432/utilities_data";
            return DriverManager.getConnection(DATABASE_URL, "postgres", "");
        } else {
            return DriverManager.getConnection(DATABASE_URL);
        }
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Problem closing connection to the database!");
        }
    }

    @Override
    public List<Tariff> findAll() {
        List<Tariff> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from tariffs");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setColdWater(resultSet.getFloat("cold_water"));
                tariff.setHotWater(resultSet.getFloat("hot_water"));
                tariff.setElectricity(resultSet.getFloat("electricity"));
                tariff.setDrainage(resultSet.getFloat("drainage"));
                tariff.setUpdateDate(resultSet.getDate("update_date"));
                result.add(tariff);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Problem when executing SELECT! " + ex);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    @Nullable
    public Tariff findByDate(LocalDate date) {
        Tariff result = new Tariff();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from tariffs where update_date <= '" +
                            date.toString() +
                            "' order by update_date DESC");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setColdWater(resultSet.getFloat("cold_water"));
                result.setHotWater(resultSet.getFloat("hot_water"));
                result.setElectricity(resultSet.getFloat("electricity"));
                result.setDrainage(resultSet.getFloat("drainage"));
                result.setUpdateDate(resultSet.getDate("update_date"));
            } else {
                return null;
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Problem when executing SELECT! " + ex);
        } finally {
            closeConnection(connection);
        }
        return result;
    }
}
