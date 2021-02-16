package Application;

import DataManagement.DataBase.Entities.MonthData;
import DataManagement.DataManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.Calendar;

public class ApplicationStarter {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

        DataManager dataManager = (DataManager) ctx.getBean("DataManager");

        System.out.println(dataManager.getReportByDate(Calendar.getInstance().getTime()));

        ctx.close();
    }
}
