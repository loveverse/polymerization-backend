package com.loveverse.auth.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loveverse.auth.entity.SysDict;
import com.loveverse.auth.entity.SysDictItem;
import com.loveverse.auth.mapper.SysDictItemMapper;
import com.loveverse.auth.mapper.SysDictMapper;
import com.loveverse.auth.request.SysDictItemDTO;
import com.loveverse.auth.response.DictCollectionVO;
import com.loveverse.auth.response.SysDictItemVO;
import com.loveverse.auth.service.SysDictItemService;
import com.loveverse.core.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author love
 * @since 2025/5/22 17:21
 */
@RequiredArgsConstructor
@Service
public class SysDictItemServiceImpl implements SysDictItemService {
    private final SysDictMapper sysDictMapper;
    private final SysDictItemMapper sysDictItemMapper;

    @Override
    public void createDictItem(SysDictItemDTO sysDictItemDTO) {
        SysDictItem sysDictItem = new SysDictItem();
        BeanUtils.copyProperties(sysDictItemDTO, sysDictItem);
        sysDictItemMapper.insert(sysDictItem);
    }

    @Override
    public void deleteDictItem(Long id) {
        sysDictItemMapper.deleteById(id);
    }

    @Override
    public void updateDictItem(SysDictItemDTO sysDictItemDTO) {
        SysDictItem data = sysDictItemMapper.selectById(sysDictItemDTO.getId());
        if (data == null) {
            throw new BadRequestException("不存在该字典项");
        }
        BeanUtils.copyProperties(sysDictItemDTO, data);
        sysDictItemMapper.updateById(data);
    }


    @Override
    public List<SysDictItemVO> queryDictItemList(Long dictId) {
        List<SysDictItem> sysDictItemList = sysDictItemMapper.selectList(
                Wrappers.<SysDictItem>lambdaQuery().eq(dictId != null, SysDictItem::getDictId, dictId));
        return Optional.ofNullable(sysDictItemList).orElse(Collections.emptyList()).stream().map(item -> {
            SysDictItemVO sysDictItemVO = new SysDictItemVO();
            BeanUtils.copyProperties(item, sysDictItemVO);
            return sysDictItemVO;
        }).sorted(Comparator.comparing(SysDictItemVO::getSortOrder)).collect(Collectors.toList());
    }

    @Override
    public DictCollectionVO queryDictItemsByModuleId(Long moduleId) {
        List<SysDict> sysDictList = sysDictMapper.selectList(
                Wrappers.<SysDict>lambdaQuery().eq(moduleId != null, SysDict::getModuleId, moduleId));
        if (CollectionUtils.isEmpty(sysDictList)) {
            return new DictCollectionVO();
        }
        List<Long> dictIds = sysDictList.stream().map(SysDict::getId).collect(Collectors.toList());
        // 避免查询无效dictId
        List<SysDictItem> sysDictItemList = sysDictItemMapper.selectList(
                Wrappers.<SysDictItem>lambdaQuery().in(SysDictItem::getDictId, dictIds));

        Map<Long, List<SysDictItemVO>> dictItemMap = sysDictItemList.stream().collect(
                Collectors.groupingBy(SysDictItem::getDictId, Collectors.mapping(item -> {
                    SysDictItemVO sysDictItemVO = new SysDictItemVO();
                    BeanUtils.copyProperties(item, sysDictItemVO);
                    return sysDictItemVO;
                }, Collectors.toList())));

        DictCollectionVO dictCollectionVO = new DictCollectionVO();
        Map<String, List<SysDictItemVO>> dictMap = new HashMap<>();
        Map<String, Map<String, Object>> dictKeyMap = new HashMap<>();
        sysDictList.forEach(dict -> {
            List<SysDictItemVO> items = dictItemMap.getOrDefault(dict.getId(), Collections.emptyList());
            dictMap.put(dict.getDictValue(), items);
            Map<String, Object> itemMap = items.stream()
                    .collect(Collectors.toMap(
                            SysDictItemVO::getDictItemValue,
                            SysDictItemVO::getDictItemLabel,
                            (existing, replacement) -> existing)); // 防止出现重复键
            dictKeyMap.put(dict.getDictValue(), itemMap);
        });
        dictCollectionVO.setDictMap(dictMap);
        dictCollectionVO.setDictKeyMap(dictKeyMap);
        return dictCollectionVO;
    }
}
