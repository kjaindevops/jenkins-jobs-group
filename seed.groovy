pipelineJob('rnd-grpfnctn-seedjob') {
  description('Pipeline creation test')
  parameters{
    choiceParam('gitBranchType', ['feature', 'main', 'develop'])
  }
}
