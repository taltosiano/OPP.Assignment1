package observer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static
        org.junit.jupiter.api.Assertions.*;


class UndoableStringBuilderTest {
    UndoableStringBuilder usb = new UndoableStringBuilder();
    String s_usb = "TalTosiano";
    UndoableStringBuilder usb1 = new UndoableStringBuilder();
    String s_usb1 = "SoMuch";
    UndoableStringBuilder usb2 = new UndoableStringBuilder();
    String s_usb2 = "HelloWorld";
    String st = "Fun";
    String st1 = "Big";

    @Test
    void testToString() {
        usb.append(s_usb);
        assertEquals("TalTosiano", usb.toString());

    }

    @Test
    void append() {
        usb1.append(s_usb1);
        usb1.append(st);
        assertEquals("SoMuchFun", usb1.toString());

        usb.append("hi");
        assertEquals("hi", usb.toString());

        usb1.append("");
        assertEquals("SoMuchFun", usb1.toString());
    }


    @Test
    void delete() {
        usb.append(s_usb);
        assertThrows(StringIndexOutOfBoundsException.class , ()->{    //  start is larger than the end
            usb.delete(3,1);
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{   // the start is less than zero
            usb.delete(-1,1);
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{    //start is larger than the length of String
            usb.delete(11,13);
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{    //start is larger than the length of String and from the end
            usb.delete(12,3);
        });

        usb.delete(2,7);
        assertEquals("Taano", usb.toString());
        usb2.append(s_usb2);
        usb2.delete(5,13);                              // when the end is longer then the s.len , we delete until the end
        assertEquals("Hello", usb2.toString());
        usb1.append(s_usb1);
        usb1.delete(2,2);
        assertEquals("SoMuch", usb1.toString());



    }

    @Test
    void insert() {
        usb2.append(s_usb2);
        usb2.insert(5,st1);
        assertEquals("HelloBigWorld", usb2.toString());

        assertThrows(StringIndexOutOfBoundsException.class , ()->{    // the start is less than zero
            usb2.insert(-1,st1);
        });

        assertThrows(StringIndexOutOfBoundsException.class , ()->{   //start is larger than the length of String
            usb2.insert(14,st1);
        });
    }

    @Test
    void replace() {
        usb2.append(s_usb2);
        usb2.replace(2, 5, " love the ");
        assertEquals("He love the World", usb2.toString());

        UndoableStringBuilder stt = new UndoableStringBuilder();
        stt.replace(0, 5, "love");
        assertEquals("love", stt.toString());

        usb.append(s_usb);
        usb.replace(6, 12, "555");    // the end index is longer than the string len.
        assertEquals("TalTos555", usb.toString());

        UndoableStringBuilder sttt = new UndoableStringBuilder();
        sttt.append("HelloTal");
        assertThrows(StringIndexOutOfBoundsException.class , ()->{   //  start is larger than the end
            sttt.replace(3, 1, "love");
        });
        assertThrows(StringIndexOutOfBoundsException.class , ()->{  // the start is less than zero
            sttt.replace(-1, 1, "love");
        });

        sttt.replace(5, 5, "love");
        assertEquals("HelloloveTal", sttt.toString());
    }

    @Test
    void reverse() {
        usb1.append(s_usb1);
        usb1.reverse();
        assertEquals("hcuMoS", usb1.toString());

        usb.reverse();
        assertEquals("", usb.toString());    // empty string return empty string - not exception!!

        UndoableStringBuilder st = new UndoableStringBuilder();
        st.append("B");
        st.reverse();
        assertEquals("B", st.toString());

    }

    @Test
    void undo() {
        UndoableStringBuilder usb4 = new UndoableStringBuilder();
        usb4.append("Hello");
        usb4.append("Pretty");
        usb4.append("World");
        usb4.undo();
        assertEquals("HelloPretty", usb4.toString());
        usb4.append("World");
        assertEquals("HelloPrettyWorld", usb4.toString());
        usb4.delete(5,11);
        assertEquals("HelloWorld", usb4.toString());
        usb4.undo();
        assertEquals("HelloPrettyWorld", usb4.toString());

        UndoableStringBuilder usb3 = new UndoableStringBuilder();   //  string with one append action return empty string
        usb3.append("hi");
        usb3.undo();
        assertEquals("", usb3.toString());

        UndoableStringBuilder stt0 = new UndoableStringBuilder();   // empty string return empty string - not exception!!
        stt0.undo();
        assertEquals("", stt0.toString());


    }
}