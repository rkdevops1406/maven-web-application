pipeline{
    agent any
    stages{
        stage("code"){
            steps{
                  git credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application.git'
            }
        }
        
        
stage("Build") {
    steps {
        script {
            def mavenHome = tool name: "maven", type: "maven"
            def mavenCMD = "${mavenHome}/bin/mvn"
            sh "${mavenCMD} clean package"
        }
    }
}

        stage("SonarQubetest") {
    steps {
        withSonarQubeEnv('sonar')
        def mavenHome = tool name: "maven", type: "maven"
        def mavenCMD = "${mavenHome}/bin/mvn"
        sh "${mavenCMD} sonar:sonar"
    }
}
       
                
        stage("Deploy to tomcat"){
            steps{
                sshagent(['70844032-23e8-4e8d-82ca-43e2ce6a40fb']) {
                sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Pipeline-1/target/maven-web-application.war  ec2-user@3.145.199.169:/opt/apache-tomcat-9.0.80/webapps'   
    
                }
            }
        }
         stage("Artifact Upload"){
            steps{
                // nexusArtifactUploader credentialsId: '1399b3b3-e85e-45a3-8cab-f4efd4e623b2', groupId: 'com.mt', nexusUrl: '3.21.241.63:8081/', nexusVersion: 'nexus2', protocol: 'http', repository: 'http://3.21.241.63:8081/#browse/browse:hackthon-release', version: '0.0.1-SNAPSHOT'
                nexusArtifactUploader credentialsId: '1399b3b3-e85e-45a3-8cab-f4efd4e623b2', groupId: 'nexus', nexusUrl: '3.21.241.63:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'http://3.21.241.63:8081/#browse/browse:hackthon-release', version: '0.0.1-SNAPSHOT'
            }
        }  
    }  
}   
