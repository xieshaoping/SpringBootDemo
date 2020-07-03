pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install -Dmaven.test.skip=true'
        mail(subject: '11', body: '11', charset: 'utf-8', from: '987319557@qq.com', to: '987319557@qq.com')
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
        sh 'mv target/demo-0.0.1-SNAPSHOT.jar /root/file/demo.jar'
        sh 'sh /root/demo.sh stop&&sh /root/demo.sh start&&tail /root/demo.log'
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