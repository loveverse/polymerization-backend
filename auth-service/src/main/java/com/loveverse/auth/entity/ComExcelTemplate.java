package com.loveverse.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.loveverse.mybatis.entity.BaseTimeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author love
 * @since 2025/6/17 16:12
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("com_excel_template")
@Table(value = "com_excel_template", comment = "excel 通用模板")
public class ComExcelTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @Column(comment = "id", isKey = true, isNull = false)
    private String id;

    @Column(comment = "文件 base64 编码", type = MySqlTypeConstant.LONGTEXT, isNull = false)
    private String base64;

    @Column(comment = "备注（描述）", length = 100, defaultValue = "", isNull = false)
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    @Column(comment = "创建时间", type = MySqlTypeConstant.DATETIME, isNull = false)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Column(comment = "更新时间", type = MySqlTypeConstant.DATETIME, isNull = false)
    private LocalDateTime updateTime;
}
