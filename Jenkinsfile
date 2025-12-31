pipeline {
  agent any

  environment {
    DOCKER_USER = "your_dockerhub"
  }

  stages {

    stage('Checkout') {
      steps {
        git 'https://github.com/yourname/bookstore-microservices.git'
      }
    }

    stage('Build Maven Services') {
      steps {
        sh 'mvn -f services/book-service/pom.xml clean package'
        sh 'mvn -f services/author-service/pom.xml clean package'
      }
    }

    stage('Build Docker Images') {
      steps {
        sh '''
        docker build -t $DOCKER_USER/book-service:1.0 services/book-service
        docker build -t $DOCKER_USER/author-service:1.0 services/author-service
        docker build -t $DOCKER_USER/frontend:1.0 frontend-ui
        '''
      }
    }

    stage('Push Images') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-creds',
          usernameVariable: 'USER',
          passwordVariable: 'PASS'
        )]) {
          sh '''
          echo $PASS | docker login -u $USER --password-stdin
          docker push $DOCKER_USER/book-service:1.0
          docker push $DOCKER_USER/author-service:1.0
          docker push $DOCKER_USER/frontend:1.0
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        sh 'kubectl apply -f k8s-manifests/'
      }
    }
  }
}
