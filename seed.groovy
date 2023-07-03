def gitUrl = "https://github.com/kjaindevops/jenkins-jobs-group.git"

pipelineJob('rnd-grpfnctn-seedjob') {
  description('Pipeline creation test')
  parameters{
    choiceParam('gitUrl', [gitUrl], 'Jenkins git URL')
  }
}
