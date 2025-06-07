package com.loveverse.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author love
 * @since 2025/6/7 16:16
 */
@Schema
@Data
public class SetRoleMenuDTO {

    @NotNull
    private Long id;

    @NotNull
    @Schema(description = "菜单ids")
    private List<Long> menuIds;
}
