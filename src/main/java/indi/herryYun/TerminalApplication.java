package indi.herryYun;

import indi.herryYun.factory.Factory;

public class TerminalApplication {
    protected static ApplicationContext applicationContext = new ApplicationContext();
    public static ApplicationContext run(Class<?> clazz) throws Exception {
       Factory.initFactories(clazz.getPackage().getName(),applicationContext);
       return applicationContext;
    }
    public static void main(String args[]) throws Exception {
        TerminalApplication.run(TerminalApplication.class);
    }
}
