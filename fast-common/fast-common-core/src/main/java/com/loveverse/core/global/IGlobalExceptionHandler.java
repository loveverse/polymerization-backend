package com.loveverse.core.global;



import com.loveverse.core.http.ResponseData;

import javax.servlet.http.HttpServletRequest;

/**
 * @author love
 * @since 2025/4/9
 */
@FunctionalInterface
public interface IGlobalExceptionHandler<T extends Throwable> {
    ResponseData<Void> handle(HttpServletRequest req, T ex);
}
