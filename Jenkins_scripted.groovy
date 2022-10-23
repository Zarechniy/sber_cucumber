def mvn = "/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/maven_3.8.6/bin/mvn"

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/Zarechniy/sber_cucumber.git'

                sh "${mvn} clean install"
            }
        }
        stage('Running Test') {
            steps {
                sh "${mvn} test -Dcucumber.filter.tags=@firstTest"
            }
        }
        stage("Allure reports"){
            steps {
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}
