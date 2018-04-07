package com.proxy;

/**
 * Created by zhang on 2018/4/7.
 */
public class ActionInfo {
    private String className;
    private String methodName;
    private Type type;

    public ActionInfo() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
