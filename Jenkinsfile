pipeline {
    agent any

  triggers {
    pollSCM('')
  }

    stages {
        stage('Gradle Build') {
            sh './gradlew clean build'
        }            
    }
}