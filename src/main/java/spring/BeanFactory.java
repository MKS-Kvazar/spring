package spring;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BeanFactory {
    private Map<String, Object> singletons = new HashMap();
    private static final Logger log = Logger.getLogger(BeanFactory.class.getName());

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

    public void instantiate(String basePackage) throws IOException,
            URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = basePackage.replace('.', '/'); //"com.mks" -> "com/mks"
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File file = new File(resource.toURI());
            for (File classFile : file.listFiles()) {
                String fileName = classFile.getName(); //ProductService.class
                if (fileName.endsWith(".class")) {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    Class classObject = Class.forName(basePackage + "." + className);
                    if (classObject.isAnnotationPresent(Component.class)) {
                        log.info("Component: " + classObject);
                        Object instance = classObject.newInstance();//=new CustomClass()
                        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                        singletons.put(beanName, instance);
                    }
                }
            }
        }
    }

    public void populateProperties() throws IllegalAccessException, InvocationTargetException {
        log.info("==populateProperties==");
        for (Object object : singletons.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    for (Object dependency : singletons.values()) {
                        if (dependency.getClass().equals(field.getType())) {
                            field.setAccessible(true);
                            if (!field.isAnnotationPresent(Qualifier.class)) {
                                runPostConstructor(object);
                            }
                            field.set(object, dependency);
                        }
                    }
                    if (field.isAnnotationPresent(Qualifier.class)) {
                        for (Object dependency : singletons.values()) {
                            Qualifier annotation = field.getAnnotation(Qualifier.class);
                            String[] args = dependency.getClass().getTypeName().split("\\.");
                            String className = args[args.length - 1];
                            String dependencyClass = className.substring(0, 1).toLowerCase() + className.substring(1);
                            if (dependencyClass.equals(annotation.value())) {
                                field.setAccessible(true);
                                runPostConstructor(object);
                                field.set(object, dependency);
                            }
                        }
                    }
                }
            }
        }
    }

    private void runPostConstructor(Object object) throws IllegalAccessException, InvocationTargetException {
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method method:methods){
            if(method.isAnnotationPresent(PostConstructor.class)){
                method.invoke(object);
            }
        }
    }
}