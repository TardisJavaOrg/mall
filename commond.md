wsl -d Ubuntu-20.04 -u root ip addr add 192.168.50.10/24 broadcast 192.168.50.255 dev eth0 label eth0:1

netsh interface ip add address "vEthernet (WSL)" 192.168.50.11 255.255.255.0