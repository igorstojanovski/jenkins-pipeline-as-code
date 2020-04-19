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
        SCANNER_HOME = tool 'SonarQubeScanner'
        SONAR_TOKEN = credentials('SonarCloudOne')
        ORGANIZATION = "igorstojanovski-github"
        PROJECT_NAME = "igorstojanovski_jenkins-pipeline-as-code"
      }
      steps {
        withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {
            sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.organization=$ORGANIZATION \
            -Dsonar.java.binaries=build/classes/java/ \
            -Dsonar.projectKey=$PROJECT_NAME \
            -Dsonar.sources=.'''
        }
      }
    }

    stage("Quality Gate") {
      steps {
        timeout(time: 1, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
      }
    }

  }
  triggers {
    pollSCM('')
  }
}