package clock;

import java.util.Calendar;
import javax.swing.JFrame;

/**
 * Implementation of the PriorityQueue ADT using a sorted array for storage.
 *
 * Because Java does not allow generic arrays (!), this is implemented as an
 * array of Object rather than of PriorityItem&lt;T&gt;, which would be natural.
 * Array elements accessed then have to be cast to PriorityItem&lt;T&gt; before
 * using their getItem() or getPriority() methods.
 *
 * This is an example of Java's poor implementation getting in the way. Java
 * fanboys will no doubt explain at length why it has to be this way, but note
 * that Eiffel allows it because Eiffel generics were done right from the start,
 * rather than being tacked on as an afterthought and limited by issues of
 * backward compatibility. Humph!
 *
 * @param <T> The type of things being stored.
 */
public class SortedArrayPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    int day = 0;
    int month = 0;
    int year = 0;

    int hour = 0;
    int minute = 0;
    int second = 0;

    public SortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;

    }

    @Override
    public String head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) storage[0]).toString();
        }
    }

    @Override
    public void add(int second, int minute, int hour, int day, int month, int year) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            
            /* Gate code is used to carry on if the alarm has not passed yet*/
            int gateCode = 0;

            /* This part of the code checks if the detials the users have already passed or not
            it goes checks if a part is great, like if the year is greater there is no need to check
            everything else*/
            Calendar date = Calendar.getInstance();
            if (year > date.get(Calendar.YEAR)) {
                gateCode = 1;
            } else if (year == date.get(Calendar.YEAR)) {

                if (month == (date.get(Calendar.MONTH) + 1)) {

                    if (day > date.get(Calendar.DAY_OF_MONTH)) {
                        gateCode = 1;

                    } else if (day == date.get(Calendar.DAY_OF_MONTH)) {

                        if (hour > date.get(Calendar.HOUR_OF_DAY)) {
                            gateCode = 1;

                        } else if (hour == date.get(Calendar.HOUR_OF_DAY)) {

                            if (minute > date.get(Calendar.MINUTE)) {
                                gateCode = 1;

                            } else if (minute == date.get(Calendar.MINUTE)) {

                                if (second > date.get(Calendar.SECOND)) {
                                    gateCode = 1;

                                } else if (second == date.get(Calendar.SECOND)) {

                                }
                            }
                        }
                    }
                } else if (month > (date.get(Calendar.MONTH) + 1)) {
                    gateCode = 1;
                }
            }
            
            final JFrame frame = new JFrame();
            if (gateCode == 1) {
                if (tailIndex == 0) {
                    int priority = 0;
                    storage[i] = new PriorityItem<>(second, minute, hour, day, month, year, priority);
                    AlarmAdded dialog = new AlarmAdded(frame, true);
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);

                } else {
                    
                    /* Uses priority backup as a placeholder and break to get out of the loop*/
                    int priorityBackup = 0;
                    int BREAK = 0;
                    i = 0;

                    while (BREAK != 1) {
                        if (year > ((PriorityItem<T>) storage[i]).getYear()) {
                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                            priorityBackup++;
                            
                        } else if (year == ((PriorityItem<T>) storage[i]).getYear()) {
                            
                            if (month == ((PriorityItem<T>) storage[i]).getMonth()) {
                                
                                if (day > ((PriorityItem<T>) storage[i]).getDay()) {
                                    priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                    priorityBackup++;
                                    
                                } else if (day == ((PriorityItem<T>) storage[i]).getDay()) {
                                    
                                    if (hour > ((PriorityItem<T>) storage[i]).getHour()) {
                                        priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                        priorityBackup++;
                                        
                                    } else if (hour == ((PriorityItem<T>) storage[i]).getHour()) {
                                        
                                        if (minute > ((PriorityItem<T>) storage[i]).getMinute()) {
                                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                            priorityBackup++;
                                            
                                        } else if (minute == ((PriorityItem<T>) storage[i]).getMinute()) {
                                            
                                            if (second > ((PriorityItem<T>) storage[i]).getSecond()) {
                                                priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                                priorityBackup++;
                                            } else {
                                                priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                                priorityBackup--;
                                                BREAK = 1;
                                            }
                                        } else {
                                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                            priorityBackup--;
                                            BREAK = 1;
                                        }
                                    } else {
                                        priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                        priorityBackup--;
                                        BREAK = 1;
                                    }
                                } else {
                                    priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                    priorityBackup--;
                                    BREAK = 1;
                                }
                                
                            } else if (month > ((PriorityItem<T>) storage[i]).getMonth()) {
                                priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                priorityBackup++;
                            } else {
                                priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                priorityBackup--;
                                BREAK = 1;
                            }
                        } else {
                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                            priorityBackup--;
                            BREAK = 1;
                        }
                        
                        /* Adds one to i this is to break the loop if searches through all items*/
                        i++;
                        
                        if (i == tailIndex) {
                            BREAK = 1;
                        }
                    }

                    i = tailIndex;
                    while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() > priorityBackup) {
                        storage[i] = storage[i - 1];
                        i = i - 1;
                    }
                    storage[i] = new PriorityItem<>(second, minute, hour, day, month, year, priorityBackup);
                    AlarmAdded dialog = new AlarmAdded(frame, true);
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                }
            }
            else
            {
                tailIndex--;
                
                AlarmNotAdded dialog = new AlarmNotAdded(frame, true);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            /*Used this for testing if alarm was removed */
            System.out.println("Alarm" + storage[0].toString() + " has been removed");
            for (int i = 0; i < tailIndex; i++) {
                storage[i] = storage[i + 1];
            }
            tailIndex = tailIndex - 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + "\n";
            }
            result = result + storage[i].toString();
        }
        result = result + "";
        return result;
    }
    
    @Override
    public String singleString(int i){
        return storage[i].toString();
    }
    
    @Override
    public int length(){
        return tailIndex;
    }
    
    @Override
    public void deleteSelected(int i){
    
        int i2 = i;
        int tailIndex2 = tailIndex;

        for (int count = i2; count < tailIndex2 ; count++) 
        {
            storage[count] = storage[count + 1];
            tailIndex--;
        }
        if(i2 == tailIndex2){
           storage[i2] = storage[i2 + 1];
           tailIndex--;
        }
    }
    
    public String saveCal(){
        String AlarmString = "";
        
        int tailIndex2 = tailIndex;
        for (int i = 0; i <= tailIndex2; i++) {
            
                AlarmString = AlarmString + "ALARM:";
                
                AlarmString = AlarmString + ((PriorityItem<T>) storage[i]).getYear();
                
                if(((PriorityItem<T>) storage[i]).getMonth() < 10){
                AlarmString = AlarmString + "0" + ((PriorityItem<T>) storage[i]).getMonth();
                }
                else
                {
                AlarmString = AlarmString + ((PriorityItem<T>) storage[i]).getMonth();
                }
                
                if(((PriorityItem<T>) storage[i]).getDay() < 10){
                AlarmString = AlarmString + "0" + ((PriorityItem<T>) storage[i]).getDay();
                }
                else
                {
                AlarmString = AlarmString + ((PriorityItem<T>) storage[i]).getDay();
                }
                
                AlarmString = AlarmString + "T";
                
                if(((PriorityItem<T>) storage[i]).getHour() < 10){
                AlarmString = AlarmString + "0" + ((PriorityItem<T>) storage[i]).getHour();
                }
                else
                {
                AlarmString = AlarmString + ((PriorityItem<T>) storage[i]).getHour();
                }
                
                if(((PriorityItem<T>) storage[i]).getMinute() < 10){
                AlarmString = AlarmString + "0" + ((PriorityItem<T>) storage[i]).getMinute();
                }
                else
                {
                AlarmString = AlarmString + ((PriorityItem<T>) storage[i]).getMinute();
                }
                
                if(((PriorityItem<T>) storage[i]).getSecond() < 10){
                AlarmString = AlarmString + "0" + ((PriorityItem<T>) storage[i]).getSecond();
                }
                else
                {
                AlarmString = AlarmString + ((PriorityItem<T>) storage[i]).getSecond();
                }
                
                AlarmString = AlarmString + "Z" + "\n";
            }
            tailIndex2--;
        
        return AlarmString;
    }

    /* These are used to get the each variable by its own*/
    public int getSec() {
        return ((PriorityItem<T>) storage[0]).getSecond();
    }

    public int getMin() {
        return ((PriorityItem<T>) storage[0]).getMinute();
    }

    public int getHou() {
        return ((PriorityItem<T>) storage[0]).getHour();
    }

    public int getDay2() {
        return ((PriorityItem<T>) storage[0]).getDay();
    }

    public int getMon() {
        return ((PriorityItem<T>) storage[0]).getMonth();
    }

    public int getYea() {
        return ((PriorityItem<T>) storage[0]).getYear();
    }
}
