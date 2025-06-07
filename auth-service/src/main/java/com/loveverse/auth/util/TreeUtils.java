package com.loveverse.auth.util;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author love
 * @since 2025/6/5 17:11
 */
public class TreeUtils {
    private TreeUtils() {
    }

    public static <T, U> List<T> buildTree(
            List<T> nodes,
            Function<T, U> idExtractor,
            Function<T, U> parentIdExtractor,
            BiConsumer<T, List<T>> childrenSetter,
            U rootParentId
    ) {
        if (CollectionUtils.isEmpty(nodes)) {
            return Collections.emptyList();
        }

        // 1. 预分配子节点映射（parentId -> children list）
        Map<U, List<T>> childrenMap = new HashMap<>(nodes.size());

        // 第一次遍历：构建父节点到子节点的映射
        nodes.forEach(node -> {
            U parentId = parentIdExtractor.apply(node);
            childrenMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(node);
        });

        // 第二次遍历：设置每个节点的子节点
        nodes.forEach(node -> {
            U id = idExtractor.apply(node);
            List<T> children = childrenMap.get(id);
            childrenSetter.accept(node, children != null ? children : Collections.emptyList());
        });

        // 返回根节点列表
        return childrenMap.getOrDefault(rootParentId, Collections.emptyList());
    }
}
