package mks;

import spring.Container;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainTest {

    public static void main(String[] args) throws IOException,
            URISyntaxException, ReflectiveOperationException {
        String basePackage = "mks"; //java.com.mks
        Container container = new Container().someAction(basePackage, Runner.class);
        TestOne testOne = (TestOne) container.getInstance(TestOne.class);
        System.out.println(testOne);
    }
}
