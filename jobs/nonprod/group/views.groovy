// Prefix for creating top level from different directory then it is located.
// Creating view as VIEWNAME will create it under directory where you put your code

def viewsPrefixAcr = '/aws/group/groupfunctions-terraform/'
def viewsPrefixFms = 'aws/group/groupfunctions-trf/'

// Adding job name to commonJobNames will add it to all listViews we have,
// i.e in all lists you can have additionally seedjob included
def commonJobNames = ['admin-seedjob']

def viewsStructure =
[
    [
        name: viewsPrefixAcr = 'RND',
        branchStructure: false,
        apps: [
            ALL:
            [
                pattern: "fms-.*"
            ]
        ]
    ]
]

def branchBuildStructure = [
    [
        name: 'ALL',
        patternSuffix: ''
    ],
    [
        name: 'TERRAFORM FROM FEATURE',
        patternSuffix: 'feature-build'
    ],
    [
        name: 'TERRAFORM FROM DEVELOP',
        patternSuffix: 'develop-build'
    ]
]

for (def topView: viewsStructure) {
    nestedView(topView.name) {
        views {
            for (def appLevel: topView.apps) {
                nestedView (appLevel.key) {
                    views {
                        def listViewsStructure = (topView.branchStructure)? branchBuildStructure : branchBuildStructure.subList(0,1)
                        for(def listViews: listViewsStructure) {
                            listViews(listViews.name) {
                                jobs {
                                    commonJobNames.each {jobName ->
                                       name(jobName)
                                     }
                                     if(appLevel.value.containsKey('customJobs')) {
                                        appLevel.value.customJobs.each {custom ->
                                            name(custom)
                                        }
                                     }
                                     if(listViews.patternSuffix != '') {
                                        regex("(?i)" +appLevel.value.pattern + '(' + listViews.patternSuffix + '|build-trigger-custom)')
                                     } else {
                                        regex("(?i)" +appLevel.value.pattern + listViews.patternSuffix)
                                     }
                                }
                                columns {
                                    status()
                                    weather()
                                    name()
                                    lastSuccess()
                                    lastFailure()
                                    lastDuration()
                                    buildButton()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}