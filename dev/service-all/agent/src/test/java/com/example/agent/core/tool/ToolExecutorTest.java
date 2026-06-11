package com.example.agent.core.tool;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.agent.demo.WeatherTool;

public class ToolExecutorTest {

    @Test
    void testExecute() {
        WeatherTool weatherTool = new WeatherTool();
        List<ToolMetadata> tools = ToolScanner.scan(weatherTool);

        ToolMetadata weather = tools.get(0);
        // Object execute = ToolExecutor.execute(weather, "广州");
        // System.out.println(execute);
    }

}
