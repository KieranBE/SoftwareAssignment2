/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

/**
 * Used to add the alarm to the queue, also lets the user enter their own time.
 *
 * @author Kieran
 */
public class Alarm {

    protected String name;

    public Alarm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
