package clock;

/**
 * A wrapper for bundling up an item and its integer priority.
 * 
 * @param <T>
 */
public class PriorityItem<T> {

    private final int second;
    private final int minute;
    private final int hour;
    private final int day;
    private final int month;
    private final int year;
    private final int priority;

    public PriorityItem(int second, int minute,int hour, int day, int month, int year, int priority) {
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
        this.priority = priority;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }
    
    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }
    
    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    
    public int getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
        return "(" + getSecond() + ":" + getMinute() + ":" + getHour() + " " 
                    + getDay() + "/" + getMonth() + "/" + getYear() +")";
    }
    
}
