package data.DataBase.Entities;

import java.util.Date;

/**
 * Entity that represents meters data for a month
 */
public class MonthData {
    private int electricity;
    private int hotWaterBath;
    private int coldWaterBath;
    private int hotWaterKitchen;
    private int coldWaterKitchen;

    public MonthData() {
    }

    private Date date;

    public MonthData(int electricity, int hotWaterBath, int coldWaterBath, int hotWaterKitchen, int coldWaterKitchen, Date date) {
        this.coldWaterBath = coldWaterBath;
        this.hotWaterBath = hotWaterBath;
        this.coldWaterKitchen = coldWaterKitchen;
        this.hotWaterKitchen = hotWaterKitchen;
        this.electricity = electricity;
        this.date = date;
    }

    public int getColdWaterBath() {
        return coldWaterBath;
    }

    public void setColdWaterBath(int coldWaterBath) {
        this.coldWaterBath = coldWaterBath;
    }

    public int getHotWaterBath() {
        return hotWaterBath;
    }

    public void setHotWaterBath(int hotWaterBath) {
        this.hotWaterBath = hotWaterBath;
    }

    public int getColdWaterKitchen() {
        return coldWaterKitchen;
    }

    public void setColdWaterKitchen(int coldWaterKitchen) {
        this.coldWaterKitchen = coldWaterKitchen;
    }

    public int getHotWaterKitchen() {
        return hotWaterKitchen;
    }

    public void setHotWaterKitchen(int hotWaterKitchen) {
        this.hotWaterKitchen = hotWaterKitchen;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MonthData{" +
                "coldWaterBath=" + coldWaterBath +
                ", hotWaterBath=" + hotWaterBath +
                ", coldWaterKitchen=" + coldWaterKitchen +
                ", hotWaterKitchen=" + hotWaterKitchen +
                ", electricity=" + electricity +
                ", date=" + date +
                '}';
    }
}
