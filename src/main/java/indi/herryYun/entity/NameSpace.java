package indi.herryYun.entity;

import java.util.ArrayList;

public class NameSpace {
    private String name;


    private Class<?> clazz;
    private ArrayList<Command> commands;

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public NameSpace(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }
}
