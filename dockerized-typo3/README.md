# Dockerized Typo3

## AWS

- If AWS is a eligible target platform, use it as an EC2 instance from AWS Marketplace
  - "Typo3 CMS 9.x" on t2.micro free tier for testing purposes
  - http://ec2-18-185-139-67.eu-central-1.compute.amazonaws.com/typo3/
    - admin:cbwxsw8a9q5460B1
    - store the produced key, give 0700 right and ssh for handling the ec2 instance by shell: ```ssh -i typo3-test.pem admin@ec2-18-185-139-67.eu-central-1.compute.amazonaws.com```
- instance has local mysql
- clarify requirements for preview-testing, stay simple
- Workspace preparation:
  - Workspace Resource location on EC2 instance: /var/www/typo3_src-9.5.9/typo3/sysext/workspaces/Resources/
  - assume a Resources backup for private and public resources 
  - ```scp -i typo3-test.pem Resources.tgz admin@ec2-18-185-139-67.eu-central-1.compute.amazonaws.com:Resources.tgz```
  - extract, copy, restart

## Docker

- https://www.martin-helmich.de/de/blog/typo3-cms-docker.html

```
docker run -d --name typo3-db \
>     -e MYSQL_ROOT_PASSWORD=yoursupersecretpassword \
>     -e MYSQL_USER=typo3 \
>     -e MYSQL_PASSWORD=yourothersupersecretpassword \
>     -e MYSQL_DATABASE=typo3 \
>   mysql:5.7 \
>     --character-set-server=utf8 \
>     --collation-server=utf8_unicode_ci

docker run -d --name typo3-web \
>     --link typo3-db:db \
>     -p 80:80 \
>   martinhelmich/typo3:9
````

To connect to the database during installation, use the hostname "typo3-db" (instead of localhost or loopback address).

- https://github.com/martin-helmich/docker-typo3
- https://techblog.sitegeist.de/typo3-meetup-hamburg-no-3-entwicklungsumgebung-fuer-typo3-mit-docker-und-xdebug/
- Compose: https://github.com/jforge/compose-typo3

```
version: '2'
services:
  typo3-web:
    image: martinhelmich/typo3:latest
    ports:
      - "80:80"
    depends_on:
      - typo3-db
  typo3-db:
    image: mariadb:latest
    environment:
      - MYSQL_ROOT_PASSWORD=mysecurepassword
      - MYSQL_USER=typo3
      - MYSQL_PASSWORD=mysecurepassword
      - MYSQL_DATABASE=typo3
    command: >
      --character-set-server=utf8
      --collation-server=utf8_unicode_ci
```
