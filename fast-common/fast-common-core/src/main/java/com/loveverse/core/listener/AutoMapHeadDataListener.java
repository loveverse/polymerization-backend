package com.loveverse.core.listener;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import cn.idev.excel.util.ListUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author love
 * @since 2025/6/20 17:41
 */
public class AutoMapHeadDataListener<T> extends AnalysisEventListener<T> {
    private static final int BATCH_COUNT = 1000;
    private final List<T> dataList;
    private final Consumer<List<T>> consumer;
    private Map<Integer, String> excelHeadMap;
    private Map<String, Field> classFieldMap;
    private boolean isFirstRow = true;

    public AutoMapHeadDataListener(List<T> dataList) {
        this.dataList = dataList;
        this.consumer = null;
    }

    public AutoMapHeadDataListener(Consumer<List<T>> consumer) {
        this.dataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        this.consumer = consumer;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.excelHeadMap = headMap;
        // 初始化类字段映射
        initClassFieldMap(context);
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        if (isFirstRow && excelHeadMap != null && classFieldMap != null) {
            // 第一行数据可能需要特殊处理
            isFirstRow = false;
        }

        if (consumer != null) {
            dataList.add(data);
            if (dataList.size() >= BATCH_COUNT) {
                consumer.accept(dataList);
                dataList.clear();
            }
        } else {
            dataList.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (consumer != null && !dataList.isEmpty()) {
            consumer.accept(dataList);
        }
    }

    /**
     * 初始化类字段映射
     */
    private void initClassFieldMap(AnalysisContext context) {
        try {
            Class<T> clazz = (Class<T>) context.readSheetHolder().getClazz();
            this.classFieldMap = new HashMap<>();

            // 获取所有字段（包括父类）
            List<Field> fields = getAllFields(clazz);

            for (Field field : fields) {
                ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                if (annotation != null) {
                    String[] values = annotation.value();
                    if (values.length > 0) {
                        // 使用第一个值作为映射key
                        classFieldMap.put(values[0], field);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("初始化类字段映射失败", e);
        }
    }

    /**
     * 获取类及其父类的所有字段
     */
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
