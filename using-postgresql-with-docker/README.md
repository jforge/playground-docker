# Using Postgresql with Docker

```
docker pull postgres
docker run --name pi_postgres -e POSTGRES_PASSWORD=***** -d postgres
docker ps
docker logs 281
```

## list tables on postgres host

```
PG_HOST==my.database.host.local; PG_PORT=5432; PG_USER=postgres; docker exec -it 281 sh -c "exec psql -h $PG_HOST -p $PG_PORT -U $PG_USER -c '\list'"
```

## list databases on postgres host without interactive password dialog

```
touch ~/.pgpass
chmod 0600 ~/.pgpass
echo "=my.database.host.local:5432:postgres:postgres:*****" >> ~/.pgpass
PGPASS_FILE=~/.pgpass; docker cp $PGPASS_FILE pi_postgres:/root/.pgpass
PG_HOST==my.database.host.locall; PG_PORT=5432; PG_USER=postgres; docker exec -it 281 sh -c "exec psql -h $PG_HOST -p $PG_PORT -U $PG_USER -c '\list'"
```

(add further entries to pgpass in order to avoid password dialogs when working with several postgresql hosts)

## list tables for a databases on postgres host without interactive password dialog

```
PG_HOST==my.database.host.local; PG_PORT=5432; PG_USER=postgres; docker exec -it 281 sh -c "exec psql -h $PG_HOST -p $PG_PORT -U $PG_USER -c '\c <user' -c '\dt <your_database>.*'"
```

## Dump database using docker'ed psql to arbitrary hosts

```
PG_HOST=my.database.host.local; PG_PORT=5432; PG_USER=postgres; docker exec -it 281 sh -c "exec pg_dump <your_database> -h $PG_HOST -p $PG_PORT -U $PG_USER"
```
