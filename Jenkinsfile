def update = ""
def gate = ""
pipeline{
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 1, unit: "HOURS")
    }
    parameters {
        string(name: 'DEPENDENCY', defaultValue: '', description: 'dependency to update')
        string(name: 'UPDATE_VERSION', defaultValue: '', description: 'version of the dependency')
        choice(name: 'TYPE', choices: ['none', 'snapshot', 'release'], description: 'type of version to update')
    }
    environment {
        MVN_SETTING_PROVIDER = "3ec57b41-efe6-4628-a6c7-8be5f1c26d77"
        COMPONENT = "projet-isa-devops-20-team-b-20-web-service-component"
        VERSION = """${sh(
                            returnStdout: true,
                            script: 'mvn help:evaluate -Dexpression=versions.${DEPENDENCY} -q -DforceStdout'
                        )}"""
    }
    stages {
        stage('New Version To check') {
            when {
                allOf {
                    expression { params.UPDATE_VERSION != VERSION }
                }
            }
            stages {
                stage("Snapshot revision") {
                    when {
                        allOf {
                            expression { params.DEPENDENCY != '' }
                            expression { params.TYPE == 'snapshot' }
                        }
                    }
                    steps {
                        sh "mvn versions:use-latest-versions -DallowSnapshots=true -DprocessParent=false -Dincludes=fr.unice.polytech.isadevops.dronedelivery:${params.DEPENDENCY}"
                        script {
                            update = "\n - Component need fix before update : ${params.DEPENDENCY}\n\t${params.UPDATE_VERSION} -> ${VERSION}"
                        }
                    }
                }
                stage("Compile") {
                    steps {
                        configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                            echo "Compile module"
                            sh "mvn -s $MAVEN_SETTINGS clean compile"
                        }
                    }
                }
                stage("Tests") {
                    steps {
                        configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                            echo "Unit tests module"
                            sh "mvn -s $MAVEN_SETTINGS test"
                        }
                    }
                }
                stage('Mutations') {
                    steps {
                        echo 'PiTest Mutation'
                        sh 'mvn org.pitest:pitest-maven:mutationCoverage'
                    }
                }
                stage("Deploy") {
                    when { expression { BRANCH_NAME ==~ /(master|develop)/ }}
                    steps {
                        configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                                echo "Deployment into artifactory"
                                sh "mvn -s $MAVEN_SETTINGS deploy"
                        }
                    }
                }
                stage('Sonarqube') {
                    steps {
                        withSonarQubeEnv('Sonarqube_env') {
                            echo 'Sonar Analysis'
                            sh 'mvn package sonar:sonar -Dsonar.pitest.mode=reuseReport'
                        }
                    }
                }
                stage('Quality Gate') {
                    steps {
                        catchError(buildResult: "SUCCESS", stageResult: "FAILURE") {
                            timeout(time: 1, unit: "HOURS") {
                                waitForQualityGate true
                            }
                        }
                    }
                    post{
                        success {
                            script {
                                gate = "\n - Quality gate was successful"
                            }
                        }
                        failure {
                            script {
                                gate = "\n - Quality gate was failed"
                            }
                        }
                    }
                }
                stage('Notify with new version') {
                    when {
                        allOf {
                            expression { params.DEPENDENCY != '' }
                            expression { params.TYPE == 'snapshot' }
                            expression { params.UPDATE_VERSION != VERSION }
                        }
                    }
                    steps {
                        script {
                            update = "\n - Component can be update : ${params.DEPENDENCY}\n\t${VERSION} -> ${params.UPDATE_VERSION}"
                        }
                    }
                }
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/**/*', fingerprint: true
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('No Update') {
            when {
                allOf {
                    expression { params.UPDATE_VERSION == VERSION }
                }
            }
            steps {
                script {
                    update = "\n - Component up to date : ${params.DEPENDENCY}\n\t${params.UPDATE_VERSION}"
                }
            }
        }
    }
    post{
        always {
            echo '======== pipeline archived ========'
        }
        success {
            slackSend(
            channel: 'projet-isa-devops-ci',
            notifyCommitters: true,
            failOnError: true,
            color: 'good',
            token: env.SLACK_TOKEN,
            message: 'Job: ' + env.JOB_NAME + ' with buildnumber ' + env.BUILD_NUMBER + ' was successful' + update + gate,
            baseUrl: env.SLACK_WEBHOOK)
            echo "======== pipeline executed successfully ========"
            sh 'mvn versions:commit'
        }
        failure {
            slackSend(
            channel: 'projet-isa-devops-ci',
            notifyCommitters: true,
            failOnError: true,
            color: 'danger',
            token: env.SLACK_TOKEN,
            message: 'Job: ' + env.JOB_NAME + ' with buildnumber ' + env.BUILD_NUMBER + ' was failed' + update + gate,
            baseUrl: env.SLACK_WEBHOOK)
            echo "======== pipeline execution failed========"
            sh 'mvn versions:revert'
        }
    }
}
