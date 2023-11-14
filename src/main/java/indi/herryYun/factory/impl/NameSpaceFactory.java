package indi.herryYun.factory.impl;

import indi.herryYun.TerminalApplication;
import indi.herryYun.annotation.nameSpace;
import indi.herryYun.factory.Factory;
import indi.herryYun.utils.ComponentScan;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Iterator;

public class NameSpaceFactory extends Factory {
    private static final Class<? extends Annotation> markClass = nameSpace.class;

    public NameSpaceFactory() {
        super(markClass, 0);
    }

    @Override
    public void createClasses() throws IOException {
        Iterator<Class<?>> classIterator = ComponentScan.scanAnnotation(TerminalApplication.class,this.markClass).iterator();
        while (classIterator.hasNext()) {
            Class<?> clazz = classIterator.next();
            if (componentScan.isSuitableAnnotation(nameSpace.class, clazz)) {
                applicationContext.nameSpaces.add(new indi.herryYun.entity.NameSpace(clazz.getAnnotation(nameSpace.class).nameSpace(), clazz));
            }
        }
        System.out.println(applicationContext.nameSpaces);
    }
}
