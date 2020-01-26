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
          }
        }

        stage('IT') {
          steps {
            sh './gradlew integrationTest'
          }
        }

      }
    }

    stage('Sonar') {
      steps {
        withSonarQubeEnv(installationName: 'SonarCloud', credentialsId: 'SonarCloud') {
          waitForQualityGate(credentialsId: 'SonarCloudOne', webhookSecretId: 'WebHookSercreId')
        }

      }
    }

  }
  triggers {
    pollSCM('')
  }
}