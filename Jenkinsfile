pipeline {
    agent any

    triggers {
        pollSCM('')
    }

    stages {
        stage('Gradle Build') {
            steps {
                sh './gradlew clean build'                    
            }            
        }            
    }
}