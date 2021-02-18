import application.Config;
import data.DataBase.Dao.MonthDataDao;
import data.DataBase.Dao.TariffDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
}
