//package com.loveverse.core.util;
//
//import com.baomidou.mybatisplus.core.metadata.OrderItem;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.loveverse.fast.common.dto.PageParam;
//import com.loveverse.fast.common.dto.PageResult;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Collections;
//import java.util.List;
//
///**
// * @author love
// * @since 2025/4/4
// */
//public final class PageUtils {
//    private PageUtils() {
//    }
//
//    public static <T> Page<T> convertToMyBatisPlusPage(Pageable pageable) {
//        Page<T> mybatisPage = new Page<>(pageable.getPageNumber() + 1, pageable.getPageSize());
//        if (pageable.getSort().isSorted()) {
//            pageable.getSort().forEach(order -> mybatisPage.addOrder(order.isAscending() ? OrderItem.asc(order.getProperty()) : OrderItem.desc(order.getProperty())));
//        }
//        return mybatisPage;
//    }
//
//    public static <T, U> PageResult<T> getPage(Page<U> page, List<T> collect) {
//        return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), collect);
//    }
//
//    public static <T extends PageParam, U> PageResult<U> defaultPage(T query) {
//        return new PageResult<>((long) query.getPage(), (long) query.getSize(), 0L, Collections.emptyList());
//    }
//
//}
