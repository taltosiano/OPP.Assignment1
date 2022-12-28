import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    @Test
    public void test(){
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm1 = new ConcreteMember("asaf");

        GA.register(cm);
        GA.register(cm1);

        System.out.println("------printing the jvmInfo-----");
        logger.info(() -> JvmUtilities.jvmInfo());
        System.out.println();

        System.out.println("------testing Footprint-----");
        GA.append("Hello");
        logger.info(()->JvmUtilities.objectFootprint(GA));
        logger.info(()->JvmUtilities.objectFootprint(cm, cm1));
        GA.delete(3,5);
        logger.info(()->JvmUtilities.objectFootprint(cm));
        System.out.println();

        System.out.println("------testingTotalSize-----");
        logger.info(()->JvmUtilities.objectTotalSize(GA));
        GA.register(new ConcreteMember("adi"));
        logger.info(()->JvmUtilities.objectTotalSize(GA));
        GA.append("World");
        logger.info(()->JvmUtilities.objectTotalSize(GA));
        System.out.println();

        System.out.println("------testing memoryStats-----");
        logger.info(() -> JvmUtilities.memoryStats(GA));
        logger.info(() -> JvmUtilities.memoryStats(cm));
        logger.info(() -> JvmUtilities.memoryStats(cm1));


    }

    // ------GroupAdmin testing------


    @Test
    void notifyAllRegister() {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm2 = new ConcreteMember("adi");
        ConcreteMember cm3 = new ConcreteMember("asaf");
        ConcreteMember cm4 = new ConcreteMember("TalTosiano");
        GA.register(cm);
        GA.register(cm2);
        GA.register(cm3);
        GA.notifyAllRegisters();
        assertEquals("",cm.getUsb_status().toString());
        GA.append("helloAllRegisters!");
        GA.notifyAllRegisters();
        assertEquals("helloAllRegisters!",cm.getUsb_status().toString());

    }

    @Test
    void register() {
        GroupAdmin GA = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm2 = new ConcreteMember("adi");
        ConcreteMember cm3 = new ConcreteMember("asaf");
        ConcreteMember cm5 = new ConcreteMember();
        assertTrue(GA.getRegisters().isEmpty());
        GA.register(cm);
        GA.register(cm2);
        GA.register(cm3);

        assertTrue(GA.getRegisters().containsKey(cm));
        assertTrue(GA.getRegisters().containsKey(cm2));
        assertTrue(GA.getRegisters().containsKey(cm3));
        assertEquals(3, GA.getRegisters().size());

        GA.register(cm3);                                   // the member is already registered - nothing changed
        assertEquals(3, GA.getRegisters().size());
        ConcreteMember cm4 = null;
        GA.register(cm4);
        assertEquals(3, GA.getRegisters().size());
        assertFalse(GA.getRegisters().containsKey(cm4));

    }

    @Test
    void unregister() {

        GroupAdmin GA = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm2 = new ConcreteMember("adi");
        ConcreteMember cm3 = new ConcreteMember("asaf");
        ConcreteMember cm4 = new ConcreteMember("TalTosiano");
        GA.register(cm);
        assertFalse(GA.getRegisters().isEmpty());
        GA.unregister(cm);
        assertTrue(GA.getRegisters().isEmpty());
        GA.register(cm);
        GA.register(cm2);
        GA.register(cm3);
        assertEquals(3, GA.getRegisters().size());
        GA.unregister(cm3);
        assertEquals(2, GA.getRegisters().size());
        assertFalse(GA.getRegisters().containsKey(cm3));
        GA.unregister(cm2);
        GA.unregister(cm);
        assertFalse(GA.getRegisters().containsKey(cm2));
        assertFalse(GA.getRegisters().containsKey(cm));
        assertEquals(0, GA.getRegisters().size());

    }

    @Test
    void insert() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm1 = new ConcreteMember("adi");
        ga.register(cm);
        ga.insert(0, "ok");
        assertEquals("ok", cm.getUsb_status().toString());
        ga.register(cm1);
        ga.insert(2, "ay");
        assertEquals("okay", cm.getUsb_status().toString());
        assertEquals("okay", cm1.getUsb_status().toString());
        assertThrows(StringIndexOutOfBoundsException.class , ()->{    // the start is less than zero
            ga.insert(-1, "hi");
        });
    }

    @Test
    void append() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm2 = new ConcreteMember("asaf");
        ConcreteMember cm3 = new ConcreteMember("Tal");
        ga.register(cm2);
        ga.append("hello");
        assertEquals("hello", cm2.getUsb_status().toString());
        ga.register(cm3);
        ga.append("World");
        assertEquals("helloWorld", cm2.getUsb_status().toString());
        assertEquals("helloWorld", cm3.getUsb_status().toString());
        ga.append("");
        assertEquals("helloWorld", ga.getUsb().toString());

        ConcreteMember unknown = new ConcreteMember();
        ga.register(unknown);
        assertEquals("helloWorld", unknown.getUsb_status().toString());


    }

    @Test
    void delete() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm1 = new ConcreteMember("adi");
        ga.register(cm);
        ga.register(cm1);
        ga.append("hello");
        assertEquals("hello", ga.getUsb().toString());
        ga.delete(1, 4);
        assertEquals("ho", ga.getUsb().toString());
        ga.append("rse");
        ga.delete(0, 3);
        assertEquals("se", ga.getUsb().toString());
        assertThrows(StringIndexOutOfBoundsException.class , ()->{    //  start is larger than the end
            ga.delete(3,1);
        });

    }

    @Test
    void undo() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm2 = new ConcreteMember("asaf");
        ConcreteMember cm3 = new ConcreteMember("Tal");
        ga.append("Hello");
        ga.append("World");
        ga.undo();
        assertEquals("Hello", ga.getUsb().toString());
        ga.insert(0, "big");
        assertEquals("bigHello", ga.getUsb().toString());
        ga.delete(2, 5);
        assertEquals("billo", ga.getUsb().toString());
        ga.undo();
        assertEquals("bigHello", ga.getUsb().toString());

    }


    // ------ConcreteMember testing------
    @Test
    void update() {
        GroupAdmin ga = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("tal");
        ConcreteMember cm1 = new ConcreteMember("asaf");
        ga.register(cm);
        ga.append("big");
        cm.update(ga.getUsb());
        assertEquals("big", cm.getUsb_status().toString());
        ga.append("World");
        cm1.update(ga.getUsb());
        assertEquals("bigWorld", cm1.getUsb_status().toString());
        assertEquals("bigWorld", ga.getUsb().toString());
        ga.undo();
        assertEquals("big", ga.getUsb().toString());

    }

}

