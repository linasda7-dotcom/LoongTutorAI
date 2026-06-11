package com.example.agent.core.tool;

public class ToolExecutor {

    // 工具执行器
    public static Object execute(ToolMetadata toolMetadata, Object... args) {
        try {
            return toolMetadata.method().invoke(
                    toolMetadata.target(),
                    args);
        } catch (Exception e) {
            throw new RuntimeException("工具执行失败:" + toolMetadata.name(), e);
        }
    }
}
