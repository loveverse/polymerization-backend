package com.loveverse.auth.controller;

import com.loveverse.auth.request.SysMenuDTO;
import com.loveverse.auth.response.SysMenuVO;
import com.loveverse.auth.service.SysMenuService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.core.valid.ValidGroup;
import com.loveverse.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author love
 * @since 2025/6/4 18:18
 */
@Tag(name = "菜单管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/system/menu")
public class SysMenuController {
    private final SysMenuService sysMenuService;

    @Operation(summary = "新增菜单")
    @HasPermission("sys:menu:add")
    @PostMapping("/create")
    public ResponseData<Void> createMenu(@Validated(ValidGroup.Create.class) @RequestBody SysMenuDTO sysMenuDTO) {
        sysMenuService.createMenu(sysMenuDTO);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除菜单")
    @HasPermission("sys:menu:delete")
    @DeleteMapping("/delete/{id}")
    public ResponseData<Void> deleteMenu(@PathVariable("id") Long id, @RequestParam("moduleId") Long moduleId) {
        sysMenuService.deleteMenu(id, moduleId);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新菜单")
    @HasPermission("sys:menu:update")
    @PutMapping("/update")
    public ResponseData<Void> updateMenu(@Validated(ValidGroup.Update.class) @RequestBody SysMenuDTO sysMenuDTO) {
        sysMenuService.updateMenu(sysMenuDTO);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "菜单列表")
    @GetMapping("/list")
    public ResponseData<List<SysMenuVO>> getMenuList() {
        List<SysMenuVO> moduleList = sysMenuService.getMenuList();
        return ResponseCode.SUCCESS.getResponse(moduleList);
    }

    @Operation(summary = "根据模块id获取菜单列表")
    @Parameters(@Parameter(name = "moduleId", description = "模块id", example = "zt"))
    @GetMapping("/menu_tree_by_module/{moduleId}")
    public ResponseData<List<SysMenuVO>> getMenuTreeByModuleId(@PathVariable("moduleId") Long moduleId) {
        List<SysMenuVO> sysMenuVOS = sysMenuService.getMenuTreeByModuleId(moduleId);

        return ResponseCode.SUCCESS.getResponse(sysMenuVOS);
    }
}
