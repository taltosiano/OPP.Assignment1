package observer;

import java.util.HashMap;
import java.util.Map;

public class GroupAdmin implements Sender {
    private String name;
    //  List<Member> registers;
    private Map<String, Member> registers;
    private UndoableStringBuilder usb_status;

    public GroupAdmin() {
        this.registers = new HashMap<>();
        this.name = "";
        //    this.registers = new ArrayList<>();
        this.usb_status = new UndoableStringBuilder();
    }

    public Map<String, Member> getRegisters() {

        return registers;
    }

    public UndoableStringBuilder getUsb() {

        return this.usb_status;
    }

    public void setUsb(UndoableStringBuilder other_ust) {
        this.usb_status = other_ust;
        notifyAllRegisters();
    }

    /**
     * this function notify all the register members about every chanhe.
     */
    public void notifyAllRegisters() {
        registers.values().forEach(member -> member.update(this.getUsb()));
//         for (Member member: this.registers.values()) {
//             member.update(this.getUsb());
//         }

    }

    /**
     * @param obj
     */
    //methods to register and unregister observers
    @Override
    public void register(Member obj) {
        if (registers.containsValue(obj)) {
            System.out.println("The Member is already registered!");
        }
        else {
            this.registers.put(((ConcreteMember) obj).getName(), obj);
            System.out.println("The Member is register!");
        }
    }

    /**
     *
     * @param obj
     */
    @Override
    public void unregister(Member obj) {
        if (!registers.containsValue(obj)) {
            System.out.println("This Member is not exist");
        } else {
            this.registers.remove(((ConcreteMember) obj).getName());
            System.out.println("The Member is unregister now!");
            obj.update(null);
        }
    }

    /**
     * Inserts the string into this character sequence.
     * Exception: This method throws StringIndexOutOfBoundsException if The offset argument less
     * than zero, and greater from the length of this sequence.
     *
     * @param offset the index (the location) we "pushing"" the string
     * @param obj   the string we insert to this character sequence.
     * @return the finished string as UndoableStringBuilder
     */
        //Inserts the string into this character sequence.
        @Override
        public void insert ( int offset, String obj){
            this.usb_status.insert(offset, obj);
            notifyAllRegisters();

        }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param obj - the specified string we add to the UndoableStringBuilder.
     * @return the finished string as UndoableStringBuilder the Modifications.
     */
        // Appends the specified string to this character sequence.
        @Override
        public void append (String obj){
            this.usb_status.append(obj);
            notifyAllRegisters();

        }
    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * <p>
     * Exception: This method throws StringIndexOutOfBoundsException if the start is less
     * than zero, or start is larger than the length of String, or start is larger than end.
     *
     * @param start the first index of the substring we delete
     * @param end   the last index of the substring we delete
     * @return the finished string as UndoableStringBuilder after we delete the substring between index "start" until index "end"
     */
        // Removes the characters in a substring of this sequence.
        // sent the param the the delete methode from UndoableStringBuilder class
        @Override
        public void delete ( int start, int end){
            this.usb_status.delete(start, end);
            notifyAllRegisters();
        }

    /**
     * undo function cancled the last method we make. is there two edge
     * cases-if the string is empty(we didnt make any actions) and if we append one time.(we add string one time).
     * in these two cases we will did nothing and the our UndoableStringBuilder will be empty.
     */
        // Erases the last change done to the document, reverting
        // it to an older state.
        @Override
        public void undo () {
            this.usb_status.undo();
            notifyAllRegisters();
        }

        public String toString () {
            return "GroupAdmin{" +
                    "Name='" + name + '\'' +
                    ", registers=" + registers +
                    ", message=" + usb_status +
                    '}';
        }
}