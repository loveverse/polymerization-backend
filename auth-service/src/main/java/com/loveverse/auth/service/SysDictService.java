package com.loveverse.auth.service;

import com.loveverse.auth.request.SysDictDTO;
import com.loveverse.auth.response.SysDictVO;

import java.util.List;

/**
 * @author love
 * @since 2025/5/22 16:31
 */
public interface SysDictService {
    void createDict(SysDictDTO sysDictDto);

    void deleteDict(Long id);

    void updateDict(SysDictDTO sysDictDto);

    List<SysDictVO> getDictList();

}
