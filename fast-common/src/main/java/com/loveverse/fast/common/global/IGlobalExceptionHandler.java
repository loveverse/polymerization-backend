package com.loveverse.fast.common.global;

import com.loveverse.fast.common.http.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * @author love
 * @since 2025/4/9
 */
@FunctionalInterface
public interface IGlobalExceptionHandler<T extends Throwable> {
    ResponseData<Void> handle(HttpServletRequest req, T ex);
}
