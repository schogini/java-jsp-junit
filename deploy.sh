#!/bin/sh

qq=$(docker service ls|grep -c tmp-svc)

if [ $qq -gt 0 ]; then
	echo "Doing a Rolling Update.. $1 "
	docker service update --env-add WEB=$2 --image $1 tmp-svc
else
	echo "Deploying Application.. $1"
        port=8080
	[ $# -eq 3 ] && port=$3 

	docker service create --name tmp-svc --replicas 2 --publish $port:8080 --env WEB=$2 $1
fi

