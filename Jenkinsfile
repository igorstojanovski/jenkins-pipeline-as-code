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

    stage('SonarCloud') {
      steps {
        agent {
          docker {
             image 'sonarsource/sonar-scanner-cli'
          }
        }
        withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {
          waitForQualityGate(credentialsId: 'SonarCloudOne', abortPipeline: true)
        }

      }
    }

  }
  triggers {
    pollSCM('')
  }
}