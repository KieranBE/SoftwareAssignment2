package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements Observer {
    
    ClockPanel panel;
    PriorityQueue<Alarm> q2; 
    int count = 0;
    Model model2;
    /**
     *
     * @param model
     * @param q
     */
    public View(Model model, PriorityQueue<Alarm> q) {
        model2 = model;
        final JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        q2 = q;
        
        // Start of border layout code
        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        Container pane = frame.getContentPane();
        
        JButton addAlarmButton = new JButton("Add Alarm");
        pane.add(addAlarmButton, BorderLayout.PAGE_START);
        
        addAlarmButton.addActionListener(new ActionListener(){
                       
            public void actionPerformed(ActionEvent evt){
                AddAlarm dialog = new AddAlarm(frame, true);
                dialog.PassQueue(q2);
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
                q2 = dialog.getQueue();

                /*System.out.println("Time:" + hour + ":" + minute + ":" + second 
                + " Date: " + day + "/" + month + "/" + year);
                System.out.println("Going to Add Alarm");*/
                System.out.println(q2.toString());
                AlarmUpdate();
                
            }
            });
        
        
        
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        JButton editButton = new JButton("Edit Alarms");
        pane.add(editButton, BorderLayout.LINE_START);
         
        editButton.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            int delete = 0;
            
            while(delete != 1){
            EditAlarm dialog = new EditAlarm(frame, true);
            dialog.PassQueue(q2);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
            delete = dialog.getDelete();
            q2 = dialog.getQueue();
            System.out.println("Going to Edit Alarms");
            }
        }
        });
        
        JButton saveButton = new JButton("Save or Load Alarms");
        pane.add(saveButton, BorderLayout.PAGE_END);
         
        saveButton.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            SaveLoad dialog = new SaveLoad(frame, true);
            dialog.PassQueue(q2);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
            q2 = dialog.GetQueue();
            System.out.println("Going to Save/Load Alarms");
        }
        });
        
        JButton viewButton = new JButton("View Alarms");
        pane.add(viewButton, BorderLayout.LINE_END);
        
        viewButton.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            ViewAlarm dialog = new ViewAlarm(frame, true);
            dialog.PassQueue(q2);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
            System.out.println("Going to View Alarms");
        }
        });
        
        // End of borderlayout code
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
    public void AlarmUpdate(){
    model2.passQueue(q2);
    }
}
