pipeline {
  agent any
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