package indi.herryYun.factory.impl;

import indi.herryYun.annotation.command;
import indi.herryYun.annotation.param;
import indi.herryYun.entity.Command;
import indi.herryYun.factory.Factory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class CommandFactory extends Factory {
    private static final Class<? extends Annotation> markClass = command.class;

    public CommandFactory() {
        super(markClass, 1);
    }

    @Override
    public void createClasses() throws IOException {
        applicationContext.nameSpaces.forEach(nameSpace -> {
            //get all marked methods in the namespace
            List<Method> methods = nameSpace.getClazz().getAnnotation(command.class) != null ? Arrays.asList(nameSpace.getClazz().getMethods()) : findCommands(nameSpace.getClazz());
            methods.forEach(item -> {
                command[] commands = item.getDeclaredAnnotationsByType(command.class);
                String commandName = commands != null ? commands[0].name() : item.getName();
                Command command = null;
                System.out.println(item.getClass().getName());
                if (item.getTypeParameters().length == 0) {

                    command = new Command(commandName, item, null,null);
                } else {
                    //传入map | 根据param传入指定参数
                    command = new Command(commandName, item, (Parameter[]) Arrays.stream(item.getTypeParameters()).filter(param -> param.getAnnotation(param.class) != null).toArray(),null);
//                    System.out.println("This command does not contain a name.");
                }
                nameSpace.addCommand(command);
            });
        });
    }

    private ArrayList<Method> findCommands(Class<?> clazz) {
        return Arrays.stream(clazz.getMethods()).filter(this::isHasCommandAnnotation).collect(Collectors.toCollection(ArrayList<Method>::new));
    }

    private boolean isHasCommandAnnotation(Method method) {
        return method.getAnnotation(markClass) != null;
    }
}
