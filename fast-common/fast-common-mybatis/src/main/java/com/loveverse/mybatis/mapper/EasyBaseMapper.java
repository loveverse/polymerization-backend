//package com.loveverse.mybatis.mapper;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.core.mapper.Mapper;
//import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Collection;
//
///**
// * @author love
// * @since 2025/5/27 16:08
// */
//public interface EasyBaseMapper<T> extends BaseMapper<T> {
//    /**
//     * 批量插入（自动填充生效版）
//     */
//
//    default boolean insertBatch(Collection<T> entityList, MetaObjectHandler metaObjectHandler) {
//        if (CollectionUtils.isEmpty(entityList)) {
//            return false;
//        }
//        // 执行自动填充
//        entityList.forEach(entity -> {
//            MetaObject metaObject = SystemMetaObject.forObject(entity);
//            metaObjectHandler.insertFill(metaObject);
//        });
//
//        // 执行批量插入（使用 MyBatis-Plus 的 insertBatchSomeColumn）
//        return SqlHelper.retBool(((Mapper<T>) this).insertBatchSomeColumn(entityList));
//    }
//
//}
