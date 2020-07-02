pipeline {
  agent any
  stages {
    stage('Deploy - Staging') {
      steps {
        sh 'sh /root/demo.sh stop&&sh /root/demo.sh start'
      }
    }

  }
  post {
    always {
      echo 'One way or another, already finished!'
    }

    success {
      echo 'I succeeeded!'
    }

    unstable {
      echo 'I am unstable :/'
    }

    failure {
      echo 'I failed :('
    }

    changed {
      echo 'Things were different before...'
    }

  }
}