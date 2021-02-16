package DataManagement.DataBase.Dao;

import DataManagement.DataBase.Entities.MonthData;
import org.springframework.lang.Nullable;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class PlainMonthDataDao implements MonthDataDao {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/utilities_data",
                "postgres", "");
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
    public List<MonthData> findAll() {
        List<MonthData> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data order by date ASC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MonthData monthData = new MonthData();
                monthData.setDate(resultSet.getDate("date"));
                monthData.setHotWaterBath(resultSet.getInt("hot_water_bath"));
                monthData.setColdWaterBath(resultSet.getInt("cold_water_bath"));
                monthData.setHotWaterKitchen(resultSet.getInt("hot_water_kitchen"));
                monthData.setColdWaterKitchen(resultSet.getInt("cold_water_kitchen"));
                monthData.setElectricity(resultSet.getInt("electricity"));
                result.add(monthData);
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
    public MonthData findByDate(Date date) {
        MonthData result = new MonthData();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data" +
                            " where date_part('year', date) = " + (1900 + date.getYear()) +
                            " AND date_part('month', date) = " + (1 + date.getMonth()) +
                            " order by date ASC");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setDate(resultSet.getDate("date"));
                result.setHotWaterBath(resultSet.getInt("hot_water_bath"));
                result.setColdWaterBath(resultSet.getInt("cold_water_bath"));
                result.setHotWaterKitchen(resultSet.getInt("hot_water_kitchen"));
                result.setColdWaterKitchen(resultSet.getInt("cold_water_kitchen"));
                result.setElectricity(resultSet.getInt("electricity"));
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

    @Override
    public List<MonthData> findActualAndPreviousMonthsByDate(Date date) {
        List<MonthData> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data " +
                            "where date <= '" +
                            date.toString() +
                            "' order by date DESC offset 0 fetch next 2 rows only");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MonthData monthData = new MonthData();
                monthData.setDate(resultSet.getDate("date"));
                monthData.setHotWaterBath(resultSet.getInt("hot_water_bath"));
                monthData.setColdWaterBath(resultSet.getInt("cold_water_bath"));
                monthData.setHotWaterKitchen(resultSet.getInt("hot_water_kitchen"));
                monthData.setColdWaterKitchen(resultSet.getInt("cold_water_kitchen"));
                monthData.setElectricity(resultSet.getInt("electricity"));
                result.add(monthData);
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
    public void insert(MonthData monthData) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into month_data (electricity, hot_water_bath, cold_water_bath, " +
                            "hot_water_kitchen, cold_water_kitchen, date) values (?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, monthData.getElectricity());
            statement.setInt(2, monthData.getHotWaterBath());
            statement.setInt(3, monthData.getColdWaterBath());
            statement.setInt(4, monthData.getHotWaterKitchen());
            statement.setInt(5, monthData.getColdWaterKitchen());
            statement.setDate(6, (java.sql.Date) monthData.getDate());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Problem executing INSERT! " + ex);
        } finally {
            closeConnection(connection);
        }
    }
}
