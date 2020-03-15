pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
                sh 'export APP_PATH=/opt/servers/jenkins/workspace/vibot-voice && docker-compose -f docker-compose.build.yml up --build --exit-code-from vibot-voice-build'
            }
        }
        stage ('Deploy') {
            steps {
                sh 'docker-compose up -d --build'
            }
        }
    }
    post {
        always {
            junit 'build/test-results/**/*.xml'
        }
        success {
            script {
                sh 'curl -s ' + '"http://172.17.0.1:50000/notification/${env.JOB_NAME}%20success"'
            }
        }
        unstable {
            script {
                sh 'curl -s ' + '"http://172.17.0.1:50000/notification/${env.JOB_NAME}%20unstable"'
            }
        }
        failure {
            script {
                sh 'curl -s ' + '"http://172.17.0.1:50000/notification/${env.JOB_NAME}%20failure"'
            }
        }
    }
}
