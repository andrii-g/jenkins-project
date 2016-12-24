import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import setup.JbehaveSetup;

/**
 * Created by User on 25.12.2016.
 */
public class TestRunner {

    @Test
    public void test(){
        JbehaveSetup jbehaveSetup = new JbehaveSetup();
        try {
            jbehaveSetup.run();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
