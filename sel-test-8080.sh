#!/bin/sh
# BUILD-ID AND IMAGE NAME

# LAUNCH A TOMCAT INSTANCE FOR TETING

# docker network create tmp-net > log.txt 2>&1
# docker container rm -f tmp-web >> log.txt 2>&1

# docker run --rm -d --name tmp-web --net tmp-net -e WEB=$1 $2 >> log.txt 2>&1

# docker ps
# sleep 20


# [ $# -ne 3 ] && echo 'NEED REPO, TEST SITE URL AND RESULTS' && exit 127 

# git clone $1  > /dev/null 2>&1
# fol=$(echo $1| sed 's/^.*\///'|sed 's/\.git$//')
# cd $fol

export CLASSPATH=".:/var/jar_repo/htmlunit-driver-2.42.0-jar-with-dependencies.jar:/var/jar_repo/selenium-server-standalone-3.141.59.jar:/var/jar_repo/testng-6.0.1.jar"

# GIT=$(echo $2|sed 's/\//\\\//g')
# URL=$(echo $3|sed 's/\//\\\//g')

sed -i "s/TEST_URL/http:\/\/tmp-web:8080\/sample\//" SelTest.java
sed -i "s/TEST_RESULT/App Version: $1/" SelTest.java

cat SelTest.java 

javac SelTest.java

java SelTest

output=`java SelTest | grep -c SUCCESS`

# CLEAR
# docker container rm -f tmp-web >> log.txt 2>&1

if [ $output -gt 0 ] 
then
  echo 'SUCCESS'
  exit 0
else
  echo 'FAILED'
  exit 127
fi
