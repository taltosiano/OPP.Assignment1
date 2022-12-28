package observer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class ConcreteMember implements Member{

    private String name;
    private UndoableStringBuilder usb_status;

    /**
     * Constructor that receive name of member and create object type  ConcreteMember
     * @param name - the member's name.
     */
    public ConcreteMember (String name){
        this.usb_status = new UndoableStringBuilder();
        this.name = name;
    }

    /**
     * An empty constructor
     */
    public ConcreteMember (){
        this.usb_status = null;
        this.name = "anonymous";
    }

        /**
         * Getter method to get the name of member.
         * @return the ConcreteMember's name
         */

    public String getName() {

        return name;
    }

    /**
     * Getter method to get the usb state.
     * @return the state right now of the this.usb
     */
    public UndoableStringBuilder getUsb_status() {
        return usb_status;
    }

    /**
     * The method actually send the notification by Shallow copy.
     * @param usb - string from class UndoableStringBuilder the function recieve and change
     *           this.usb string of this class to be the string the function recieve.
     */
    public void update(UndoableStringBuilder usb){

        this.usb_status = usb;

        System.out.println("Sending notification to " + name + " concerning " + usb_status);
    }

    @Override
    public String toString() {
        return
                "Member's Name:" + this.name + '\'' +
                ",  right now the string is: " + this.usb_status ;
    }

}

