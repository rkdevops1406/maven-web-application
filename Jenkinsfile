pipeline{
    agent any
    stages{
        stage("code"){
            steps{
               git branch: 'main', credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application.git'
            }
        }
        stage("Build"){
            steps{
                
                sh 'mvn clean package'
                               
            }
        }
        stage("Deploy to tomcat"){
            steps{
                sshagent(['7383c439-b0d7-4543-ab62-946b0c613f9a']) {
                sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Pipeline-2/target/maven-web-application.war  ec2-user@3.135.239.39:/opt/apache-tomcat-9.0.80/webapps'   
    
                }
            }
        }
    }  
}   