package spring;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class ApplicationContext {
    private BeanFactory beanFactory = new BeanFactory();
    private static final Logger log = Logger.getLogger(ApplicationContext.class.getName());

    public ApplicationContext(String basePackage, String runClass) throws ReflectiveOperationException, IOException, URISyntaxException {
        log.info("Run ApplicationContext");
        beanFactory.instantiate(basePackage);
        beanFactory.populateProperties();
        log.info("Class run - " + runClass);
        Object run = beanFactory.getBean(runClass);
        run.getClass().getMethod("run").invoke(run);
    }

    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }
}