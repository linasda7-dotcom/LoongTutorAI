package com.example.agent.core.tool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.agent.core.annotation.Tool;

public class ToolScanner {
    public static List<ToolMetadata> scan(Object toolObject) {
        List<ToolMetadata> tools = new ArrayList<>();

        Class<?> clazz = toolObject.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Tool.class)) {
                Tool tool = method.getAnnotation(Tool.class);

                String name = method.getName();
                String description = tool.value();

                tools.add(new ToolMetadata(name, description, toolObject, method));
            }
        }
        return tools;
    }
}
