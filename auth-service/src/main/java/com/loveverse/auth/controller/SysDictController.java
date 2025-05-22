package com.loveverse.auth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.loveverse.auth.request.SysDictDTO;
import com.loveverse.auth.request.SysDictItemDTO;
import com.loveverse.auth.response.SysDictItemVO;
import com.loveverse.auth.response.SysDictVO;
import com.loveverse.auth.service.SysDictItemService;
import com.loveverse.auth.service.SysDictService;
import com.loveverse.core.http.ResponseCode;
import com.loveverse.core.http.ResponseData;
import com.loveverse.core.valid.ValidGroup;
import com.loveverse.security.annotation.HasPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author love
 * @since 2025/5/22 16:29
 */
@Tag(name = "字典管理")
@ApiSupport(order = 99)
@RequiredArgsConstructor
@RestController // 自动将响应转换为 json 格式
@Controller("/v1/dict")
public class SysDictController {
    private final SysDictService sysDictService;
    private final SysDictItemService sysDictItemService;

    @Operation(summary = "新增字典")
    @HasPermission("sys:dict:add")
    @PostMapping("/create")
    public ResponseData<Void> createDict(@Validated(ValidGroup.Create.class) @RequestBody SysDictDTO sysDictDto) {
        sysDictService.createDict(sysDictDto);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除字典")
    @HasPermission("sys:dict:delete")
    @DeleteMapping("/delete")
    public ResponseData<Void> deleteDict(@RequestParam String id) {
        sysDictService.deleteDict(id);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新字典")
    @HasPermission("sys:dict:update")
    @PutMapping("/update")
    public ResponseData<Void> updateDict(@Validated(ValidGroup.Update.class) @RequestBody SysDictDTO sysDictDto) {
        sysDictService.updateDict(sysDictDto);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "字典列表")
    @GetMapping("/list")
    public ResponseData<List<SysDictVO>> getDictList() {
        List<SysDictVO> dictList = sysDictService.getDictList();
        return ResponseCode.SUCCESS.getResponse(dictList);
    }

    @Operation(summary = "新增字典项")
    @HasPermission("sys:dict:add")
    @PostMapping("/detail/create")
    public ResponseData<Void> createDictItem(@Validated(ValidGroup.Create.class) @RequestBody SysDictItemDTO sysDictItemDTO) {
        sysDictItemService.createDictItem(sysDictItemDTO);
        return ResponseCode.SUCCESS.getResponse("添加成功");
    }

    @Operation(summary = "删除字典项")
    @HasPermission("sys:dict:delete")
    @DeleteMapping("/delete")
    public ResponseData<Void> deleteDictItem(@RequestParam String id) {
        sysDictItemService.deleteDictItem(id);
        return ResponseCode.SUCCESS.getResponse("删除成功");
    }

    @Operation(summary = "更新字典项")
    @HasPermission("sys:dict:update")
    @PutMapping("/update")
    public ResponseData<Void> updateDictItem(@Validated(ValidGroup.Update.class) @RequestBody SysDictItemDTO sysDictItemDTO) {
        sysDictItemService.updateDictItem(sysDictItemDTO);
        return ResponseCode.SUCCESS.getResponse("编辑成功");
    }

    @Operation(summary = "字典项列表")
    @GetMapping("/list")
    public ResponseData<List<SysDictItemVO>> getDictItemList() {
        List<SysDictItemVO> dictItemList = sysDictItemService.getDictItemList();
        return ResponseCode.SUCCESS.getResponse(dictItemList);
    }

}
