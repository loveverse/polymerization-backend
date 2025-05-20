package com.loveverse.core.dto;

//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.loveverse.core.valid.ValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author love
 * @since 2025/4/3
 */
@Data
@Schema(description = "通用分页查询")
public abstract class PageReqDto {
    // 为什么不使用int， 因为@NotNull对基本类型不生效，int默认值为0，无法使用@NotNull
    // @Min接收Long类型的值，不传时会自动进行隐式转换
    @Min(value = 1L, groups = {ValidGroup.Page.class}, message = "page必须大于等于1")
    @NotNull(groups = {ValidGroup.Page.class}, message = "page不能为空")
    @Schema(description = "页码，默认为1")
    private Integer page = 1;

    @Min(value = 1L, groups = {ValidGroup.Page.class}, message = "size必须大于等于1")
    @Max(value = 1000L, groups = {ValidGroup.Page.class}, message = "size最大为1000")
    @NotNull(groups = {ValidGroup.Page.class}, message = "size不能为空")
    @Schema(description = "每页数量,默认为10，最大为1000")
    private Integer size = 10;

    //@Schema(description = "排序字段", type = "Map<String, Sort.Direction>")
    //private Map<String, Sort.Direction> sort;
    //
    //@Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    //public Pageable pageable() {
    //    Sort pageSort;
    //    if (sort != null && !sort.isEmpty()) {
    //        Sort.Order[] orders = sort.entrySet().stream().map(entry -> new Sort.Order(entry.getValue(), entry.getKey())).toArray(Sort.Order[]::new);
    //        pageSort = Sort.by(orders);
    //    } else {
    //        pageSort = Sort.by(Sort.Order.desc("update_time"), Sort.Order.desc("id"));
    //    }
    //    return PageRequest.of(page - 1, size, pageSort);
    //}
    //
    //// 兼容list page 查询的结果类型
    //public final <T> Page<T> getIPage() {
    //    Pageable pageable = this.pageable();
    //    return PageUtils.convertToMyBatisPlusPage(pageable);
    //}
}
