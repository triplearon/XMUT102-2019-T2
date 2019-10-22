import java.io.Serializable;
import java.util.Calendar;



public class User implements Serializable {
    private String name;
    private String sex;
    private String type;
    private Calendar startDay;
    private Calendar endDay;
    public User(int y,int m,int d,int howLong){
        startDay=Calendar.getInstance();
        startDay.set(y, m, d);
        endDay=Calendar.getInstance();
        endDay.set(y,m,d);
        endDay.add(endDay.DATE,howLong);
    }
    public String getType() {
        return type;
    }
    public Calendar getEndDay() {
        return endDay;
    }
    public Calendar getStartDay() {
        return startDay;
    }
    public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }
    public void setEndDay(Calendar endDay) {
        this.endDay = endDay;
    }
    public void setName(String name){

            this.name=name;

    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setStartDay(Calendar startDay) {
        this.startDay = startDay;
    }
    public void setType(String type) {
        this.type = type;
    }
}
