import application.Config;
import data.DataBase.Dao.MonthDataDao;
import data.DataBase.Dao.TariffDao;
import data.DataBase.Entities.MonthData;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class ApplicationTests {
    private final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

    @Test
    void monthDataDaoTest() {
        assert ((MonthDataDao) ctx.getBean("PlainMonthDataDao")).findAll().size() > 0;
    }

    @Test
    void tariffsDaoTest() {
        assert ((TariffDao) ctx.getBean("PlainTariffDao")).findAll().size() > 0;
    }

    @Test
    void monthDataParseTest() {
        LocalDate now = LocalDate.now();
        MonthData monthData = MonthData.parse("43500    460    518    112   178 " + now.toString());
        assert monthData.getDate().equals(now);
        assert monthData.getElectricity() == 43500;
        assert monthData.getHotWaterBath() == 460;
        assert monthData.getColdWaterBath() == 518;
        assert monthData.getHotWaterKitchen() == 112;
        assert monthData.getColdWaterKitchen() == 178;
    }
}
