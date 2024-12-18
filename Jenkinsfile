pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AitMoulayFatimaZahra/GestionBibliotheque.git'
            }
        }
        stage('Build') {
            script {
            def mvnReturnCode = bat(script: '"${MAVEN_HOME}/bin/mvn" clean compile', returnStatus: true)
            echo "Maven build finished with return code: ${mvnReturnCode}"
            if (mvnReturnCode != 0) {
                error "Maven build failed with return code: ${mvnReturnCode}"
            }
        }
        }
        stage('Test') {
            steps {
                bat '${MAVEN_HOME}/bin/mvn test'
            }
        }
        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat '${MAVEN_HOME}/bin/mvn sonar:sonar'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }
    post {
        success {
            emailext to: 'fatimazahra200115@gmail.com',
                subject: 'Build Success',
                body: 'Le build a été complété avec succès.'
        }
        failure {
            emailext to: 'fatimazahra200115@gmail.com',
                subject: 'Build Failed',
                body: 'Le build a échoué.'
        }
    }
}
