def dslScriptFilename = ''
def gitBranchType = ''
def gitBranchName = ''
def dslScriptDirectory = "functions_terraform"
def dslScripts = "jobs/nonprod/group/${dslScriptDirectory}/**/*.groovy"

if(dslScriptFilename != '') {
    dslScripts = "jobs/nonprod/group/${dslScriptDirectory}/${dslScriptFilename}"
}
def gitBranch;

// set full branch names
switch(gitBranchType) {
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

node {
    stage ('Checkout'){
        deleteDir()
        checkout([$class: 'GitSCM', branches: [[name: gitBranch]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SubmoduleOption', disableSubmodules: false, parentCredentials: true, recursiveSubmodules: true, reference: '', trackingSubmodules: false]], submodulecfg: [], userRemoteConfigs: [[url: gitUrl]]])
    }

    stage ('Job DSL'){
        echo "Refresshing all the job definition using pattern ${dslScripts} based on branch ${gitBranch}"
        jobDsl targets: dslScripts
    }
}
