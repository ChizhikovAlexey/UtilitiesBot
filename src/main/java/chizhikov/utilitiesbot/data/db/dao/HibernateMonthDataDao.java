package chizhikov.utilitiesbot.data.db.dao;

import chizhikov.utilitiesbot.data.db.entities.MonthData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Repository("HibernateMonthDataDao")
public class HibernateMonthDataDao implements MonthDataDao {
    private final SessionFactory sessionFactory;

    @Autowired
    HibernateMonthDataDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<MonthData> findAll() {
        try (final Session session = sessionFactory.openSession()) {
            return session.createQuery("from MonthData m").list();
        }
    }

    @Override
    public MonthData findByDate(LocalDate date) {
        try (final Session session = sessionFactory.openSession()) {
            return session.get(MonthData.class, date);
        }
    }

    @Override
    public List<MonthData> findActualAndPreviousMonthsByDate(LocalDate date) {
        try (final Session session = sessionFactory.openSession()) {
            return session.createQuery("from MonthData m where m.date <= :date order by date desc").
                    setParameter("date", date).setMaxResults(2).list();
        }
    }

    @Override
    public List<MonthData> findActualAndPreviousMonthsByYearMonth(YearMonth yearMonth) {
        YearMonth tmp = yearMonth.plusMonths(1);
        LocalDate date = LocalDate.of(tmp.getYear(), tmp.getMonth(), 1).minusDays(1);
        return findActualAndPreviousMonthsByDate(date);
    }

    @Override
    public void insert(MonthData monthData) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(monthData);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteByDate(LocalDate date) {
        try (final Session session = sessionFactory.openSession()) {
            MonthData monthData = findByDate(date);
            session.beginTransaction();
            session.delete(monthData);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteByYearMonth(YearMonth yearMonth) {
        try (final Session session = sessionFactory.openSession()) {
            MonthData monthData = findByYearMonth(yearMonth);
            session.beginTransaction();
            session.delete(monthData);
            session.getTransaction().commit();
        }
    }

    @Override
    public MonthData findByYearMonth(YearMonth yearMonth) {
        LocalDate date =  LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1).plusMonths(1).minusDays(1);
        try (final Session session = sessionFactory.openSession()) {
            return (MonthData) session.createQuery("from MonthData m where m.date < :date order by date desc").
                    setParameter("date", date).setMaxResults(1);
        }
    }
}
