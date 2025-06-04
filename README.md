## 测试环境

- 使用电脑主机安装vm ware和vagrant，安装centos7和ubuntu版本
- 下载vagrant:`https://developer.hashicorp.com/vagrant/install?product_intent=vagrant` 下载社区版，AMD64(
  代表64位操作系统)
  ，I686(32位操作系统)，根据系统架构下载，查看系统命令 `wmic os get osarchitecture`
- 使用vm ware 需要下载 `vagrant-vmware-utility` 插件和 `vagrant plugin install vagrant-vmware-desktop` 支持。
- 下载系统镜像资源，官方镜像： `https://portal.cloud.hashicorp.com/vagrant/discover` ;
- 中科大镜像： `https://mirrors.tuna.tsinghua.edu.cn/`
- 因为使用的是vm ware，所以选择不同虚拟化技术，vmware对应的是 `VMwareFusion` 所以镜像链接使用
- `https://mirrors.ustc.edu.cn/centos-cloud/centos/7/vagrant/x86_64/images/CentOS-7-x86_64-Vagrant-2004_01.VMwareFusion.box`
- ubuntu镜像： `https://portal.cloud.hashicorp.com/vagrant/discover/generic/ubuntu1804`
- 1.将镜像下载到本地安装
- `vagrant box add [box名称] [本地镜像文件路径]`:路径不能有中文
- `vagrant box list`:查看是否安装成功
- 使用 `vagrant init centos7:box名字` 初始化
- 使用 Window + R cmd打开用户默认位置，使用 `vagrant init` 生成默认配置 `Vagrantfile`
- vagrant 在不同目录启动会创建多个独立的vm实例，Vagrant 的 box 是一个基础镜像模板，而每次 vagrant up 会根据这个模板创建一个独立的
  VirtualBox VM 实例
- private_network:只允许宿主机和虚拟机通信，外部设备无法访问(如同一局域网内的手机或其他电脑)。
- public_network:直接连接到宿主机的物理网络（局域网）,虚拟机可以被局域网内的其他设备访问
- 使用 `ipconfig` 查看 以太网适配器 `VMware Network Adapter VMnet1` 我的是 ` 192.168.36.1` 只要前面两段相同，后面两段随意填写固定ip
- 如果没有正确分配ip，可以看看系统服务 `VMware DHCP Service` 和 `VMware NAT Service` 是否启动
- 连接ssh后更换yum源
- `cp /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.bak`
- 更换阿里云： `curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo`
  - ```shell
    sudo yum clean all
    sudo yum makecache
    yum repolist
    yum install dnf
    sudo dnf -y install dnf-plugins-core
    设置存储库(使用阿里镜像源)
    dnf config-manager --add-repo https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    设置dokcer服务开机自启，同时会立即启动
    systemctl enable --now docker
    创建目录
    mkdir -p /etc/docker
    sudo tee /etc/docker/daemon.json <<-'EOF'
    {
      "registry-mirrors": [
          "https://docker.m.daocloud.io",
          "https://noohub.ru",
          "https://huecker.io",
          "https://dockerhub.timeweb.cloud",
          "https://0c105db5188026850f80c001def654a0.mirror.swr.myhuaweicloud.com",
          "https://5tqw56kt.mirror.aliyuncs.com",
          "https://docker.1panel.live",
          "http://mirrors.ustc.edu.cn/",
          "http://mirror.azure.cn/",
          "https://hub.rat.dev/",
          "https://docker.ckyl.me/",
          "https://docker.chenby.cn",
          "https://docker.hpcloud.cloud",
          "https://docker.m.daocloud.io"
      ]
    }
    EOF
    systemctl daemon-reload
    systemctl restart docker
    docker pull docker.1ms.run/nginx:latest nginx:latest
- ```
  
# 查看Spring Cloud Alibaba版本
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

# 聚合多应用

## 网关（30000）

## oss存储（30100）

## 认证授权（30200）

## 壁纸网站（30300）

## docker安装nginx

1. `docker pull nginx:latest`:拉取最新版本nginx镜像
2. 在系统中创建挂载的配置文件 `mkdir -p /home/nginx/{conf,logs,html}`或`mkdir -p ~/nginx/{conf,logs,html}`,root用户~
   为root，普通用户一般为home

## ubuntu首次进入使用sudo passwd 设置root密码

## 生成文件注释
Editor -> File and Code Templates
```java
/**
 * @author ${USER}
 * @since ${DATE} ${TIME}
 */
```


## spring security
使用spring security+Redis实现身份认证和权限鉴定【你们项目中怎么使用spring security来实现认证和授权的?】
1. 客户端提交用户名和密码给登录接口
2. 登录接口获取得到用户名和密码，封装成 authentication 对象
  自定义登录接口:登录接口获取得到用户名和密码，封装成Authentication对象
  调用ProviderManager的方法进行认证，如果认证通过生成jwt
  把用户信息(登录用户信息和权限信息)存入redis中
3. 调用AuthenticationManager里面的authenticate()方法进行身份认证
4. 调用DaoAuthenticationProvider里面的authenticate()力法进行身份认证
5. 调用自定义UserDetailsService接口的实现类的loadUserByUsername()方法查询用户相关信息 
  5.1 调用Mapper层根据用户名查询对应的用户信息及其权限信息
  5.2 把用户信息及其权限信息封装成UserDetails对象白定义类实现UserDetailsService接口:
    重写loadUserByUsername(), 在这个方法中去查询数据库，获取用户信息和用户权限信息
6. 返回UserDetails对象
7. 通过PasswordEncoder对比UserDetails对象和Authentication对象中的密码
8. 如果密码正确，把UserDetails对象里面的权限设置到authentication对象(用户信息和权限信息)
9. 返回authentication对象
10. 如果认证通过，使用userid生成一个jwt，把jwt令牌响应给前端，然后把登录成功的用户对象放到Redis，key是用户id，value是用户对象
11. 前端发送请求都携带Token
12. 定义正jwt认证过滤器，获取Token，解析Token，得到userid，根据userId查询Redis得到用户相关信息，把用户相关信息放到securityContext上下文对象中

关注点:2、5、12

# spring security 学习
https://www.cnblogs.com/lvlaotou/p/18114025#_caption_0