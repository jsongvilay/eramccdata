node {
    stage('Checkout') {
        git branch: 'main', url: 'https://github.com/jsongvilay/eramccdata/'
    }

    stage('Gradle build - DataService') {
        sh 'gradle clean build'
    }
    
    stage('Gradle bootJar-Package - DataService') {
        sh 'gradle bootJar'
    }
    
    stage ('Containerize the app-docker build - DataService') {
        sh 'docker build --rm -t project-data:v1.0 .'
    }
    
    stage ("Inspect the docker image - DataService"){
        sh "docker images project-data:v1.0"
        sh "docker inspect project-data:v1.0"
    }
    
    stage ("Run Docker container instance - DataService"){
        sh "docker run -d --rm --name project-data -p 8080:8080 project-data:v1.0"
     }
    
    stage('User Acceptance Test - DataService') {
	
	  def response= input message: 'Is this build good to go?',
	   parameters: [choice(choices: 'Yes\nNo', 
	   description: '', name: 'Pass')]
	
	  if(response=="Yes") {
	    stage('Deploy to Kubenetes cluster - DataService') {
	      sh "kubectl create deployment project-data --image=project-data:v1.0"
	      sh "kubectl expose deployment project-data --type=LoadBalancer --port=8080"
	    }
	  }
    }
    
    stage("Production Deployment View") {
        sh "kubectl get deployments"
        sh "kubectl get pods"
        sh "kubectl get services"
    }
}

