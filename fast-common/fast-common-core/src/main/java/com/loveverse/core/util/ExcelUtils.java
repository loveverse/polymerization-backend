package com.loveverse.core.util;

import cn.idev.excel.ExcelReader;
import cn.idev.excel.FastExcel;
import cn.idev.excel.read.metadata.ReadSheet;
import com.loveverse.core.listener.AutoMapHeadDataListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author love
 * @since 2025/6/20 17:07
 */
public class ExcelUtils {
    /**
     * 读取Excel数据（自动使用@ExcelProperty映射）
     *
     * @param inputStream Excel文件输入流
     * @param head        表头类
     * @param sheetNo     工作表序号（从0开始）
     * @param <T>         泛型
     * @return 数据列表
     */
    public static <T> List<T> readExcel(InputStream inputStream, Class<T> head, int sheetNo) {
        return readExcel(inputStream, head, sheetNo, 1);
    }

    /**
     * 读取Excel数据（自动使用@ExcelProperty映射）
     *
     * @param inputStream   Excel文件输入流
     * @param head          表头类
     * @param sheetNo       工作表序号（从0开始）
     * @param headRowNumber 表头行数
     * @param <T>           泛型
     * @return 数据列表
     */
    public static <T> List<T> readExcel(InputStream inputStream, Class<T> head, int sheetNo,
                                        int headRowNumber) {
        List<T> dataList = new ArrayList<>();

        ExcelReader reader = null;
        try {
            reader = FastExcel.read(inputStream)
                    .head(head)
                    .registerReadListener(new AutoMapHeadDataListener<>(dataList))
                    .build();

            ReadSheet readSheet = FastExcel.readSheet(sheetNo)
                    .headRowNumber(headRowNumber)
                    .build();

            reader.read(readSheet);
        } finally {
            if (reader != null) {
                reader.finish();
            }
        }

        return dataList;
    }

    /**
     * 流式读取Excel数据（自动使用@ExcelProperty映射）
     *
     * @param inputStream Excel文件输入流
     * @param head        表头类
     * @param sheetNo     工作表序号（从0开始）
     * @param consumer    每批数据的消费者
     * @param <T>         泛型
     */
    public static <T> void readExcelByChunk(InputStream inputStream, Class<T> head,
                                            int sheetNo, Consumer<List<T>> consumer) {
        readExcelByChunk(inputStream, head, sheetNo, 1, consumer);
    }

    /**
     * 流式读取Excel数据（自动使用@ExcelProperty映射）
     *
     * @param inputStream   Excel文件输入流
     * @param head          表头类
     * @param sheetNo       工作表序号（从0开始）
     * @param headRowNumber 表头行数
     * @param consumer      每批数据的消费者
     * @param <T>           泛型
     */
    public static <T> void readExcelByChunk(InputStream inputStream, Class<T> head, int sheetNo,
                                            int headRowNumber, Consumer<List<T>> consumer) {
        ExcelReader reader = null;
        try {
            reader = FastExcel.read(inputStream)
                    .head(head)
                    .registerReadListener(new AutoMapHeadDataListener<>(consumer))
                    .build();

            ReadSheet readSheet = FastExcel.readSheet(sheetNo)
                    .headRowNumber(headRowNumber)
                    .build();

            reader.read(readSheet);
        } finally {
            if (reader != null) {
                reader.finish();
            }
        }
    }
}
