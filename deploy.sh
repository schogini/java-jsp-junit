#!/bin/sh
# IMAGE BUILD_ID PORT SERVICE_NAME

qq=$(docker service ls|grep -c tmp-svc)

service_name=tmp-svc
[ $# -gt 3 ] && service_name=$4 

port=8080
[ $# -gt 2 ] && port=$3 

if [ $qq -gt 0 ]; then
	echo "Doing a Rolling Update.. $1 "
	docker service update --env-add WEB=$2 --image $1 $service_name
else
	echo "Deploying Application.. service-name=$service_name port=$port build=$2 image=$1"

	docker service create --name $service_name --replicas 2 --publish $port:8080 --env WEB=$2 $1
fi

