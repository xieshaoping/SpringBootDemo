pipeline {
  agent any
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