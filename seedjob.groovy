pipelineJob('rnd-grpfnctn-seedjob') {
  description('Pipeline creation test')
  parameters{
    choiceParam('gitUrl', [gitUrl], 'Jenkins git URL')
  }
}
