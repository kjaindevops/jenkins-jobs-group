def dslScriptFilename = ''
def gitBranchType = ''
def gitBranchName = ''
def dslScriptDirectory = "functions_terraform"
def dslScripts = "jobs/nonprod/group/${dslScriptDirectory}/**/*.groovy"

if (dslScriptFilename != '') {
    dslScripts = "jobs/nonprod/group/${dslScriptDirectory}/${dslScriptFilename}"
}
def gitBranch

// Set full branch names
switch (gitBranchType) {
    case "main":
        gitBranch = "main"
        break
    case "feature":
        gitBranch = "feature"
        break
    default:
        gitBranch = "${gitBranchType}/${gitBranchName}"
        break
}

pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: gitBranch]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [[$class: 'SubmoduleOption', disableSubmodules: false, parentCredentials: true, recursiveSubmodules: true, reference: '', trackingSubmodules: false]],
                    submoduleCfg: [],
                    userRemoteConfigs: [[url: gitUrl]]
                ])
            }
        }

        stage('Job DSL') {
            steps {
                echo "Refreshing all the job definitions using pattern ${dslScripts} based on branch ${gitBranch}"
                jobDsl targets: dslScripts
            }
        }
    }
}
