# Dockerized Cassandra

## References

- http://cassandra.apache.org/
- http://blog.rdx.com/cassandra-and-relational-database-schema-comparison-query-vs-relationship-modeling
- For Java Developers:
  - https://academy.datastax.com/resources/getting-started-apache-cassandra-and-java-part-i

## Dockerized Cassandra Cluster

Follow the white rabbit and this short guide for a Apache Cassandra instance:

https://github.com/pokle/cassandra

The image(s) are about 60 MB.

``` 
docker pull poklet/cassandra
docker run --detach --name cassone poklet/cassandra

// connect to cassandra
docker run -it --rm --net container:cassone poklet/cassandra cqlsh
```

```
// test cassandra
CREATE KEYSPACE test_keyspace WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1}; 
USE test_keyspace; 
CREATE TABLE test_table ( id text, test_value text, PRIMARY KEY (id) ); 
INSERT INTO test_table (id, test_value) VALUES ('1', 'one'); 
INSERT INTO test_table (id, test_value) VALUES ('2', 'two'); 
INSERT INTO test_table (id, test_value) VALUES ('3', 'three'); 
SELECT * FROM test_table;
``` 

```
// proceed with a <n>-node cluster as described in the github resource
./scripts/run.sh 3
docker pull poklet/opscenter
docker run -d --name opscenter -p 8888:8888 poklet/opscenter
// follow the instruction on the same resource to install, run and configure opscenter
```
