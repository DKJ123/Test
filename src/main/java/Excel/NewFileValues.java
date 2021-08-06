package Excel;

public class NewFileValues {

    String RestaurantNew;
    int PrevWeekNew;
    int CurWeekNew;
    int ChangeNew;
    double PercentNew;

    public NewFileValues(String restaurantNew, int prevWeekNew, int curWeekNew, int changeNew, double percentNew) {
        this.RestaurantNew = restaurantNew;
        this.PrevWeekNew = prevWeekNew;
        this.CurWeekNew = curWeekNew;
        this.ChangeNew = changeNew;
        this.PercentNew = percentNew;
    }

    public String getRestaurantNew() {
        return RestaurantNew;
    }
    public void setRestaurantNew(String restaurantNew) {
        RestaurantNew = restaurantNew;
    }

    public int getPrevWeekNew() {
        return PrevWeekNew;
    }
    public void setPrevWeekNew(int prevWeekNew) {
        PrevWeekNew = prevWeekNew;
    }

    public int getCurWeekNew() {
        return CurWeekNew;
    }
    public void setCurWeekNew(int curWeekNew) {
        CurWeekNew = curWeekNew;
    }

    public int getChangeNew() {
        return ChangeNew;
    }
    public void setChangeNew(int changeNew) {
        ChangeNew = changeNew;
    }

    public double getPercentNew() {
        return PercentNew;
    }
    public void setPercentNew(double percentNew) {
        PercentNew = percentNew;
    }
}

