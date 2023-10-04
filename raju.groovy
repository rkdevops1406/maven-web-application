node{
    stage("Code"){
        
         git credentialsId: 'gitcred', url: 'https://github.com/rkdevops1406/maven-web-application-old.git'
        
        //  git"https://github.com/rkdevops1406/maven-web-application-old.git"
    }
}

node{
    stage("Build"){
        def mavenHome = tool name: "maven", type: "maven"
        def mavenCMD = "${mavenHome}/bin/mvn"
        sh "${mavenCMD} clean package"
    }
}

node {
    stage("Test") {
        // Wrap the SonarQube environment setup within withSonarQubeEnv
        withSonarQubeEnv('mysonar') {
            def mavenHome = tool name: "maven", type: "maven"
            def mavenCMD = "${mavenHome}/bin/mvn"
            sh "${mavenCMD} sonar:sonar"
        }
    }
}
node {
    stage("Deploy") {
                sshagent(['410fba6f-5bac-492a-9cc1-902f1598c80f']) {
                sh 'scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/Jenkins-Project/target/maven-web-application.war  ec2-user@18.119.125.167:/opt/apache-tomcat-9.0.80/webapps' 
                }
                
    }
}
    



    
