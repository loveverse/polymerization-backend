# 运行docker compose
旧版：docker compose
新版：docker-compose -f docker-compose.yml up -d


# docker-compose logs 查看服务容器的输出日志。
docker compose logs
# 跟踪日志输出
docker compose logs -f

# 查看日志
docker compose logs web

# 删除所有服务容器(容器)
docker compose down

# 前台启动, 启动项目中的所有服务。
docker compose up

# 后台启动, 启动所有服务并在后台运行。
docker compose up -d

# 停止所有服务。
docker compose stop

# restart
docker compose restart: 重启服务容器。
docker compose restart: 重启工程中所有服务的容器
docker compose restart nginx: 重启工程中指定服务的容器

# start
docker compose start: 启动服务容器。
docker compose start: 启动工程中所有服务的容器
docker compose start nginx: 启动工程中指定服务的容器

# stop
docker compose stop: 停止服务容器。
docker compose stop: 停止工程中所有服务的容器
docker compose stop nginx: 停止工程中指定服务的容器


# 查看项目中所有服务的信息。
docker compose ps

# 查看容器的日志。
docker compose logs

# 在服务镜像的容器中执行命令。
docker compose exec service_name command