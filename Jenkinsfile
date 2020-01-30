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

    stage('Smoke') {
        agent {
            node {            
                docker.image('openjdk:11-stretch').withRun('-p 3306:3306') { c ->
                    sh 'java --vesrsion'
                }
            }
        }
        steps {
            sh './gradlew smokeTest'
        }
    }

  }
  triggers {
    pollSCM('')
  }
}