package com.loveverse.core.exception;

/**
 * 自定义服务器异常类，支持格式化消息
 *
 * @author love
 * @since 2025/4/8
 */
public class ServerException extends RuntimeException {

    /**
     * 构造函数，支持格式化消息
     *
     * @param message 异常消息模板，使用 {} 作为占位符
     * @param args    格式化参数
     */
    public ServerException(String message, Object... args) {
        super(formatMessage(message, args));
    }

    /**
     * 构造函数，仅包含异常消息
     *
     * @param message 异常消息
     */
    public ServerException(String message) {
        super(message);
    }

    /**
     * 构造函数，包含异常消息和原因
     *
     * @param message 异常消息模板，使用 {} 作为占位符
     * @param cause   异常原因
     * @param args    格式化参数
     */
    public ServerException(String message, Throwable cause, Object... args) {
        super(formatMessage(message, args), cause);
    }

    /**
     * 构造函数，仅包含异常原因
     *
     * @param cause 异常原因
     */
    public ServerException(Throwable cause) {
        super(cause);
    }

    /**
     * 格式化消息，将 {} 替换为 %s 并进行格式化
     *
     * @param message 消息模板
     * @param args    格式化参数
     * @return 格式化后的消息
     */
    private static String formatMessage(String message, Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        // 替换占位符 {} 为 %s
        String replaced = message.replace("{}", "%s");
        return String.format(replaced, args);
    }
}
