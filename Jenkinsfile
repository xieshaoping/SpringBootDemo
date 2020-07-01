pipeline {
  agent {
    docker {
      image 'arron.harbor.com/seczone/maven:3.6.3'
      args '-v /root/maven:/usr/share/maven --restart always --name maven'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
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
        sshagent(credentials: ['nZdmSb1Apt0UoTVi1beWUaL9Qbc71PFc']) {
          sh 'ssh -o StrictHostKeyChecking=no root@192.168.110.20 uptime'
          sh "scp ${env.WORKSPACE}/target/seczone-ssdlc-portal.war root@192.168.110.20:/root"
        }

      }
    }

    stage('Sanity check') {
      steps {
        input 'Does the staging environment look ok?'
      }
    }

    stage('Deploy - Production') {
      steps {
        echo './deploy production'
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
