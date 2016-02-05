JobSpecification
    :jobSpecificationId
    :name
    :itemReader     [create message for each record]
    :itemProcessor  [generate checksum | compare checksum | change event ]
    :itemWriter     [change event -> source]
    :jobManager     [record status | terminate job ]

JobInstance
    :type   <JobSpecification:name>
    :jobInstanceId

JobCache
    :key    <JobSpecification>
    :value  <JobInstance>
    :synchronized
    :database backed

Event:StartJob
    [fileName]
    ->JobCache:AddJob[fileName]
        ->If Already Exists Error
        or
        ->publish [JobReaderProcessor]start_job[jobInstanceId]

[JobReaderProcessor]Message:start_job[jobInstanceId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemReader[fileName] (stream, reactiveObservable)
    ->itemProcessor[publish [Status no_change | error] |  [JobWriter pending_change [CUD] ]
[JobWrite]Message:change[CUD] [jobInstanceId | recordId]
    ->itemWrite[publish [Status change[CUD] | error]]

[Status]Message:no_change [jobInstanceId | recordId]
[Status]Message:pending_change[CUD] [jobInstanceId | recordId]
[Status]Message:change[CUD] [jobInstanceId | recordId]
    ->jobManager[ update db stats (batch update every n seconds) | write to file ]
[Status]Error:error [jobInstanceId | recordId]
    ->jobManager[ update db stats (batch update every n seconds) | write to file  | jobCompletion(success | terminated) ]