pipeline {
  agent any
  stages {
    stage('Gradle Build') {
      steps {
        sh './gradlew clean build'
      }
    }

    stage('Tests') {
      parallel {
        stage('UT') {
          steps {
            sh './gradlew test'
          }
        }
        stage('IT') {
          steps {
            sh './gradlew integrationTest'
          }
        }
      }
    }

    stage('SonarCloud') {
      environment {
        scannerHome = tool 'SonarQubeScanner'
      }
      steps {
        withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {
            sh "${scannerHome}/bin/sonar-scanner"
        }
      }
    }

  }
  triggers {
    pollSCM('')
  }
}