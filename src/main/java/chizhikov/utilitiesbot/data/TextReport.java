package chizhikov.utilitiesbot.data;

import chizhikov.utilitiesbot.data.db.entities.MonthData;
import chizhikov.utilitiesbot.data.db.entities.Tariff;

import java.time.LocalDate;

public class TextReport {
    private final MonthData actualMonth;
    private final MonthData previousMonth;


    private final int coldWaterBath;
    private final int hotWaterBath;
    private final int coldWaterKitchen;
    private final int hotWaterKitchen;
    private final int electricity;
    private final int drainage;

    private final int coldWater;
    private final int hotWater;

    private final LocalDate actualDate;
    private final LocalDate previousDate;

    private final Tariff tariff;

    private final float coldWaterBathPrice;
    private final float hotWaterBathPrice;
    private final float coldWaterKitchenPrice;
    private final float hotWaterKitchenPrice;
    private final float electricityPrice;
    private final float drainagePrice;

    private final float coldWaterPrice;
    private final float hotWaterPrice;

    private final float overallPrice;


    public TextReport(MonthData previousMonth, MonthData actualMonth, Tariff tariff) {
        this.previousMonth = previousMonth;
        this.actualMonth = actualMonth;

        coldWaterBath = actualMonth.getColdWaterBath() - previousMonth.getColdWaterBath();
        hotWaterBath = actualMonth.getHotWaterBath() - previousMonth.getHotWaterBath();
        coldWaterKitchen = actualMonth.getColdWaterKitchen() - previousMonth.getColdWaterKitchen();
        hotWaterKitchen = actualMonth.getHotWaterKitchen() - previousMonth.getHotWaterKitchen();
        electricity = actualMonth.getElectricity() - previousMonth.getElectricity();

        this.tariff = tariff;

        coldWater = coldWaterBath + coldWaterKitchen;
        hotWater = hotWaterBath + hotWaterKitchen;

        drainage = coldWater + hotWater;

        coldWaterKitchenPrice = coldWaterKitchen * tariff.getColdWater();
        hotWaterKitchenPrice = hotWaterKitchen * tariff.getHotWater();
        coldWaterBathPrice = coldWaterBath * tariff.getColdWater();
        hotWaterBathPrice = hotWaterBath * tariff.getHotWater();
        electricityPrice = electricity * tariff.getElectricity();
        drainagePrice = drainage * tariff.getDrainage();

        hotWaterPrice = hotWater * tariff.getHotWater();
        coldWaterPrice = coldWater * tariff.getColdWater();

        overallPrice = coldWaterPrice + hotWaterPrice + drainagePrice + electricityPrice;

        actualDate = actualMonth.getDate();
        previousDate = previousMonth.getDate();
    }

    public int getColdWaterBath() {
        return coldWaterBath;
    }

    public int getHotWaterBath() {
        return hotWaterBath;
    }

    public int getColdWaterKitchen() {
        return coldWaterKitchen;
    }

    public int getHotWaterKitchen() {
        return hotWaterKitchen;
    }

    public int getElectricity() {
        return electricity;
    }

    public int getDrainage() {
        return drainage;
    }

    public int getColdWater() {
        return coldWater;
    }

    public int getHotWater() {
        return hotWater;
    }

    public LocalDate getActualDate() {
        return actualDate;
    }

    public LocalDate getPreviousDate() {
        return previousDate;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public float getColdWaterBathPrice() {
        return coldWaterBathPrice;
    }

    public float getHotWaterBathPrice() {
        return hotWaterBathPrice;
    }

    public float getColdWaterKitchenPrice() {
        return coldWaterKitchenPrice;
    }

    public float getHotWaterKitchenPrice() {
        return hotWaterKitchenPrice;
    }

    public float getElectricityPrice() {
        return electricityPrice;
    }

    public float getDrainagePrice() {
        return drainagePrice;
    }

    @Override
    public String toString() {
        return "Короткий отчёт от " + actualDate + ":\n" +
                "Электричество: " + electricityPrice + "р. (расход: " + electricity + ");\n" +
                "Горячая вода: " + hotWaterPrice + "р. (расход: " + hotWater + ");\n" +
                "Холодная вода: " + coldWaterPrice + "р. (расход: " + coldWater + ");\n" +
                "Водоотведение: " + drainagePrice + ";\n" +
                "ИТОГО: " + overallPrice + "р.\n";
    }
}
