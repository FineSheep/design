package com.example.design.bridge;

import com.example.design.bridge.function.RegisterLoginInterface;
import com.example.design.bridge.function.abs.AbstractRegisterLoginComponent;
import com.example.design.bridge.function.abs.RegisterLoginComponent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanghao
 * @createTime 2023/11/19 13:47
 * @description
 */

public class RegisterLoginComponentFactory {


    public static Map<String, AbstractRegisterLoginComponent> componentMap = new ConcurrentHashMap<>();
    public static Map<String, RegisterLoginInterface> funcMap = new ConcurrentHashMap<>();

    public static AbstractRegisterLoginComponent getComponent(String type) {
        AbstractRegisterLoginComponent component = componentMap.get("type");
        if (component == null) {
            synchronized (componentMap) {
                component = componentMap.get(type);
                if (component == null) {
                    component = new RegisterLoginComponent(funcMap.get(type));
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }
}
