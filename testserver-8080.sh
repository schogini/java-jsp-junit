#!/bin/sh

docker network create tmp-net > log.txt 2>&1
docker container rm -f tmp-web >> log.txt 2>&1
docker run --rm -d --name tmp-web --net tmp-net -e WEB=$1 $2 >> log.txt 2>&1

# docker ps
sleep 20
