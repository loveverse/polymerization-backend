package com.loveverse.auth.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loveverse.core.dto.PageParam;
import com.loveverse.core.dto.PageResult;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/20 17:01
 */
public class PageUtils {
    public static <T> IPage<T> startPage(PageParam pageParam) {
        return new Page<>(pageParam.getPage(), pageParam.getSize());
    }

    public static <T> PageResult<T> convert(IPage<T> page) {
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public static <T, R> PageResult<R> convert(IPage<T> page, Function<T, R> converter) {
        List<R> records = page.getRecords().stream()
                .map(converter)
                .collect(Collectors.toList());

        return new PageResult<>(
                page.getCurrent(),
                page.getSize(),
                page.getTotal(),
                records
        );
    }
}
