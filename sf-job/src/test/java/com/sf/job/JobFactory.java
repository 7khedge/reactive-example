package com.sf.job;

import com.sf.job.domain.IdKey;
import com.sf.job.domain.JobName;
import com.sf.job.domain.JsonRecord;
import com.sf.job.testjob.*;
import com.sf.util.file.FileUtil;

import java.util.List;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class JobFactory {


    public static JobDefinition<String, JsonRecord> getTestJob(JobName jobName,
                                                               IdKey idKey,
                                                               String fileName,
                                                               List<JsonRecord> jsonRecordCollector,
                                                               List<String> stringCollector) {
        return JobDefinitionBuilder.<String, JsonRecord>jobDefinition()
                .name(jobName)
                .idKey(idKey)
                .observableItems(new FileObserver().createObserver(FileUtil.getClassPathInputStream(fileName)))
                .itemReader(new JsonRecordItemReader(idKey))
                .itemProcessor(new JsonRecordItemProcessor(jsonRecordCollector))
                .itemWriter(new JsonRecordItemWriter(stringCollector))
                .jobExecutionParameters(DefaultJobExecutionParameters.class)
                .build();
    }
}
