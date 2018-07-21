package clock;

public class Clock {
        
    public static void main(String[] args) {
        PriorityQueue<Alarm> q;  
        q = new SortedArrayPriorityQueue<>(8);
        Model model = new Model();
        View view = new View(model, q);
        model.addObserver(view);
        Controller controller = new Controller(model, view, q);
        
    }
}
