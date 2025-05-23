package com.loveverse.auth.response;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author love
 * @since 2025/5/23 14:14
 */
@Data
public class DictCollectionVO {

    private Map<String, List<SysDictItemVO>> dictMap;
    private Map<String, Map<Object, String>> dictKeyMap;
}
