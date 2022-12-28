package observer;

import java.util.LinkedList;

public class UndoableStringBuilder {

    private StringBuilder sb;
    LinkedList<StringBuilder> s = new LinkedList<>();

    @Override
    public String toString() {
        return sb.toString();
    }

    public UndoableStringBuilder() {
        this.sb = new StringBuilder();
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str - the specified string we add to the UndoableStringBuilder.
     * @return the finished string as UndoableStringBuilder the Modifications.
     */
    public UndoableStringBuilder append(String str) {
        sb.append(str);
        StringBuilder tmp = new StringBuilder(sb.toString());
        s.push(tmp);

        return this;
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
    public UndoableStringBuilder delete(int start, int end) throws StringIndexOutOfBoundsException {

        sb.delete(start, end);
        StringBuilder tmp = new StringBuilder(sb);
        s.push(tmp);

        return this;
    }

    /**
     * Inserts the string into this character sequence.
     * Exception: This method throws StringIndexOutOfBoundsException if The offset argument less
     * than zero, and greater from the length of this sequence.
     *
     * @param offset the index (the location) we "pushing"" the string
     * @param str    the string we insert to this character sequence.
     * @return the finished string as UndoableStringBuilder
     */
    public UndoableStringBuilder insert(int offset, String str) throws StringIndexOutOfBoundsException {
        sb.insert(offset, str);
        StringBuilder tmp = new StringBuilder(sb);
        s.push(tmp);

        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in the specified String.
     * The substring begins at the specified start and extends to the character at index end - 1 or to
     * the end of the sequence if no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     * <p>
     * Exception: If the start is negative, greater than length(), or greater
     * than end then StringIndexOutOfBoundsException.
     *
     * @param start - the first index in the substring we replace
     * @param end   - the last index in the substring we replace
     * @param str   - the string we replace with the substring we set(start,end)
     * @return the finished string as UndoableStringBuilder
     */
    public UndoableStringBuilder replace(int start, int end, String str) throws StringIndexOutOfBoundsException {

        sb.replace(start, end, str);
        StringBuilder tmp = new StringBuilder(sb);
        s.push(tmp);

        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     *
     * @return the string in reverse as UndoableStringBuilder
     */
    public UndoableStringBuilder reverse() {

        sb.reverse();
        StringBuilder tmp = new StringBuilder(sb);
        s.push(tmp);

        return this;
    }

    /**
     * undo function cancled the last method we make. is there two edge
     * cases-if the string is empty(we didnt make any actions) and if we append one time.(we add string one time).
     * in these two cases we will did nothing and the our UndoableStringBuilder will be empty.
     */
    public void undo() {

        if (s.size() == 0) {
            return;
        }
        if (s.size() == 1) {
            s.pop();
            sb = new StringBuilder();
            return;
        }

        s.pop();
        StringBuilder tmp = new StringBuilder(s.peek());
        sb = new StringBuilder(tmp.toString());
        return;
    }
}


