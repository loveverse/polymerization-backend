package com.loveverse.auth.controller;

import com.loveverse.auth.entity.SysRoleMenu;
import com.loveverse.auth.request.SetRoleMenuDTO;
import com.loveverse.auth.request.SysRoleDTO;
import com.loveverse.auth.request.SysRolePageDTO;
import com.loveverse.auth.response.SysMenuVO;
import com.loveverse.auth.response.SysRoleVO;
import com.loveverse.auth.service.SysRoleService;
import com.loveverse.core.dto.PageResult;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.core.valid.ValidGroup;
import com.loveverse.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author love
 * @since 2025/5/17 20:00
 */
@Tag(name = "角色管理", description = "系统角色相关操作接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/system/role")
public class SysRoleController {
    private final SysRoleService sysRoleService;

    @Operation(summary = "新增角色")
    @HasPermission("sys:role:add")
    @PostMapping("/create")
    public ResponseData<Void> createRole(@Validated(ValidGroup.Create.class) @RequestBody SysRoleDTO sysRoleDto) {
        sysRoleService.createRole(sysRoleDto);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除角色")
    @HasPermission("sys:role:delete")
    @PostMapping("/delete")
    public ResponseData<Void> deleteRoles(@RequestBody List<Long> ids) {
        sysRoleService.deleteRoles(ids);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新角色")
    @HasPermission("sys:role:update")
    @PutMapping("/update")
    public ResponseData<Void> updateRole(@Validated(ValidGroup.Update.class) @RequestBody SysRoleDTO sysRoleDto) {
        sysRoleService.updateRole(sysRoleDto);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "角色列表")
    @GetMapping("/list")
    public ResponseData<List<SysRoleVO>> getRoleList(@RequestParam("status") Integer status) {
        List<SysRoleVO> roleList = sysRoleService.getRoleList(status);
        return ResponseCode.SUCCESS.getResponse(roleList);
    }

    @Operation(summary = "角色分页列表")
    @GetMapping("/page")
    public ResponseData<PageResult<SysRoleVO>> getRolePage(@Valid @ParameterObject SysRolePageDTO sysRolePageDTO) {
        PageResult<SysRoleVO> rolePage = sysRoleService.getRolePage(sysRolePageDTO);
        return ResponseCode.SUCCESS.getResponse(rolePage);
    }

    @Operation(summary = "根据用户id查询角色列表")
    @GetMapping("/user-role-list/{userId}")
    public ResponseData<List<SysRoleVO>> findRoleListByUserId(@PathVariable("userId") Long userId) {
        List<SysRoleVO> roleList = sysRoleService.findRoleListByUserId(userId);
        return ResponseCode.SUCCESS.getResponse(roleList);
    }

    @Operation(summary = "根据角色id查询角色的菜单权限信息")
    @GetMapping("/menu-tree-by-role/{roleId}")
    public ResponseData<SysRoleVO> getMenuTreeByRoleId(@PathVariable("roleId") Long roleId) {
        SysRoleVO menuVOS = sysRoleService.getMenuTreeByRoleId(roleId);
        return ResponseCode.SUCCESS.getResponse(menuVOS);
    }


    @Operation(summary = "设置角色的菜单权限")
    @PostMapping("/update-role-permissions")
    public ResponseData<Void> updateRolePermissions(@Validated @RequestBody SetRoleMenuDTO RoleMenuDTO) {
        sysRoleService.updateRolePermissions(RoleMenuDTO);
        return ResponseCode.SUCCESS.getResponse("操作成功");
    }

}
