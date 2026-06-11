package com.example.agent.demo;

import com.example.agent.core.annotation.Tool;

public class WeatherTool {

    @Tool("查询城市天气")
    public String weather(String city) {
        return city + "今天多云，28°C";
    }
}
