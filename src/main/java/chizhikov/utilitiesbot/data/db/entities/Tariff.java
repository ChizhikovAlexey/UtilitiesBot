package chizhikov.utilitiesbot.data.db.entities;

import java.time.LocalDate;

/**
 * Entity that represents tariff that has been actual since "updateDate"
 */
public class Tariff {
    private float hotWater;
    private float coldWater;
    private float drainage;
    private float electricity;
    private LocalDate updateDate;

    public Tariff() {}

    public Tariff(float electricity, float hotWater, float coldWater, float drainage,  LocalDate updateDate) {
        this.hotWater = hotWater;
        this.coldWater = coldWater;
        this.drainage = drainage;
        this.electricity = electricity;
        this.updateDate = updateDate;
    }

    public float getHotWater() {
        return hotWater;
    }

    public void setHotWater(float hotWater) {
        this.hotWater = hotWater;
    }

    public float getColdWater() {
        return coldWater;
    }

    public void setColdWater(float coldWater) {
        this.coldWater = coldWater;
    }

    public float getDrainage() {
        return drainage;
    }

    public void setDrainage(float drainage) {
        this.drainage = drainage;
    }

    public float getElectricity() {
        return electricity;
    }

    public void setElectricity(float electricity) {
        this.electricity = electricity;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "hotWater=" + hotWater +
                ", coldWater=" + coldWater +
                ", drainage=" + drainage +
                ", electricity=" + electricity +
                ", updateDate=" + updateDate +
                '}';
    }
}
