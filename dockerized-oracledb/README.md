# Dockerized Oracle DB

Follow the white rabbit and this short guide for an oracle 11 instance:

https://github.com/wnameless/docker-oracle-xe-11g

- The image(s) are about 300 MB.
- Skip the SSH port configuration in case of local issues.
- Verify connection with a current oracle driver, e.g. using DataGrip

```
docker pull wnameless/oracle-xe-11g
docker run -d -p 49160:22 -p 49161:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g

// or docker run -d -p 49161:1521 -e ORACLE_ALLOW_REMOTE=true wnameless/oracle-xe-11g
```

## Verify running instance

```
docker ps
CONTAINER ID        IMAGE                                                     COMMAND                  CREATED             STATUS              PORTS                                                                                                                        NAMES
2e6fb8616c4f        wnameless/oracle-xe-11g                                   "/bin/sh -c '/usr/sbi"   7 seconds ago       Up 6 seconds        22/tcp, 8080/tcp, 0.0.0.0:49161->1521/tcp                                                                                    cocky_morse
```

## Connect to database

```
hostname: localhost
port: 49161
sid: xe
username: system
password: oracle
```
 

## Customize DB

Dockerfile:
```
FROM wnameless/oracle-xe-11g 
ADD init.sql /docker-entrypoint-initdb.d/
```
