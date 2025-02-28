package com.loveverse.fast.common.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;
import com.baomidou.mybatisplus.generator.type.ITypeConvertHandler;
import com.baomidou.mybatisplus.generator.type.TypeRegistry;
import com.loveverse.fast.common.entity.BaseEntity;

import java.io.File;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {

    private static final String URL = "jdbc:mysql://192.168.36.10:3306/wallpaper_web_dev?useUnicode=true" +
            "&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String OUTPUT_DIR = "D:\\study\\back-end\\polymerization-backend\\wallpaper-module-web\\src" +
            "\\main" +
            "\\java";
    private static final String XML_OUTPUT_PATH = "D:\\study\\back-end\\polymerization-backend\\wallpaper-module-web\\src" +
            "\\main\\java\\com\\loveverse\\wallpaper\\mapper\\xml";

    public static void main(String[] args) {
        generateCode();
    }

    private static void generateCode() {
        // 创建 XML 输出目录
        createOutputDirectory(XML_OUTPUT_PATH);

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("loveverse") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(OUTPUT_DIR); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> builder.parent("com.loveverse.wallpaper") // 设置父包名
                        .mapper("mapper") // 设置mapper接口包名
                        .entity("model.dto")
                        .xml("xml")  // 设置mapper xml包名
                        .service("service") // 设置service接口包名
                        .serviceImpl("service.impl") // 设置service实现类包名
                        .controller("controller") // 设置controller包名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, XML_OUTPUT_PATH)) // 设置mapperXml生成路径
                )
                .strategyConfig(builder -> {
                    builder.addInclude() // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableLombok(new ClassAnnotationAttributes("@Data", "lombok.Data")) // 开启lombok
                            .controllerBuilder() // 接口策略配置
                            .enableRestStyle(); // 生成@RestController
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板
                .execute();
    }

    private static void createOutputDirectory(String path) {
        File outputDir = new File(path);
        if (!outputDir.exists()) {
            if (outputDir.mkdirs()) {
                System.out.println("目录创建成功: " + path);
            } else {
                System.err.println("目录创建失败: " + path);
            }
        }
    }

}
