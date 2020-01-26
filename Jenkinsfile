pipeline {
  agent any
  stages {
    stage('Gradle Build') {
      steps {
        sh './gradlew clean build'
        script {
          docker.build("pipeline-as-code-app:${env.BUILD_ID}")
        }
      }
    }

    stage('Tests') {
      parallel {
        stage('UT') {
          agent {
            docker {
              image 'openjdk:11-stretch'
            }

          }
          steps {
            sh './gradlew test'
            withSonarQubeEnv(installationName: 'SonarQubeOne', credentialsId: 'SonarQubeCOne')
          }
        }

        stage('IT') {
          steps {
            sh './gradlew integrationTest'
          }
        }

      }
    }

  }
  triggers {
    pollSCM('')
  }
}