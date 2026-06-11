package com.example.agent.provider.openai;

import com.example.agent.core.model.ChatModel;

public class OpenAiChatModel implements ChatModel {

    @Override
    public String chat(String message) {
        System.out.println("目标即将接收的参数:");
        System.out.println(message);

        // 获取最后一条消息
        String lastLine = getLastMeaningfulLine(message);

        // 如果最后一条消息是工具开头的那么
        if (lastLine.startsWith("tool:")) {
            // 截取字符串从数组下标以字符 tool: 长度开始的剩余字符串
            String toolResult = lastLine.substring("tool:".length());
            return "根据工具查询结果：" + toolResult;
        }
        // 如果最后一条消息是用户那么
        if (lastLine.startsWith("user:")) {
            String userMessage = lastLine.substring("user:".length());
            // 如果用户消息包含天气那么
            if (userMessage.contains("天气")) {
                return "TOOL_CALL:weather:广州";
            }
        }

        // 兜底返回
        return "收到";
    }

    public String getLastMeaningfulLine(String prompt) {
        String[] lines = prompt.split("\n");
        for (int i = lines.length - 1; i >= 0; i--) {
            String line = lines[i];
            if (!line.isEmpty()) {
                return line;
            }
        }
        return "";
    }

}
