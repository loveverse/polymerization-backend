package com.loveverse.auth.controller;

import com.loveverse.auth.request.SysRoleReqDTO;
import com.loveverse.auth.response.SysRoleResVO;
import com.loveverse.auth.service.SysRoleService;
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

import java.util.List;

/**
 * @author love
 * @since 2025/5/17 20:00
 */
@Tag(name = "角色管理", description = "系统角色相关操作接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/role")
public class SysRoleController {
    private final SysRoleService sysRoleService;

    @Operation(summary = "新增角色")
    @HasPermission("sys:role:add")
    @PostMapping("/create")
    public ResponseData<Void> createRole(@Validated(ValidGroup.Create.class) @RequestBody SysRoleReqDTO sysRoleDto) {
        sysRoleService.createRole(sysRoleDto);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除角色")
    @HasPermission("sys:role:delete")
    @DeleteMapping("/delete")
    public ResponseData<Void> deleteRoles(@RequestBody Long[] ids) {
        sysRoleService.deleteRoles(ids);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新角色")
    @HasPermission("sys:role:update")
    @PutMapping("/update")
    public ResponseData<Void> updateRole(@Validated(ValidGroup.Update.class) @RequestBody SysRoleReqDTO sysRoleDto) {
        sysRoleService.updateRole(sysRoleDto);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "角色列表")
    @GetMapping("/list")
    public ResponseData<List<SysRoleResVO>> getRoleList() {
        List<SysRoleResVO> roleList = sysRoleService.getRoleList();
        return ResponseCode.SUCCESS.getResponse(roleList);
    }

    @Operation(summary = "根据用户id查询角色列表")
    @GetMapping("/user-role-list/{userId}")
    public ResponseData<List<SysRoleResVO>> findRoleListByUserId(@PathVariable("userId") Long userId) {
        List<SysRoleResVO> roleList = sysRoleService.findRoleListByUserId(userId);
        return ResponseCode.SUCCESS.getResponse(roleList);
    }

}
