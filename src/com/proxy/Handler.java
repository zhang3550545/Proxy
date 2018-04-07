package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class Handler implements InvocationHandler {

    private Object target;

    private ActionInfo[] actionInfos;

    public Handler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(this.actionInfos);
        method.invoke(target);
        after(this.actionInfos);
        return null;
    }

    private void before(ActionInfo[] actionInfos) throws Exception {
        for (ActionInfo actionInfo : actionInfos) {
            if (actionInfo != null && (actionInfo.getType() == Type.BEFORE || actionInfo.getType() == Type.ALL)) {
                action(actionInfo);
                break;
            }
        }
    }

    private void after(ActionInfo[] actionInfos) throws Exception {
        for (ActionInfo actionInfo : actionInfos) {
            if (actionInfo != null && (actionInfo.getType() == Type.AFTER || actionInfo.getType() == Type.ALL)) {
                action(actionInfo);
                break;
            }
        }
    }

    private void action(ActionInfo actionInfo) throws Exception {
        String className = actionInfo.getClassName();
        String methodName = actionInfo.getMethodName();
        Class<?> cls = Class.forName(className);
        Object o = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(o);
    }

    public void setInfo(ActionInfo[] actionInfos) {
        this.actionInfos = actionInfos;
    }
}
