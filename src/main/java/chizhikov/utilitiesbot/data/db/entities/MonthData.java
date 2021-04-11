package chizhikov.utilitiesbot.data.db.entities;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Entity that represents meters chizhikova.utilitiesbot.data for a month
 */
public class MonthData {
    private int electricity;
    private int hotWaterBath;
    private int coldWaterBath;
    private int hotWaterKitchen;
    private int coldWaterKitchen;
    private LocalDate date;

    public MonthData() {
    }

    public MonthData(int electricity, int hotWaterBath, int coldWaterBath, int hotWaterKitchen, int coldWaterKitchen, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public static MonthData parse(String string) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("\\s*(?<electricity>\\d+)\\s+" +
                "(?<hotwaterbath>\\d+)\\s+" +
                "(?<coldwaterbath>\\d+)\\s+" +
                "(?<hotwaterkitchen>\\d+)\\s+" +
                "(?<coldwaterkitchen>\\d+)\\s+" +
                "(?<date>\\d{4}-\\d{2}-\\d{2})\\s*");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return new MonthData(
                    Integer.parseInt(matcher.group("electricity")),
                    Integer.parseInt(matcher.group("hotwaterbath")),
                    Integer.parseInt(matcher.group("coldwaterbath")),
                    Integer.parseInt(matcher.group("hotwaterkitchen")),
                    Integer.parseInt(matcher.group("coldwaterkitchen")),
                    LocalDate.parse(matcher.group("date")));
        } else {
            throw new IllegalArgumentException("matcher.find() == false at MonthData.parse() for string " + string);
        }
    }
}
