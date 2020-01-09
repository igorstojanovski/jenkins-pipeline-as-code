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
        stage('Tests') {
            steps {
                parallel(
                    UT: {
                        sh './gradlew test'
                    },
                    IT: {
                        sh './gradlew integrationTest'
                    }
                )
            }
        }
    }
}