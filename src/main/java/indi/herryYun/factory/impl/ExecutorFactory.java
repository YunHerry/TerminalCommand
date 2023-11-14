package indi.herryYun.factory.impl;

import indi.herryYun.annotation.component;
import indi.herryYun.factory.Factory;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class ExecutorFactory extends Factory {
    private static final Class<? extends Annotation> markClass = component.class;
    public ExecutorFactory() {
        super(markClass, 1);
    }

    @Override
    public void createClasses() throws IOException {

    }
}
