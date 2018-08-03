package clock;

import java.util.Calendar;
import java.util.Observable;
import javax.swing.JFrame;
//import java.util.GregorianCalendar;


public class Model extends Observable {

    int day = 0;
    int month = 0;
    int year = 0;

    int hour = 0;
    int minute = 0;
    int second = 0;

    int oldSecond = 0;
    
    int count = 0;

    PriorityQueue<Alarm> q2;
    
    public Model() {
        update();
    }

    public void passQueue(PriorityQueue<Alarm> q){
    q2 = q;
    count = 1;
    }
    
    public void update() {
        /* I used the commented out code to test my alarm */
        

        Calendar date = Calendar.getInstance();

        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        month = month + 1;
        year = date.get(Calendar.YEAR);

        hour = date.get(Calendar.HOUR_OF_DAY);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);

        if(count == 1){
        if (q2.isEmpty()) {
        } else {
            if (q2.getYea() == year) {
                if (q2.getMon() == month) {
                    if (q2.getDay2() == day) {
                        if (q2.getHou() == hour) {
                            if (q2.getMin() == minute) {
                                System.out.println("Alarm goes off");
                                JFrame frame = new JFrame();
                                AlarmOff dialog = new AlarmOff(frame, true);
                                dialog.setLocationRelativeTo(frame);
                                dialog.setVisible(true);
                                count = 0;
                                try {
                                    q2.remove();
                                } catch (QueueUnderflowException e) {
                                    System.out.println("Can't remove head of queue: " + e);
                                }
                                /* I got an error when i tried to compare the seconds, 
                                i tried to fix it but i did not have enough time*/
//                                if(q.getSec() == second)
//                                {
//                                }
                            }
                        }
                    }
                }
            }
        }
        }

        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
    }
}
