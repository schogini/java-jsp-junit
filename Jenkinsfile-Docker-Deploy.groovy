pipeline {
    agent any
    
    parameters {
        string(name: 'MVNCACHE', defaultValue: '/Users/sree/vms/docker/jenkins/2019-feb-1/.m2', description: 'Maven repository cache in the host machine')    
        string(name: 'JENKINSDIR', defaultValue: '/Users/sree/vms/docker/jenkins/jenkins_home', description: 'The project path in the host machine')
        
    }
    
    stages {
        stage('Git Clone'){
            steps {
                git url: 'https://github.com/schogini/java-war-junit-add.git'
            }
        }
  
       stage('Build Java App') {
            steps {
              sh "sudo docker run --rm -i -v ${params.MVNCACHE}:/root/.m2 -v ${params.JENKINSDIR}/workspace/${JOB_BASE_NAME}:/project -w /project maven:3.3-jdk-8 mvn package"
           }
       }  

      stage('Deploy to Docker Tomcat') {
          steps {
            sh "sudo docker inspect my-tcc >/dev/null 2>&1 && sudo docker rm -f my-tcc || echo No container to remove. Proceed."
            sh "sudo docker run -d --name my-tcc -p 8123:8080 -v ${params.JENKINSDIR}/workspace/${JOB_BASE_NAME}/target/SampleJava1.war:/usr/local/tomcat/webapps/demo.war tomcat"
          }
      } 

   }
}