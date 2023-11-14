package indi.herryYun.entity;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Command {
    private String name;
    private Method method;
    private Parameter[] argsList;


    private Class<?> sourceBean;
    public Command(String name, Method method, Parameter[] argsList,Class<?> clazz) {
        this.name = name;
        this.method = method;
        this.argsList = argsList;
        this.sourceBean = clazz;
    }

    public void invoke() {

    }

    public Method getMethod() {
        return method;
    }

    public Parameter[] getArgsList() {
        return argsList;
    }

}
