JobDefinition
    :jobDefinitionId
    :jobName
    :itemReader     [create message for each record]
    :itemProcessor  [generate checksum | compare checksum | change event ]
    :itemWriter     [change event -> source]

JobCache
    :key    <jobDefinitionId>
    :value  <jobExecutionId>
    :distributed Lock (local and database backed implementations)

JobManager
    :StartJob
        [fileName]
        ->JobCache:AddJob[fileName]
            ->If Already Exists Error
            or
            ->publish [JobReaderProcessor]start_job[jobInstanceId]
    :StopJob
        ->jobExecution::stopJob = 1


JobExecution<JEParam,Integer><P,A>[completion_status | stats ]
    :jobDefinitionId
    :jobExecutionId
    :startTime
    :stopTime
    :status
    :error
    :Map<P,A>
        itemReader          count
        itemProcessor       count
        itemWrite           count
        stopJob             0/1
        deltaNoChange       count
        deltaCreatePending  count
        deltaUpdatePending  count
        deltaDeletePending  count
        deltaCreate         count
        deltaUpdate         count
        deltaDelete         count
    ->checkCompletion
        ->skip termination logic
        ->all completion messages received

JobJournalist
    ->journal to file
    ->update to db (batch update every n seconds)
    ->logErrors for sentinel

[JobReadProcess]Message:start_job[jobInstanceId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemReader[fileName] (stream, reactiveObservable)
        ->[publish [JobJournal completion (itemReader,recordCount)]]
    ->itemProcessor[publish [JobJournal no_change | pending_change | error] &&  [JobWriter pending_change [CUD] ]
        ->[publish [JobJournal completion (itemProcessor,recordCount) ]]
        ->[publish [JobJournal expected (itemWriter,recordCount) ]]

[JobWrite]Message:change[CUD] [jobInstanceId | recordId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemWriter[publish [JobJournal change[CUD] | error]]

[JobJournal]Message:no_change [jobInstanceId | recordId]
[JobJournal]Message:pending_change[CUD] [jobInstanceId | recordId]
[JobJournal]Message:change[CUD] [jobInstanceId | recordId]
[JobJournal]Message:error       [jobInstanceId | recordId]
[JobJournal]Message:completion  [workerName(reader|processor|writer) | recordCount]
[JobJournal]Message:expected    [workerName(writer) | recordCount]
    ->jobJournalist[ update to db (batch update every n seconds) | write to file | logErrors] (multiple threaded io subscriber)
    ->JobExecution [ update internal state ] (single threaded subscriber)