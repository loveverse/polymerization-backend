package com.loveverse.auth.controller;

import com.loveverse.auth.request.SysModuleDTO;
import com.loveverse.auth.response.SysModuleVO;
import com.loveverse.auth.service.SysModuleService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.core.valid.ValidGroup;
import com.loveverse.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author love
 * @since 2025/5/23 15:39
 */
@Tag(name = "模块管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/system/module")
public class SysModuleController {
    private final SysModuleService sysModuleService;

    @Operation(summary = "新增模块")
    //@HasPermission("sys:module:add")
    @PostMapping("/create")
    public ResponseData<Void> createModule(@Validated(ValidGroup.Create.class) @RequestBody SysModuleDTO sysModuleReqDTO) {
        sysModuleService.createModule(sysModuleReqDTO);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除模块")
    @HasPermission("sys:module:delete")
    @DeleteMapping("/delete")
    public ResponseData<Void> deleteModules(@RequestParam("id") Long id) {
        sysModuleService.deleteModule(id);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新模块")
    @HasPermission("sys:module:update")
    @PutMapping("/update")
    public ResponseData<Void> updateModule(@Validated(ValidGroup.Update.class) @RequestBody SysModuleDTO sysModuleReqDTO) {
        sysModuleService.updateModule(sysModuleReqDTO);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "模块列表")
    @GetMapping("/list")
    public ResponseData<List<SysModuleVO>> getModuleList() {
        List<SysModuleVO> moduleList = sysModuleService.getModuleList();
        return ResponseCode.SUCCESS.getResponse(moduleList);
    }
}
