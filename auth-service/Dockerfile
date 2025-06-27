# 源镜像：docker.io/maven:3.8.6-openjdk-8
FROM swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/maven:3.8.6-openjdk-8 AS builder
WORKDIR /javaserver
# 1. 仅复制当前服务的POM文件
COPY pom.xml .
COPY auth-service/pom.xml auth-service/pom.xml
# 执行mvn clean install -N初始化项目（-N表示不递归子模块）
RUN mvn clean install -N
# 2. 下载依赖（使用本地仓库缓存）
RUN mvn dependency:go-offline -B -pl auth-service
# 3. 仅复制当前服务源码（假设fast-common已安装到本地仓库）
COPY auth-service/src auth-service/src
# 4. 编译跳过测试且指定环境变量
RUN mvn clean package -DskipTests -pl auth-service -am -P prod

# 源镜像：docker.io/openjdk:8-jdk-slim
FROM swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/openjdk:8-jdk-slim-linuxarm64
WORKDIR /javaserver
RUN mkdir -p config && mkdir -p jar
# 复制构建结果（使用通配符匹配版本号）
COPY --from=builder /javaserver/auth-service/target/auth-service-*.jar jar/
COPY auth-service/src/main/resources/application-*.yaml ./config/
EXPOSE 30200
# 启动命令（带JVM参数）
CMD sh -c "java -jar /javaserver/jar/$(ls /javaserver/jar | grep '.jar$' | head -n 1) --spring.config.additional-location=file:/javaserver/config/"