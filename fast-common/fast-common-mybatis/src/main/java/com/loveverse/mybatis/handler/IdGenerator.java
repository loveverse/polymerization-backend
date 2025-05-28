package com.loveverse.mybatis.handler;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @author love
 * @since 2025/4/10
 */
public class IdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        return generateId();
    }

    public Long generateId() {
        // 使用实体类名作为业务键，或者提取参数生成业务键
        //String bizKey = entity.getClass().getName();
        // 根据业务键调用分布式ID生成服务
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        // 返回生成的ID值
        return snowflake.nextId();
    }

    //@Resource
    //private SqlSessionFactory sqlSessionFactory;
    //private IdentifierGenerator identifierGenerator;
    //
    //public IdGenerator() {
    //}
    //
    //public Long nextId() {
    //    if (this.identifierGenerator != null) {
    //        return (Long)this.identifierGenerator.nextId((Object)null);
    //    } else {
    //        Configuration configuration = this.sqlSessionFactory.getConfiguration();
    //        this.identifierGenerator = GlobalConfigUtils.getGlobalConfig(configuration).getIdentifierGenerator();
    //        return (Long)this.identifierGenerator.nextId((Object)null);
    //    }
    //}
}
