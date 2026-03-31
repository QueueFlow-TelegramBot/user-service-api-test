pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                withMaven {
                    sh "mvn clean test -Dbase.uri=${USER_SERVICE_URI}"
                }
            }
        }

        // stage('Publish Allure Report') {
        //     steps {
        //         allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        //     }
        // }
    }
}