package chizhikov.utilitiesbot.data.db.dao;

import chizhikov.utilitiesbot.data.db.entities.MonthData;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Component("PlainMonthDao")
public class PlainMonthDataDao implements MonthDataDao {

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
                monthData.setDate(resultSet.getDate("date").toLocalDate());
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
    public MonthData findByDate(LocalDate date) {
        MonthData result = new MonthData();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data" +
                            " where date = " + date.toString() +
                            " order by date ASC");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setDate(resultSet.getDate("date").toLocalDate());
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
    @Nullable
    public MonthData findByYearMonth(YearMonth yearMonth) {
        MonthData result = new MonthData();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data" +
                            " where date_part('year', date) = " + yearMonth.getYear() +
                            " AND date_part('month', date) = " + yearMonth.getMonthValue() +
                            " order by date ASC");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result.setDate(resultSet.getDate("date").toLocalDate());
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
    public List<MonthData> findActualAndPreviousMonthsByDate(LocalDate date) {
        List<MonthData> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data " +
                            "where date <= '" +
                            date.toString() +
                            "' order by date DESC LIMIT 2");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MonthData monthData = new MonthData();
                monthData.setDate(resultSet.getDate("date").toLocalDate());
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
    public List<MonthData> findActualAndPreviousMonthsByYearMonth(YearMonth yearMonth) {
        List<MonthData> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from month_data " +
                            "where date <= '" + yearMonth.atEndOfMonth() +
                            "' order by date DESC LIMIT 2");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MonthData monthData = new MonthData();
                monthData.setDate(resultSet.getDate("date").toLocalDate());
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
            statement.setDate(6, Date.valueOf(monthData.getDate()));
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Problem executing INSERT! " + ex);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Deletes all chizhikova.utilitiesbot.data exists for these year and month
     *
     * @param yearMonth - year and month
     */
    @Override
    public void deleteByYearMonth(YearMonth yearMonth) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from month_data where " +
                            "date_part('year', date) = " + yearMonth.getYear() + " AND " +
                            "date_part('month', date) = " + yearMonth.getMonthValue());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Problem executing deleteByYearMonth! " + ex);
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Deletes a row with the specified date
     *
     * @param date - row with this exact date will be deleted
     */
    @Override
    public void deleteByDate(LocalDate date) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE from month_data where date = '" + date.toString() + "'");
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Problem executing deleteByDate! " + ex);
        } finally {
            closeConnection(connection);
        }
    }
}
