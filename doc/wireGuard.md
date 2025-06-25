# ubuntu22 安装 wireGuard
- 安装：apt-get install wireguard
- 生成密钥：wg genkey | tee /etc/wireguard/privatekey | wg pubkey | tee /etc/wireguard/publickey
- 开启 windows 端口转发（需重启电脑） Set-ItemProperty -Path "HKLM:\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters" -Name "IPEnableRouter" -Value 1
- 查看所有转发：netsh interface portproxy show all
- 删除转发：netsh interface portproxy delete v4tov4 listenaddress=127.0.0.1 listenport=8005

# linux 查看转发规则
- 查看所有转发：iptables -t nat -L PREROUTING -n --line-numbers
- 删除转发：iptables -t nat -D PREROUTING 1
注：centos7 内核版本不够无法安装wireGuard，需升级内核