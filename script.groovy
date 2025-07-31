def buildJar() {
    echo "Building the application..."
    sh 'mvn package'
}

def buildImage() {
    echo "Building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-gub-maven-rep', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """
            echo "\$DOCKER_PASSWORD" | docker login -u "\$DOCKER_USERNAME" --password-stdin
            docker build -t java-maven-app:${env.IMAGE_NAME} .
            docker tag java-maven-app:${env.IMAGE_NAME} \$DOCKER_USERNAME/java-maven-app:${env.IMAGE_NAME}
            docker push \$DOCKER_USERNAME/java-maven-app:${env.IMAGE_NAME}
        """
    }
}

def deployApp() {
    echo "Deploying the application..."
}

return this
