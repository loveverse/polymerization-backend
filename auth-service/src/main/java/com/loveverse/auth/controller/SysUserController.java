package com.loveverse.auth.controller;

import com.loveverse.auth.request.SysUserDTO;
import com.loveverse.auth.request.SysUserPageDTO;
import com.loveverse.auth.response.SysUserVO;
import com.loveverse.auth.service.SysUserService;
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
 * @since 2025/5/19 17:56
 */
@Tag(name = "用户管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/system/user")
public class SysUserController {
    private final SysUserService sysUserService;

    @Operation(summary = "新增用户")
    @HasPermission("sys:user:add")
    @PostMapping("/create")
    public ResponseData<Void> createUser(@Validated(ValidGroup.Create.class) @RequestBody SysUserDTO sysUserDTO) {
        sysUserService.createUser(sysUserDTO);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除用户")
    @HasPermission("sys:user:delete")
    @PostMapping("/delete")
    public ResponseData<Void> deleteUsers(@RequestBody List<Long> ids) {
        sysUserService.deleteUsers(ids);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新用户")
    @HasPermission("sys:user:update")
    @PutMapping("/update")
    public ResponseData<Void> updateUser(@Validated(ValidGroup.Update.class) @RequestBody SysUserDTO sysUserDTO) {
        sysUserService.updateUser(sysUserDTO);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "用户列表")
    @GetMapping("/list")
    public ResponseData<List<SysUserVO>> queryUserList() {
        List<SysUserVO> userList = sysUserService.queryUserList();
        return ResponseCode.SUCCESS.getResponse(userList);
    }

    @Operation(summary = "用户列表")
    @GetMapping("/page")
    public ResponseData<PageResult<SysUserVO>> getUserPage(@Valid @ParameterObject SysUserPageDTO sysUserPageDTO) {
        PageResult<SysUserVO> userPage = sysUserService.getUserPage(sysUserPageDTO);
        return ResponseCode.SUCCESS.getResponse(userPage);
    }
}
