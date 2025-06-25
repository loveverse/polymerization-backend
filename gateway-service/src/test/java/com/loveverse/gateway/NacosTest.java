package com.loveverse.gateway;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

/**
 * @author love
 * @since 2025/4/7
 */
@SpringBootTest
public class NacosTest {

    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", "192.168.37.20:8848");
        properties.put("username", "nacos");
        properties.put("password", "nacos");
        NamingService namingService = NamingFactory.createNamingService(properties);
        System.out.println("Nacos server status: " + namingService.getServerStatus());
    }

}
