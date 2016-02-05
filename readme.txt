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
    :jobManager

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

JobManager
    :itemReader(recordCount)
    :itemProcessor(recordCount)
    :itemWriter (recordCount)
    :statistics
    :jobTerminateFlag (AtomicBoolean)
    ->updateStatistics
    ->checkCompletion
        ->skip termination logic
        ->all completion messages received

JobJournalist
    ->journal to file
    ->update to db (batch update every n seconds)

[JobReadProcess]Message:start_job[jobInstanceId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemReader[fileName] (stream, reactiveObservable)
        ->[publish [JobJournal completion (itemReader,recordCount)]]
    ->itemProcessor[publish [JobJournal no_change | pending_change | error] &&  [JobWriter pending_change [CUD] ]
        ->[publish [JobJournal completion (itemProcessor,recordCount) (itemWrite,recordCount)]]

[JobWrite]Message:change[CUD] [jobInstanceId | recordId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemWrite[publish [JobJournal change[CUD] | error]]

[JobJournal]Message:no_change [jobInstanceId | recordId]
[JobJournal]Message:pending_change[CUD] [jobInstanceId | recordId]
[JobJournal]Message:change[CUD] [jobInstanceId | recordId]
[JobJournal]Message:error [jobInstanceId | recordId]
[JobJournal]Message:completion [workerName(reader|processor|writer) | recordCount]
    ->jobJournalist[ update db stats (batch update every n seconds) | write to file | logErrors]
    ->jobManager [ update internal state ]