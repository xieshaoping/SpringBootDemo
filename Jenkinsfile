pipeline {
  agent any
  stages {
    stage('deploy') {
      steps {
        sh 'sh /root/demo.sh stop&&sh /root/demo.sh start'
      }
    }

  }
}