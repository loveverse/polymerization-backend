FROM ubuntu:latest
LABEL authors="love"
# 设置工作目录
WORKDIR /auth-service

ARG JAR_FILE=target/auth-service.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["top", "-b"]