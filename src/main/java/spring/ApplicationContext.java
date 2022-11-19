package spring;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class ApplicationContext {
    private BeanFactory beanFactory = new BeanFactory();
    private static final Logger log = Logger.getLogger(ApplicationContext.class.getName());

    public ApplicationContext(String basePackage, Class runner) throws ReflectiveOperationException, IOException, URISyntaxException {
        log.info("Run ApplicationContext");
        beanFactory.instantiate(basePackage);
        beanFactory.populateProperties();
        String[] args = runner.getTypeName().split("\\.");
        String className = args[args.length-1];
        String runClass = className.substring(0, 1).toLowerCase() + className.substring(1);
        log.info("Class run - " + runClass);
        Object run = beanFactory.getBean(runClass);
        System.out.println(run);
        run.getClass().getMethod("run").invoke(run);
    }
}