package com.loveverse.auth.request;

import com.loveverse.core.dto.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author love
 * @since 2025/5/26 14:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserPageDTO extends PageParam {

    private String keyword;
}
