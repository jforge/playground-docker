# Docker sample for Groovy scripts

Demonstrates using a Groovy File Watcher from within docker container

## Build

```
docker build --rm -t jforge/watchfile .
```

## Run

```
docker run --rm -it -v ${PWD}:/var/watchfile -w /var/watchfile jforge/watchfile watch_sample.log
```

or

```
docker run --rm -v "$PWD":/home/groovy/scripts -w /home/groovy/scripts groovy groovy WatchFile.groovy watch_sample.log
```

## Reference

- https://hub.docker.com/_/groovy
