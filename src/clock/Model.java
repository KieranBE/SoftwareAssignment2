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

    public Model() {
        update();
    }

    public void update() {
        /* I used the commented out code to test my alarm */
        PriorityQueue<Alarm> q;
        q = new SortedArrayPriorityQueue<>(8);
//        int sec2 = 30;
//        int min2 = 1;
//        int hou2 = 10;
//
//        int day2 = 15;
//        int mon2 = 5;
//        int yea2 = 2018;
//
//        try {
//            q.add(sec2, min2, hou2, day2, mon2, yea2);
//        } catch (QueueOverflowException e) {
//            System.out.println("Add operation failed: " + e);
//        }

        Calendar date = Calendar.getInstance();

        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
        month = month + 1;
        year = date.get(Calendar.YEAR);

        hour = date.get(Calendar.HOUR);
        minute = date.get(Calendar.MINUTE);
        oldSecond = second;
        second = date.get(Calendar.SECOND);
        if (q.isEmpty()) {
        } else {
            if (q.getYea() == year) {
                if (q.getMon() == month) {
                    if (q.getDay2() == day) {
                        if (q.getHou() == hour) {
                            if (q.getMin() == minute) {
                                System.out.println("Alarm goes off");
                                JFrame frame = new JFrame();
                                AlarmOff dialog = new AlarmOff(frame, true);
                                dialog.setLocationRelativeTo(frame);
                                dialog.setVisible(true);
                                try {
                                    q.remove();
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

        if (oldSecond != second) {
            setChanged();
            notifyObservers();
        }
    }
}
