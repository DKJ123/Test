package Excel;

public class CurrentFileValues {
    String Restaurant;
    int PrevWeek;
    int CurWeek;
    int Change;
    double Percent;

    public CurrentFileValues(String restaurant, int prevWeek, int curWeek, int change, double percent) {
        this.Restaurant = restaurant;
        this.PrevWeek = prevWeek;
        this.CurWeek = curWeek;
        this.Change = change;
        this.Percent = percent;
    }

    public String getRestaurant() {
        return Restaurant;
    }

    public void setRestaurant(String restaurant) {
        Restaurant = restaurant;
    }

    public int getPrevWeek() {
        return PrevWeek;
    }

    public void setPrevWeek(int prevWeek) {
        PrevWeek = prevWeek;
    }

    public int getCurWeek() {
        return CurWeek;
    }

    public void setCurWeek(int curWeek) {
        CurWeek = curWeek;
    }

    public int getChange() {
        return Change;
    }

    public void setChange(int change) {
        Change = change;
    }

    public double getPercent() {
        return Percent;
    }

    public void setPercent(double percent) {
        Percent = percent;
    }
}
