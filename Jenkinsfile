pipeline {
agent any


environment {
DOCKER_USER = "your-dockerhub-username"
REGISTRY = "docker.io"
}


stages {


stage('Checkout Code') {
steps {
checkout scm
}
}


stage('Build Docker Images') {
steps {
sh '''
docker build -t $DOCKER_USER/frontend:$BUILD_NUMBER frontend/
docker build -t $DOCKER_USER/book-service:$BUILD_NUMBER book-service/
docker build -t $DOCKER_USER/author-service:$BUILD_NUMBER author-service/
'''
}
}


stage('Push Docker Images') {
steps {
withCredentials([string(credentialsId: 'dockerhub-token', variable: 'TOKEN')]) {
sh '''
docker login -u $DOCKER_USER -p $TOKEN
docker push $DOCKER_USER/frontend:$BUILD_NUMBER
docker push $DOCKER_USER/book-service:$BUILD_NUMBER
docker push $DOCKER_USER/author-service:$BUILD_NUMBER
'''
}
}
}


stage('Deploy to Kubernetes') {
steps {
sh '''
kubectl apply -f k8s/namespace.yaml


sed -i "s|image: .*frontend.*|image: $DOCKER_USER/frontend:$BUILD_NUMBER|" k8s/frontend.yaml
sed -i "s|image: .*book-service.*|image: $DOCKER_USER/book-service:$BUILD_NUMBER|" k8s/book.yaml
sed -i "s|image: .*author-service.*|image: $DOCKER_USER/author-service:$BUILD_NUMBER|" k8s/author.yaml


kubectl apply -f k8s/
'''
}
}
}
}
