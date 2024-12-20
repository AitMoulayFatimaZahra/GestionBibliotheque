pipeline {
    agent any
    tools {
            maven 'Maven' // Nom donné dans la configuration des outils Maven
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/AitMoulayFatimaZahra/GestionBibliotheque.git'
            }
        }
        
        stage('Build') {
             steps {
                echo 'Compilation du projet avec Maven...'
                 bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                echo 'Exécution des tests unitaires...'
                bat 'mvn test'
            }
        }
        stage('Package') {
                    steps {
                        echo "Packaging du projet en fichier JAR ..."
                        bat 'mvn package'
                    }
        }
        stage('Quality Analysis') {
            steps {
                echo 'Analyse de la qualite du code avec SonarQube...'
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn sonar:sonar'
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
        always {
                    echo 'Pipeline terminé.'
                    junit '/target/surefire-reports/*.xml'
                }

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
