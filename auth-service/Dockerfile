# 构建阶段 - 只构建auth-service
FROM maven:3.8.9-openjdk-8-slim AS builder
WORKDIR /app
# 1. 仅复制当前服务的POM文件
COPY pom.xml .
COPY auth-service/pom.xml auth-service/pom.xml
# 执行mvn clean install -N初始化项目（-N表示不递归子模块）
RUN mvn clean install -N
# 2. 下载依赖（使用本地仓库缓存）
RUN mvn dependency:go-offline -B -pl auth-service
# 3. 仅复制当前服务源码（假设fast-common已安装到本地仓库）
COPY auth-service/src auth-service/src
# 4. 构建时跳过fast-common的编译
RUN mvn clean package -DskipTests -pl auth-service -am

# 运行时阶段
FROM openjdk:8-jre-slim
WORKDIR /app
# 复制构建结果（使用通配符匹配版本号）
COPY --from=builder /app/auth-service/target/auth-service-*.jar app.jar
# 复制配置文件（按需）
RUN mkdir -p config
COPY auth-service/src/main/resources/application*.yaml ./config/
EXPOSE 30200
# 启动命令（带JVM参数）
CMD ["java", "-jar", "app.jar", "--spring.config.additional-location=file:/app/config/"]