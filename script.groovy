def buildJar() {

    echo "Building the application..."
                    sh 'mvn package'



}

def buildImage() {

    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId:'docker-gub-maven-rep', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD' )])
        {
            sh '''
            echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
            docker build -t java-maven-app:2.0 .
            docker tag java-maven-app:2.0 $DOCKER_USERNAME/java-maven-app:2.0
            docker push $DOCKER_USERNAME/java-maven-app:2.0

            '''
         }


}


def deployApp() {

     echo "Deploying the application..."
}




return this