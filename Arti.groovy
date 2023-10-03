pipeline{
    agent any
    environment {
        NEXUS_VERSION = 'nexus3'  // Or 'nexus2' based on your Nexus version
        NEXUS_REPOSITORY = 'hackthon-release'
        NEXUS_CREDENTIAL_ID = '1399b3b3-e85e-45a3-8cab-f4efd4e623b2'
    }
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

        stage('Deploy to Nexus') {
    steps {
        script {
            // Deploy the war file to Nexus
            sh "pwd"
            sh "ls -l"
            echo "Deploying to Nexus with the following parameters:"
            echo "URL: ${NEXUS_VERSION}:${NEXUS_REPOSITORY}"
            echo "Repository ID: ${NEXUS_CREDENTIAL_ID}"
            echo "File: target/maven-web-application.war"
            echo "POM File: pom.xml"
            sh "mvn deploy:deploy-file -Durl=${NEXUS_VERSION}:${NEXUS_REPOSITORY} -DrepositoryId=nexus -Dfile=target/maven-web-application.war -DpomFile=pom.xml -DrepositoryUsername=admin -DrepositoryPassword=12345"

            // sh "mvn deploy:deploy-file -Durl=${NEXUS_VERSION}:${NEXUS_REPOSITORY} -DrepositoryId=${NEXUS_CREDENTIAL_ID} -Dfile=target/maven-web-application.war -DpomFile=pom.xml"
        }
    }
}

    //      stage('Deploy to Nexus') {
    //         steps {
    //             script {
    //                 // Deploy the war file to Nexus
    //                 sh "pwd"
    //                 sh "ls -l"
    //                 sh "mvn deploy:maven-web-application.war -Durl=${NEXUS_VERSION}:${NEXUS_REPOSITORY} -DrepositoryId=${NEXUS_CREDENTIAL_ID} -Dfile=target/maven-web-application.war -DpomFile=pom.xml"
    // }  }
    //      }

    }
}   
