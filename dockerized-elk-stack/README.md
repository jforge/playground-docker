# Dockerized ELK Stack

## References

- [Elasticsearch, Logstash, Kibana](https://www.elastic.co/de/downloads)
- Docker Images + Tutorials:
  - https://github.com/spujadas/elk-docker
  - https://github.com/deviantony/docker-elk
  - http://logz.io/learn/docker-monitoring-elk-stack/

## In 3 minutes...

1. Have a Docker 1.12.1 or higher.
2. Pull sebp/elk from Docker HUB (https://hub.docker.com/r/sebp/elk/)
   `docker pull sebp/elk``
3. Create a Docker-Compose file docker-compose.yml:
   ```
   elk:
     image: sebp/elk
     ports:
       - "5601:5601"
       - "9200:9200"
       - "5044:5044"
    ```
4. If you work on MacOS and get the error message mentioned below, increase the respective parameter
  Possible message: `MacOS max virtual memory areas vm.max_map_count [65530] likely too low, increase to at least [262144]`
  ```
  $ cd ~/Library/Containers/com.docker.docker/Data/database/com.docker.driver.amd64-linux/etc
  $ echo "vm.max_map_count=262144" >> sysctl.conf
  $ git add sysctl.conf
  $ git commit -m 'Change sysctl.conf for elasticsearch'
  ```
5. Start it: `docker-compose up elk`
6. Access Kibana: http://localhost:5601/


## In 10 minutes...

1. See Dockerized ELK Stack#In_3_Minutes
2. Setup FileBeat and configure a source of log files
3. Disable SSL for Logstash
   a. in filebeat.yml
   b. in logstash configuration inside the container
      ```
      docker ps
      docker exec -it 45d  /bin/bash
      vi /etc/logstash/conf.d/02-beats-input.conf  
      ```
4. To test a logstash configuration
   a. Change the above mentioned filebeat input configuration as required AND then restart the container or
   b. call logstash directly inside the container with a stdin configuration:
      ```
      /opt/logstash/bin/logstash -e 'input { stdin { } } filter { kv { prefix => "app_" trim => "\s" trimkey => "\s" } } output { elasticsearch { hosts => ["localhost"] } }'
      ````
5. Push log events using a Filebeat (together with your test app) or a clipboard paste into the above mentioned stdin channel.
6. Create Kibana index pattern
7. Select data in Kibana
 
## Using Filebeat

- Extract a download from https://www.elastic.co/de/products/beats/filebeat
- Change filebeat.yml for using your log source and the logstash container port without ssl
- Push messages by starting the application writing logs monitored by the Filebeat
  ```
  ./filebeat -e -c filebeat.yml
  ```

### Sample changes to Filebeat

```
###################### Filebeat Configuration Example #########################
....
- input_type: log
  paths:
    #- /var/log/*.log
    #- c:\programdata\elasticsearch\logs\*
    - /Users/kpittig/git/azimmer1-logback-sample/log/*.log
....
#-------------------------- Elasticsearch output ------------------------------
#output.elasticsearch:
  # Array of hosts to connect to.
  #hosts: ["localhost:9200"]
 
 
#----------------------------- Logstash output --------------------------------
output.logstash:
  # The Logstash hosts
  hosts: ["localhost:5044"]
 
  # Optional SSL. By default is off.
  # List of root certificates for HTTPS server verifications
  #ssl.certificate_authorities: ["/etc/pki/root/ca.pem"]
  # Certificate for SSL client authentication
  #ssl.certificate: "/etc/pki/client/cert.pem"
...
```
