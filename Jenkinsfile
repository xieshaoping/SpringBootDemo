pipeline {
  agent {
    docker {
      image 'arron.harbor.com/seczone/maven:3.6.3'
      args '-v /root/maven:/usr/share/maven --restart always --name maven'
    }

  }
  stages {
    stage('Deploy - Production') {
      steps {
        echo 'test'
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