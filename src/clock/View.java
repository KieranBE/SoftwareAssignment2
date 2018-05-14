package clock;

import java.awt.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements Observer {
    
    ClockPanel panel;
        
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
                JFrame frame2 = new JFrame();
                AddAlarm dialog = new AddAlarm(frame2, true);
                dialog.setLocationRelativeTo(frame2);
                dialog.setVisible(true);
                System.out.println("Going to Add Alarm");
            }
            });
        
        panel.setPreferredSize(new Dimension(200, 200));
        pane.add(panel, BorderLayout.CENTER);
         
        JButton editButton = new JButton("Edit Alarms");
        pane.add(editButton, BorderLayout.LINE_START);
         
        editButton.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            JFrame frame3 = new JFrame();
            EditAlarm dialog = new EditAlarm(frame3, true);
            dialog.setLocationRelativeTo(frame3);
            dialog.setVisible(true);
            System.out.println("Going to Edit Alarms");
        }
        });
        
        JButton saveButton = new JButton("Save Alarms and Quit");
        pane.add(saveButton, BorderLayout.PAGE_END);
         
        saveButton.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            System.out.println("Going to Save Alarms");
            System.exit(0);
        }
        });
        
        JButton viewButton = new JButton("View Alarms");
        pane.add(viewButton, BorderLayout.LINE_END);
        
        viewButton.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent evt){
            JFrame frame4 = new JFrame();
            ViewAlarm dialog = new ViewAlarm(frame4, true);
            dialog.setLocationRelativeTo(frame4);
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
}
