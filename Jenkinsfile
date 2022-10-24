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
    
    stage('User Acceptance test - DataService') {
    
    	def response= input message: 'Is this build good to go?',
    		parameters: [choice(choices: 'Yes\nNo', 
    		description: '', name: 'Pass')]
    		
    	if(response=="Yes") {
    		stage('Release - DataService') {
    			sh 'gradle build -x test'
    			sh 'echo DataService is ready to release'
    		}
    	}
	}
}

