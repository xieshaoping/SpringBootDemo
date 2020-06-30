pipeline {
  agent {
    docker {
      image 'maven'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('start') {
      steps {
        echo 'start'
      }
    }

    stage('test') {
      steps {
        sh '''mvn clean
&&
mvn test
&&
mvn package
&&
ls'''
      }
    }

  }
}