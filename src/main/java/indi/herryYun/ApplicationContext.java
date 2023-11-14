package indi.herryYun;

import indi.herryYun.entity.NameSpace;

import java.util.ArrayList;

//应用上下文
public class ApplicationContext {
    //当前命名空间
    protected String nowNameSpace = "root";
    public ArrayList<NameSpace> nameSpaces = new ArrayList<>();
    private boolean enableAutoMethodInvoke = false;
    protected ApplicationContext() {

    }
}
