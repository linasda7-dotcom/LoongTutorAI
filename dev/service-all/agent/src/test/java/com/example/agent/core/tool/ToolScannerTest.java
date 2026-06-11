package com.example.agent.core.tool;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ToolScannerTest {
    @Test
    void testScan() {

        WeatherTool weatherTool = new WeatherTool();
        List<ToolMetadata> tools = ToolScanner.scan(weatherTool);

        tools.forEach(tool -> {
            System.out.println("工具名称:" + tool.name());
            System.out.println("工具描述:" + tool.description());
            System.out.println("工具目标对象:" + tool.target().getClass().getSimpleName());
            System.out.println("工具方法:" + tool.method().getName());
        });
    }
}
