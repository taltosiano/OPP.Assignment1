import observer.ConcreteMember;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
//        String s1 = "Alice";
//        String s2 = "Bob";

        GroupAdmin GA = new GroupAdmin();
        GA.register(new ConcreteMember("aa"));
        GroupAdmin GA2 = new GroupAdmin();
        ConcreteMember cm = new ConcreteMember("bb");
        GA2.register(cm);
        GA2.append("123");
        GA2.delete(0,1);

        logger.info(()->JvmUtilities.objectFootprint(GA2));

//        logger.info(()->JvmUtilities.objectFootprint(s1));
//
//        logger.info(()->JvmUtilities.objectFootprint(s1,s2));
//
//        logger.info(()->JvmUtilities.objectTotalSize(s1));
//
//        logger.info(() -> JvmUtilities.jvmInfo());
    }
}
