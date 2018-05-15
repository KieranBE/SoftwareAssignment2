package clock;
import java.util.Calendar;

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
        System.out.println("Tail Index " + tailIndex);
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            int gateCode = 0;
            
            Calendar date = Calendar.getInstance();
            System.out.println(second + " " + minute + " " + hour + " " + day + " " + month+ " " + year);
            System.out.println("Second: "+ date.get(Calendar.SECOND) + " Min: " + date.get(Calendar.MINUTE) + " Hour: " + date.get(Calendar.HOUR) + "Day " + date.get(Calendar.DAY_OF_MONTH) + "Month " + (date.get(Calendar.MONTH)+1) + "Year: " + date.get(Calendar.YEAR));
            if(year > date.get(Calendar.YEAR))
            {
                gateCode = 1;
                System.out.println("Year big");
            }
            else if(year == date.get(Calendar.YEAR))
            {
                System.out.println("Year Equal");
                System.out.println(month);
                System.out.println((date.get(Calendar.MONTH)+1));
                if(month == (date.get(Calendar.MONTH)+1))
                {
                System.out.println("Month Equal");
                    if(day > date.get(Calendar.DAY_OF_MONTH))
                    {gateCode = 1;
                    System.out.println("Date big");
                    }
                    else if(day == date.get(Calendar.DAY_OF_MONTH))
                    {
                        System.out.println("Date Equal");
                        if(hour > date.get(Calendar.HOUR))
                        {gateCode = 1;
                        System.out.println("Hour Big");
                        }
                        else if(hour == date.get(Calendar.HOUR))
                        {
                            System.out.println("Hour Equal");

                            if(minute > date.get(Calendar.MINUTE))
                            {gateCode = 1;
                            System.out.println("Minute Big");
                            }
                            else if(minute == date.get(Calendar.MINUTE))
                            {
                                System.out.println("Minute Equal");
                                if(second > date.get(Calendar.SECOND))
                                {gateCode = 1;
                                System.out.println("Second Big");
                                }
                                else if(second == date.get(Calendar.SECOND))
                                {
                                System.out.println("Second Equal");
                                }
                            }
                        }
                    }
                }
                else if(month > (date.get(Calendar.MONTH)+1))
                {gateCode = 1;
                System.out.println("Month big");
                }
            }
            
            System.out.println(gateCode);

            if(gateCode == 1)
            {
                if(tailIndex == 0)
                {
                    System.out.println(i);
                    int priority = 0;
                    storage[i] = new PriorityItem<>(second, minute, hour, day, month, year, priority);
                    System.out.println(((PriorityItem<T>) storage[i]).getSecond()+"");
                }
                else
                {
                    int priorityBackup = 0;
                    int BREAK = 0;
                    i = 0;

                    while (BREAK != 1)
                    {
                    if(year > ((PriorityItem<T>) storage[i]).getYear())
                    {
                        priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                        priorityBackup++;
                    }
                    else if(year == ((PriorityItem<T>) storage[i]).getYear())
                    {
                        if(month == ((PriorityItem<T>) storage[i]).getMonth())
                        {
                            if(day > ((PriorityItem<T>) storage[i]).getDay())
                            {
                                priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                priorityBackup++;
                            }
                            else if(day == ((PriorityItem<T>) storage[i]).getDay())
                            {
                                if(hour > ((PriorityItem<T>) storage[i]).getHour())
                                {
                                    priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                    priorityBackup++;
                                }
                                else if(hour == ((PriorityItem<T>) storage[i]).getHour())
                                {
                                    if(minute > ((PriorityItem<T>) storage[i]).getMinute())
                                    {
                                        priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                        priorityBackup++;
                                    }
                                    else if(minute == ((PriorityItem<T>) storage[i]).getMinute())
                                    {
                                        if(second > ((PriorityItem<T>) storage[i]).getSecond())
                                        {
                                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                            priorityBackup++;
                                        }
                                        else
                                        {
                                            //priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                            priorityBackup--;
                                            BREAK = 1;
                                        }
                                    }
                                    else
                                    {
                                        priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                        priorityBackup--;
                                        BREAK = 1;
                                    }
                                }
                                else
                                {
                                    priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                    priorityBackup--;
                                    BREAK = 1;
                                }
                            }
                            else
                            {
                                priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                                priorityBackup--;
                                BREAK = 1;
                            }
                        }
                        else if(month > ((PriorityItem<T>) storage[i]).getMonth())
                        {
                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                            priorityBackup++;
                        }
                        else
                        {
                            priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                            priorityBackup--;
                            BREAK = 1;
                        }
                    }
                    else
                    {
                        priorityBackup = ((PriorityItem<T>) storage[i]).getPriority();
                        priorityBackup--;
                        BREAK = 1;
                    }
                    i++;
                    if(i == tailIndex)
                    {
                    BREAK = 1;
                    }
                }
                    
                i = tailIndex;
                    while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getPriority() > priorityBackup) {
                        storage[i] = storage[i - 1];
                        i = i - 1;
                    }
                    storage[i] = new PriorityItem<>(second, minute, hour, day, month, year, priorityBackup);
                    System.out.println(((PriorityItem<T>) storage[i]).getPriority()+"");

                }
            }
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            System.out.println("Alarm" + storage[0].toString()+ " has been removed");
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
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i].toString();
        }
        result = result + "]";
        return result;
    }
    
    public int getSec() {
            return ((PriorityItem<T>) storage[0]).getSecond();
        }
    
    public int getMin(){
            return ((PriorityItem<T>) storage[0]).getMinute();
        }
    
    public int getHou(){
            return ((PriorityItem<T>) storage[0]).getHour();
        }
    
    public int getDay2(){
            return ((PriorityItem<T>) storage[0]).getDay();
        }
    
    public int getMon(){
            return ((PriorityItem<T>) storage[0]).getMonth();
        }
    
    public int getYea(){
            return ((PriorityItem<T>) storage[0]).getYear();
        }
}
