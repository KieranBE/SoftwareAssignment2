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
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* Scan backwards looking for insertion point */
            int i = tailIndex;
            int gateCode = 0;
            
            Calendar date = Calendar.getInstance();
            
            if(year > date.get(Calendar.YEAR))
            {gateCode = 1;}
            else if(year == date.get(Calendar.YEAR))
            {
                if(month == (date.get(Calendar.MONTH)+1))
                {
                    if(day > date.get(Calendar.DAY_OF_MONTH))
                    {gateCode = 1;}
                    else if(day == date.get(Calendar.DAY_OF_MONTH))
                    {
                        if(hour > date.get(Calendar.HOUR))
                        {gateCode = 1;}
                        else if(hour == date.get(Calendar.HOUR))
                        {
                            if(minute > date.get(Calendar.MINUTE))
                            {gateCode = 1;}
                            else if(minute == date.get(Calendar.MINUTE))
                            {
                                if(second > date.get(Calendar.SECOND))
                                {gateCode = 1;}
                                else if(second == date.get(Calendar.SECOND))
                                {}
                            }
                        }
                    }
                }
                else if(month > date.get(Calendar.YEAR))
                {gateCode = 1;}
            }

            if(gateCode == 1)
            {
                while (i > 0 && ((PriorityItem<T>) storage[i - 1]).getYear() < year) {
                    storage[i] = storage[i - 1];
                    i = i - 1;
                }
                storage[i] = new PriorityItem<>(second, minute, hour, day, month, year);
            }
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
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
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
}
