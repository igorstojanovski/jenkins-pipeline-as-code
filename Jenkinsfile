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

        stage('Create image') {
          steps{    
            script {
              def customImage = docker.build("pipeline-as-code-app:${env.BUILD_ID}")              
              customImage.push()
            }
          }
        }
      }
    }

    stage('Smoke') {
          node {
            checkout scm

            docker.image("pipeline-as-code-app:${env.BUILD_ID}").withRun('-p 8384:8384') { c ->
                /* Wait until mysql service is up */
                sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
                /* Run some tests which require MySQL */
                sh 'make check'
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