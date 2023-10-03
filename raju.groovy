pipeline{
    agent any
    stages{
        stage("code"){
            steps{
                  git credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application.git'
            }
        }
        
        stage("Build"){
            steps{
                def mavenHome =tool name: "maven" ,type: "maven"
                def mavenCMD ="${mavenHome}/bin/mvn"
                sh "${mavenCMD} clean package"
            }
        }
    }
}  
    
