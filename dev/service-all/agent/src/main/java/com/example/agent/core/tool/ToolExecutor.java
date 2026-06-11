package com.example.agent.core.tool;

import java.util.List;

public class ToolExecutor {

    private final List<ToolMetadata> toolMetadataList;

    public ToolExecutor(List<ToolMetadata> toolMetadataList) {
        this.toolMetadataList = toolMetadataList;
    }

    // 工具执行器
    public String execute(ToolCall toolCall) {
        ToolMetadata toolMetadata = findTool(toolCall.name());

        try {
            Object invoke = toolMetadata.method().invoke(toolMetadata.target(), toolCall.argument());
            return String.valueOf(invoke);
        } catch (Exception e) {
            throw new RuntimeException("工具执行失败:" + toolCall.name(), e);
        }
    }

    private ToolMetadata findTool(String toolName) {
        for (ToolMetadata toolMetadata : toolMetadataList) {
            if (toolMetadata.name().equals(toolName)) {
                return toolMetadata;
            }
        }
        throw new IllegalArgumentException("未找到工具:" + toolName);
    }
}
