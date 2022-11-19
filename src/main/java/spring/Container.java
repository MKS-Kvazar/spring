package spring;

import java.io.IOException;
import java.net.URISyntaxException;

public class Container {

    private ApplicationContext applicationContext;

    public Container someAction(String basePackage, Class runner) throws ReflectiveOperationException, IOException, URISyntaxException {
        String[] args = runner.getTypeName().split("\\.");
        String className = args[args.length - 1];
        String runClass = className.substring(0, 1).toLowerCase() + className.substring(1);
        applicationContext = new ApplicationContext(basePackage, runClass);
        return this;
    }

    public Object getInstance(Class name) {
        String[] args = name.getTypeName().split("\\.");
        String className = args[args.length - 1];
        String nameClass = className.substring(0, 1).toLowerCase() + className.substring(1);
        return applicationContext.getBean(nameClass);
    }
}
