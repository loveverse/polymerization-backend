package com.loveverse.auth.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.loveverse.auth.entity.ComExcelTemplate;
import com.loveverse.auth.service.ComExcelTemplateService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

/**
 * @author love
 * @since 2025/6/17 16:08
 */
@Tag(name = "excel 通用下载")
@ApiSupport(order = 99)
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/common-excel")
public class ExcelController {

    private final ComExcelTemplateService comExcelTemplateService;


    @Operation(summary = "上传模板")
    @Parameters({
            @Parameter(name = "file", description = "excel 文件", required = true, schema = @Schema(type = "string", format = "binary")),
            @Parameter(name = "key", description = "excel 文件的 key，不能重复", example = "sys_module_excel", required = true),
            @Parameter(name = "remark", description = "描述", example = "用户导入模板")
    })
    @PostMapping("/save-excel")
    public ResponseData<Void> saveExcel(
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "remark", required = false) String remark
    ) throws IOException {
        comExcelTemplateService.saveExcel(file, key, remark);
        return ResponseCode.SUCCESS.getResponse("上传成功");
    }

    @Operation(summary = "下载模板")
    @Parameters(@Parameter(name = "key", description = "excel 文件 key", example = "sys_module_excel"))
    @GetMapping("/download/{key}")
    public void downloadExcelByKey(HttpServletResponse response, @PathVariable(name = "key") String key) throws IOException {
        // 1. 根据key从服务层获取Excel模板信息
        ComExcelTemplate excelTemplate = comExcelTemplateService.getExcel(key);

        // 2. 将Base64编码的Excel数据解码为字节数组
        byte[] excelData = Base64.getDecoder().decode(excelTemplate.getBase64());

        // 3. 设置响应头信息
        response.setContentType("application/x-download");  // 设置内容类型为下载
        String fileName = URLEncoder.encode(excelTemplate.getId(), "UTF-8") + ".xlsx";  // 编码文件名防止乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);  // 设置附件下载

        // 4. 使用try-with-resources确保流自动关闭
        try (OutputStream out = response.getOutputStream()) {
            // 5. 将Excel数据写入响应输出流
            out.write(excelData);
            out.flush();
        }
        // try-with-resources会自动调用out.close()
    }

    @GetMapping("/list")
    @Operation(summary = "查询 excel 模板列表")
    public ResponseData<List<ComExcelTemplate>> getExcelList() {
        List<ComExcelTemplate> excelList = comExcelTemplateService.getExcelList();
        return ResponseCode.SUCCESS.getResponse(excelList);
    }
}
