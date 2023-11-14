package indi.herryYun.factory;

import indi.herryYun.ApplicationContext;
import indi.herryYun.utils.ComponentScan;
import indi.herryYun.utils.ResourceFind;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Factory implements Comparable<Factory> {
    protected Class<? extends Annotation> markClass;
    protected int index;
    private static final ArrayList<Factory> factories = new ArrayList<>();
    protected static ApplicationContext applicationContext;
    protected static ComponentScan componentScan;

    public Factory(Class<? extends Annotation> markClass, int index) {
        this.markClass = markClass;
        this.index = index;
    }

    public static void initFactories(String basePackage, ApplicationContext applicationContext) throws Exception {
        Factory.applicationContext = applicationContext;
        componentScan = new ComponentScan(basePackage);
        Iterator<Class<?>> iterator = ResourceFind.getClasses(basePackage).stream().filter(clazz -> componentScan.isSuitableClass(clazz, Factory.class)).collect(Collectors.toCollection(ArrayList::new)).iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            factories.add(((Factory) clazz.newInstance()));
        }
        Collections.sort(factories);
        run();
    }
    private static void run() throws IOException {
        Iterator<Factory> factoryIterator = factories.iterator();
        while (factoryIterator.hasNext()) {
            Factory factory = factoryIterator.next();
            factory.createClasses();
        }
    }
    public abstract void createClasses() throws IOException;

    @Override
    public int compareTo(Factory factory) {
        return factory.index - this.index;
    }
}
