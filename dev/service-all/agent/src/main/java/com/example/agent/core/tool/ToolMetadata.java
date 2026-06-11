package com.example.agent.core.tool;

import java.lang.reflect.Method;

public class ToolMetadata {
    private final String name;
    private final String description;
    private final Object target;
    private final Method method;

    public ToolMetadata(String name, String description, Object target, Method method) {
        this.name = name;
        this.description = description;
        this.target = target;
        this.method = method;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public Object target() {
        return target;
    }

    public Method method() {
        return method;
    }

    
}
