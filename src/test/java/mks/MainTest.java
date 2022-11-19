package mks;

import spring.ApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainTest {

    public static void main(String[] args) throws IOException,
            URISyntaxException, ReflectiveOperationException {
        String basePackage = "mks"; //java.com.mks
        ApplicationContext applicationContext = new ApplicationContext(basePackage, Runner.class);
    }
}
