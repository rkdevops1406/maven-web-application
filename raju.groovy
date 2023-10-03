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
