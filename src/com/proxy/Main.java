package com.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("com.proxy.ActionImpl");
        Constructor<?> constructor = clazz.getConstructor();
        ActionImpl user = (ActionImpl) constructor.newInstance();

        Handler handler = new Handler(user);
        ActionInfo actionInfo1 = new ActionInfo();
        actionInfo1.setClassName("com.proxy.EnAction");
        actionInfo1.setMethodName("before");
        actionInfo1.setType(Type.BEFORE);

        ActionInfo actionInfo2 = new ActionInfo();
        actionInfo2.setClassName("com.proxy.EnAction");
        actionInfo2.setMethodName("after");
        actionInfo2.setType(Type.AFTER);

        ActionInfo[] actionInfos = new ActionInfo[]{actionInfo1, actionInfo2};
        handler.setInfo(actionInfos);

        Action action = (Action) Proxy.newProxyInstance(ActionImpl.class.getClassLoader(), user.getClass().getInterfaces(), handler);
        action.action();
    }
}
