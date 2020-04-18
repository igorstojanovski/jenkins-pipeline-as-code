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
      steps {
        withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {

            def scannerHome = tool 'SonarScanner 4.0';
            sh "${scannerHome}/bin/sonar-scanner"
        }
      }
    }

  }
  triggers {
    pollSCM('')
  }
}