package observer;

import java.util.HashMap;
import java.util.Map;

public class GroupAdmin implements Sender {

    private Map<Member, String> registers;
    private UndoableStringBuilder usb_status;

    /**
     * An empty constructor.
     */
    public GroupAdmin() {
        this.registers = new HashMap<>();
        this.usb_status = new UndoableStringBuilder();
    }

    /**
     * The method returns the Members attribute that registered
     * @return Hash-map that contains all the register members
     */
    public Map<Member, String> getRegisters() {

        return registers;
    }

    public UndoableStringBuilder getUsb() {

        return this.usb_status;
    }

    /**
     * this function notify all the register members about every change.
     */
    public void notifyAllRegisters() {
         for (Member member: this.registers.keySet()){
             member.update(this.getUsb());
        }
    }

    /**
     * * The method add a specific member to the Group admin
     * @param obj - the member the method receive to add the register group
     */
    //methods to register and unregister observers
    @Override
    public void register(Member obj) {
        if (registers.containsKey(obj)) {
            System.out.println("The Member is already registered!");
        }
          else if (obj == null);

        else{
            this.registers.put(obj, ((ConcreteMember) obj).getName());
            obj.update(this.getUsb());
            System.out.println("The Member is register!");
        }
    }

    /**
     * The method removes a specific member from the Group admin.
     * @param obj - the member the method receive to remove from the register group
     */
    @Override
    public void unregister(Member obj) {
        if (!registers.containsKey(obj)) {
            System.out.println("This Member is not exist");
        } else {
            this.registers.remove(obj);
            System.out.println("The Member is unregister now!");
        }
    }

    /**
     * Inserts a specified string into character sequence.
     * After the method notify about the change to all the register members.
     *
     * @param offset the index (the location) we "pushing"" the string
     * @param obj   the string we insert to this character sequence.
     */
        @Override
        public void insert ( int offset, String obj){
            this.usb_status.insert(offset, obj);
            notifyAllRegisters();
        }

    /**
     * The method append specified string (obj) to character sequence (our usb).
     * After the method notify about the change to all the register members.
     *
     * @param obj - the specified string we add to the UndoableStringBuilder.
     */
        @Override
        public void append (String obj){
            this.usb_status.append(obj);
            notifyAllRegisters();
        }
    
    /**
     * The removes method delete the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index end.
     * After we notify about the change to all the register members.
     *
     * @param start the first index of the substring we delete
     * @param end   the last index of the substring we delete
     */
        @Override
        public void delete ( int start, int end){
            this.usb_status.delete(start, end);
            notifyAllRegisters();
        }

    /**
     * The undo method erases the last change done to the document, reverting it to an older state.
     * After we notify about the change to all the register members.
     */
        @Override
        public void undo () {
            this.usb_status.undo();
            notifyAllRegisters();
        }

    /**
     *  To string method
     * @return Nice print of the objects in the class and their names
     */
    @Override
    public String toString() {
        return "GroupAdmin{" +
                "registers=" + registers +
                ", usb_status=" + usb_status +
                '}';
    }
}
