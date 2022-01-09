@Library('ceiba-jenkins-library') _
pipeline{

    agent {
        label 'Slave_Induccion'
    }


    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK8_Centos'
    }

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    gitTool: 'Default' ,
                    submoduleCfg: [],
                    userRemoteConfigs: [[credentialsId: 'GitHub_andresriascos9',
                        url: 'https://github.com/andresriascos9/Prestamo']]])
                }
        }
        stage('Compilacion y Test Unitarios'){
            steps {
                echo '------------>Test Backend<------------'
                sh 'chmod +x ./microservicio/gradlew'
                sh './gradlew clean'
                sh './gradlew --stacktrace test'
            }
        }

		stage('Static Code Analysis') {
			steps{
				sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:prestamo-andres.riascos',
				sonarName:'CeibaADN-Prestamo(andres.riascos)',
				sonarPathProperties:'./sonar-project.properties')
			}
		}

        stage('Build'){
            steps{
                echo "------------>Compilación backend<------------"
                    dir("${PROJECT_PATH_BACK}"){
                        sh './gradlew build -x test'
                    }
                }
            }
        }

    post {
        failure {
            echo 'This will run only if failed'
            mail(
                to: 'andres.riascos@ceiba.com.co',
                body:"Something is wrong with ${env.BUILD_URL}",
                subject: "Failed Pipeline:${currentBuild.fullDisplayName}"
            )
        }
        success {
            echo 'This will run only if successful'
        }
    }
}