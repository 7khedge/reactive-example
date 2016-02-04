JobSpecification
    :jobSpecificationId
    :name
    :itemReader     [create message for each record]
    :itemProcessor  [generate checksum | compare checksum | change event ]
    :itemWriter     [change event -> source]

JobInstance
    :type <JobSpecification:name>
    :jobInstanceId

JobCache
    :key <JobSpecification>
    :value <JobInstance>
    :synchronized
    :database backed

Event:StartJob
    [fileName]
    ->JobCache:AddJob[fileName]
        ->If Already Exists Error
        or
        ->publish start_job[jobInstanceId]

Message:start_job[jobInstanceId]
    ->JobCache::GetJob[jobInstanceId]
    ->itemReader[fileName] (reactiveObservable)
    ->itemProcessor

