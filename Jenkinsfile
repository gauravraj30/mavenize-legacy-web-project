
node {
    docker.image('maven:3-alpine').inside {
        stage('Build') {
            echo 'Building...'
            sh 'mvn clean package'
        }
        stage('Test') {
            echo 'Testing...'
        }
        stage('Deploy') {
            echo 'Deployng'
        }
    }
}