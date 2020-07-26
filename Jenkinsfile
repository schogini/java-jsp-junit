pipeline {
  agent {
    node {
      label 'master'
    }

  }
  
  parameters {
    string(name: 'JENKINSDIR', defaultValue: '/Users/sree/vms/docker/jenkins/jenkins_home', description: 'The project path in the host machine')
    string(name: 'MVNCACHE', defaultValue: '/Users/sree/vms/docker/jenkins/2019-feb-1/.m2', description: 'Maven repository cache in the host machine')
    string(name: 'DOCKER_U', defaultValue: 'schogini', description: 'Docker Hib Username')
  }
    
  stages {

      stage('Clone') {
        steps {
          git url: 'https://github.com/schogini/java-war-junit-add.git'
          sh '''sed -i \"s/BUILD_ID/${BUILD_ID}/\" src/main/webapp/index.jsp'''
          sh '''sed -i \"s/BUILD_ID/${BUILD_ID}/\" kubernetes/deploy-svc.yml'''
          sh '''sed -i \"s/BUILD_ID/${BUILD_ID}/\" ansible/docker-image-creation.yml'''
          sh '''sed -i \"s/BUILD_ID/${BUILD_ID}/\" ansible/sample.yml'''
          
        }
      }
      
           stage('Unit Tests') {
        steps {
         sh "sudo docker run --rm -i -v ${params.MVNCACHE}:/root/.m2 -v ${params.JENKINSDIR}/workspace/${JOB_BASE_NAME}:/project -w /project maven:3.3-jdk-8 mvn clean test"
        }
      }
      stage('Build Java App') {
        steps {
          sh "sudo docker run --rm -i -v ${params.MVNCACHE}:/root/.m2 -v ${params.JENKINSDIR}/workspace/${JOB_BASE_NAME}:/project -w /project maven:3.3-jdk-8 mvn package"
       }
      }

      stage('Docker Image Context') {
        steps {
          sh 'cp -f target/SampleJava1.war tomcat/webapp.war'
        }
      }      

      stage('Build-Push Docker Image - Ansible') {
        steps {
          # sh "ansible-playbook ansible/docker-image-creation.yml"
          sh "ansible-playbook -i localhost ansible/sample.yml"
        }
      }

      stage('Build Docker Image') {
        steps {
          sh "sudo docker build -t schogini/tc:${BUILD_ID} tomcat"
        }
      }
      stage('Push Docker Image') {
        steps {
          sh "cat  ~/.docker-pass|sudo docker login -u=${params.DOCKER_U} --password-stdin"
          sh "sudo docker push schogini/tc:${BUILD_ID}"
        }
      }
      stage('Deploy to Docker Tomcat') {
        steps {
      sh "sudo docker inspect my-tcc2 >/dev/null 2>&1 && sudo docker rm -f my-tcc2 || echo No container to remove. Proceed."
      sh "sudo docker run -id -p 7081:8080 --name my-tcc2 docker.io/schogini/tc:${BUILD_ID}"
        }
      }      
 
      stage('Deploy to Kubernetes') {
        steps {
      sh "sudo kubectl apply -f kubernetes/deploy-svc.yml"
      #sh "sudo kubectl set image deploy/webapp-demo-deploy webapp=docker.io/schogini/docker.io/schogini/tc:${BUILD_ID}"
        }
      }

    }
}
