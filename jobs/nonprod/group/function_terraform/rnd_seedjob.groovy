def gitUrl = "https://github.com/kjaindevops/jenkins-jobs-group.git"
def jobDslScript = "pipelines/group/functions_terraform/fms_pipelines_rnd_seedjob.groovy"

pipelineJob('group-function-terraform-rnd-seedjob') {
    description('Pipeline job for validating new groovy jobs')
    logRotator(30,100)
    parameters{
        choiceParam('gitUrl', [gitUrl], 'Jenkins Git URL for Terraform repo')
        choiceParam('gitBranchType', ['feature', 'main'])
        stringParam('gitBranchName', '', 'Leave blank for Main branch')
        choiceParam('dslScriptDirectory',
                [
                    'functions_terraform'
                ], 'select directory where your pipeline definition is located: <br>\
                    it will pick up all the definition macthing patter: jobs/nonprod/group[dslScriptDirectory]/**/*.groovy<br>\
                    Job will reload pipelines using definition from selected branch.')
        stringParam('dslScriptfilename', '', '<b>OPTIONAL</b> If you want to refresh just one job select directory and provide file name including extension, leave blank for whole directory <br>\
                    jobs/nonprod/group/[dslScriptfile]/[dslScriptfilename]')
    }
    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
            sandbox()
        }
    }
}

pipelineJob('aws/group/groupfunctions-terraform/admin-seedjob') {
    description('Pipeline job for validating new groovy jobs')
    logRotator(30,100)
    parameters{
        choiceParam('gitUrl', [gitUrl], 'Jenkins Git URL for Terraform repo')
        choiceParam('gitBranchType', ['feature', 'main'])
        stringParam('gitBranchName', '', 'Leave blank for Main branch')
        choiceParam('dslScriptDirectory',
                [
                    'functions_terraform'
                ], 'select directory where your pipeline definition is located: <br>\
                    it will pick up all the definition macthing patter: jobs/nonprod/group[dslScriptDirectory]/**/*.groovy<br>\
                    Job will reload pipelines using definition from selected branch.')
        stringParam('dslScriptfilename', '', '<b>OPTIONAL</b> If you want to refresh just one job select directory and provide file name including extension, leave blank for whole directory <br>\
                    jobs/nonprod/group/[dslScriptfile]/[dslScriptfilename]')
    }
    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
            sandbox()
        }
    }
}

pipeline('aws/group/groupfunctions-trf/admin-seedjob') {
    description('Pipeline job for validating new groovy jobs')
    logRotator(30,100)
    parameters{
        choiceParam('gitUrl', [gitUrl], 'Jenkins Git URL for Terraform repo')
        choiceParam('gitBranchType', ['feature', 'main'])
        stringParam('gitBranchName', '', 'Leave blank for Main branch')
        choiceParam('dslScriptDirectory',
                [
                    'functions_terraform'
                ], 'select directory where your pipeline definition is located: <br>\
                    it will pick up all the definition macthing patter: jobs/nonprod/group[dslScriptDirectory]/**/*.groovy<br>\
                    Job will reload pipelines using definition from selected branch.')
        stringParam('dslScriptfilename', '', '<b>OPTIONAL</b> If you want to refresh just one job select directory and provide file name including extension, leave blank for whole directory <br>\
                    jobs/nonprod/group/[dslScriptfile]/[dslScriptfilename]')
    }
    definition {
        cps {
            script(readFileFromWorkspace(jobDslScript))
            sandbox()
        }
    }
}