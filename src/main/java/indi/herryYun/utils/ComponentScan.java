package indi.herryYun.utils;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

//扫描实现类
public class ComponentScan {
    private ArrayList<Class<?>> rawMarkClasses = new ArrayList<>();
    private String basePackage;

    public ComponentScan(String basePackage) {
        this.basePackage = basePackage;
    }

    public static <A extends Annotation> ArrayList<Class<?>> scanAnnotation(Class<?> scanRootClass,Class<A> annotation) throws IOException {
        return ResourceFind.findClasses(scanRootClass).stream().filter(clazz->clazz.getAnnotation(annotation) != null).collect(Collectors.toCollection(ArrayList::new));
    }
    //    public <A extends Annotation> void  scanAnnotation(Class<A>[] annotations) {
//
//    }
//    public void  scanSuitableClasses(Class<?>[] fatherClasses) {
//           for(Class<?> fatherclass : fatherClasses) {
//               isSuitableClass(,)
//           }
//    }

    public <A extends Annotation> boolean isSuitableClass(Class<?> clazz, Class<A>[] suitableClasses) {
        for (Class<A> suitableClass : suitableClasses
        ) {
            if (suitableClass.isAnnotation()) {
                return isSuitableAnnotation(suitableClass, clazz);
            } else {
                return isSuitableClass(clazz, suitableClass);
            }
        }
        return false;
    }

    public boolean isSuitableClass(Class<?> clazz, Class<?> fatherClass) {
        if (clazz == fatherClass) {
            return false;
        }
        return fatherClass != null && fatherClass.isAssignableFrom(clazz);
    }

    public  <A extends Annotation> boolean isSuitableAnnotation(Class<A> suitableAnnotation, Class<?> clazz) {
        return clazz.getAnnotation(suitableAnnotation) != null;
    }

}
