pipeline {
  agent any
  stages {
    stage('Deploy') {
      steps {
        sh 'sh /root/demo.sh stop&&sh /root/demo.sh start'
        input 'done'
      }
    }

  }
}
