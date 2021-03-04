package data.ods;

import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
import data.DataBase.Entities.MonthData;
import data.DataBase.Entities.Tariff;
import data.DataManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OdsManager {
    private final DataManager dataManager;

    public OdsManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public File getOds() {
        List<MonthData> list = dataManager.getAllMonths();
        File result = new File("utilities.ods");
        try {
            int rows = 9 * list.size();
            int columns = 7;
            Sheet sheet = new Sheet("A", rows, columns);
            sheet.setColumnWidth(0, 45.0);
            sheet.setColumnWidths(1, 6, 28.0);

            for (int i = 1; i < list.size(); i++) {
                int firstRow = (i - 1) * 9;

                MonthData newMonth = list.get(i);
                MonthData previousMonth = list.get(i - 1);
                Tariff tariff = dataManager.getTariffByDate(newMonth.getDate());

                sheet.setRowHeight(firstRow + 7, 7.5);
                sheet.getRange(firstRow + 1, 5, 7, 1).setStyle(Styles.getPriceStyle());
                sheet.getRange(firstRow + 7, 5).setStyle(Styles.getTotalPriceStyle());
                sheet.getRange(firstRow + 1, 0, 6, 5).setStyle(Styles.getCommonStyle());
                sheet.getRange(firstRow + 1, 6).setStyle(Styles.getDateStyle());
                sheet.getRange(firstRow + 7, 0, 1, 5).merge();
                sheet.getRange(firstRow, 0, 1, 7).setStyle(Styles.getFirstRowStyle());

                sheet.getRange(firstRow,
                        0, 7).setValues(
                        "",
                        "электричество",
                        "гор вода 848497 (санузел)",
                        "хол вода 848198 (санузел)",
                        "гор  вода 848565 (кухня)",
                        "хол вода 858292 (кухня)",
                        "водоотведение");

                sheet.getRange(firstRow,
                        1, 7).setValues(
                        "новые п-я",
                        newMonth.getElectricity(),
                        newMonth.getHotWaterBath(),
                        newMonth.getColdWaterBath(),
                        newMonth.getHotWaterKitchen(),
                        newMonth.getColdWaterKitchen(),
                        "");

                sheet.getRange(firstRow,
                        2, 7).setValues(
                        "предыдущие п-я",
                        previousMonth.getElectricity(),
                        previousMonth.getHotWaterBath(),
                        previousMonth.getColdWaterBath(),
                        previousMonth.getHotWaterKitchen(),
                        previousMonth.getColdWaterKitchen(),
                        "");

                //В формулах индексация начинается с единицы!
                sheet.getRange(firstRow,
                        3, 7).setFormulas(
                        "расход",
                        "=B" + (firstRow + 2) + " - C" + (firstRow + 2),
                        "=B" + (firstRow + 3) + " - C" + (firstRow + 3),
                        "=B" + (firstRow + 4) + " - C" + (firstRow + 4),
                        "=B" + (firstRow + 5) + " - C" + (firstRow + 5),
                        "=B" + (firstRow + 6) + " - C" + (firstRow + 6),
                        "=SUM(D" + (firstRow + 3) + ":D" + (firstRow + 6)
                );

                sheet.getRange(firstRow,
                        4, 7).setValues(
                        "тариф",
                        tariff.getElectricity(),
                        tariff.getHotWater(),
                        tariff.getColdWater(),
                        tariff.getHotWater(),
                        tariff.getColdWater(),
                        tariff.getDrainage());

                sheet.getRange(firstRow,
                        5, 8).setFormulas(
                        "к оплате",
                        "= D" + (firstRow + 2) + " * E" + (firstRow + 2),
                        "= D" + (firstRow + 3) + " * E" + (firstRow + 3),
                        "= D" + (firstRow + 4) + " * E" + (firstRow + 4),
                        "= D" + (firstRow + 5) + " * E" + (firstRow + 5),
                        "= D" + (firstRow + 6) + " * E" + (firstRow + 6),
                        "= D" + (firstRow + 7) + " * E" + (firstRow + 7),
                        "= SUM(F" + (firstRow + 2) + ":F" + (firstRow + 7) + ")"
                );

                sheet.getRange(firstRow,
                        6, 2).setValues(
                        "дата показаний",
                        newMonth.getDate()
                );
            }

            SpreadSheet spread = new SpreadSheet();
            spread.appendSheet(sheet);
            spread.save(result);
        } catch (Exception e) {
            System.out.println("Error while creating .ods!\n" + e);
        }

        System.out.println(result);
        return result;
    }
}
