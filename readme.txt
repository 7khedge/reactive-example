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

[JobReadProcess]Message:start_job[jobInstanceId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemReader[fileName] (stream, reactiveObservable)
    ->itemProcessor[publish [Status no_change | error] |  [JobWriter pending_change [CUD] ]

[JobWrite]Message:change[CUD] [jobInstanceId | recordId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemWrite[publish [Status change[CUD] | error]]

[JobJournal]Message:no_change [jobInstanceId | recordId]
[JobJournal]Message:pending_change[CUD] [jobInstanceId | recordId]
[JobJournal]Message:change[CUD] [jobInstanceId | recordId]
[JobJournal]Message:error [jobInstanceId | recordId]
    ->jobManager[ update db stats (batch update every n seconds) | write to file  | jobCompletion(success | terminated) ]