pipeline{
    agent any
    stages{
        stage("code"){
            steps{
            //    git branch: 'main', credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application.git'
               git credentialsId: 'ceadcc92-006e-4df4-a5a6-c552f1dc2e1a', url: 'https://github.com/rkdevops1406/maven-web-application-old.git'
            }
        }
        stage("Build"){
            steps{
                
                sh 'mvn clean package'
                               
            }
        }
        stage("Deploy to tomcat"){
            steps{
                sshagent(['a8ae6ec2-5567-425d-a1ee-442cd325710b']) {
                sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Pipeline-1/target/maven-web-application.war  ec2-user@18.222.11.106:/opt/apache-tomcat-9.0.80/webapps'   
    
                }
            }
        }
    }  
}   