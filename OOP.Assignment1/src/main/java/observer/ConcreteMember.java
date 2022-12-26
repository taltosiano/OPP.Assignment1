package observer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class ConcreteMember implements Member{

    private GroupAdmin sender;
    private String name;
    private UndoableStringBuilder usb_status;

    public ConcreteMember (String name){
        this.usb_status = usb_status;
        this.name = name;
        this.sender = sender;
    }

    public String getName() {

        return name;
    }

    /**
     *
     * @param usb - string from class UndoableStringBuilder the function recieve and change
     *           this.usb string of this class to be the string the function recieve.
     */
    public void update(UndoableStringBuilder usb){
        // actually send the email
        this.usb_status = usb;

//        UndoableStringBuilder temp = new UndoableStringBuilder();
//        temp.append(usb.toString());
//        this.usb_status = temp;

        // System.out.println("Sending notification to " + memName + " concerning " + ust);
    }

    @Override
    public String toString() {
        return "ConcreteMember{" +
                "Name='" + name + '\'' +
                ", message=" + this.usb_status +
                '}';
    }



    public static void main(String[] args) {

        GroupAdmin GA = new GroupAdmin();
        ConcreteMember CM = new ConcreteMember("aa");
        ConcreteMember CM2 = new ConcreteMember("bb");
        ConcreteMember CM3 = new ConcreteMember("CC");

        GA.register(CM);
        GA.register(CM2);
        GA.register(CM);
        GA.register(CM3);
        GA.append("TEST");
        GA.delete(1,2);
        GA.unregister(CM3);
        System.out.println(GA.getRegisters());
        GA.unregister(CM3);
        GA.append("QQQ");
        System.out.println(CM+","+CM2+","+CM3);
        System.out.println(CM3);



    }
}
