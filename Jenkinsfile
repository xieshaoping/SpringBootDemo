pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        input 'pause!'
        sh 'mvn clean install -Dmaven.test.skip=true'
      }
    }

    stage('Test') {
      steps {
        echo 'Build successed, result URL: ${env.BUILD_URL}'
        echo 'Launching Auto Security Testing...'
      }
    }

    stage('Deploy - Staging') {
      steps {
        echo 'Security Testing completed...'
        echo "Workspace: ${env.WORKSPACE}"
        echo 'Deploying to staging enviroment....'
        sh 'nohup java -jar target/demo-0.0.1-SNAPSHOT.jar --htttpPort=8086>log.log&'
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