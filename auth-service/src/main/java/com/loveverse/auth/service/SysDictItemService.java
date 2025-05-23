package com.loveverse.auth.service;

import com.loveverse.auth.request.SysDictItemDTO;
import com.loveverse.auth.response.DictCollectionVO;
import com.loveverse.auth.response.SysDictItemVO;

import java.util.List;

/**
 * @author love
 * @since 2025/5/22 17:21
 */
public interface SysDictItemService {
    void createDictItem(SysDictItemDTO sysDictDto);

    void deleteDictItem(String id);

    void updateDictItem(SysDictItemDTO sysDictDto);

    List<SysDictItemVO> queryDictItemList(String dictId);

    DictCollectionVO queryDictItemsByModuleId(String moduleId);
}
