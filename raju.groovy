pipeline{
    agent any
    stages{
        stage("code"){
            steps{
            //    git branch: 'main', credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application.git'
               git credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application.git'
            }
        }
        stage("Build"){
            steps{
                
                sh 'mvn clean package'
                               
            }
        }
        stage("Deploy to tomcat"){
            steps{
                sshagent(['70844032-23e8-4e8d-82ca-43e2ce6a40fb']) {
                sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Pipeline-1/target/maven-web-application.war  ec2-user@3.145.199.169:/opt/apache-tomcat-9.0.80/webapps'   
    
                }
            }
        }
    }  
}   
