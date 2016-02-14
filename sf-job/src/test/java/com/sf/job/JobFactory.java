package com.sf.job;

import com.sf.job.testjob.*;
import com.sf.util.file.FileUtil;

/**
 * Created by adityasofat on 10/02/2016.
 */
public class JobFactory {


    public static JobDefinition<String,JsonRecord,DefaultJobExecutionParameters> getTestJob(String jobName, IdKey idKey, String fileName){
        return JobDefinitionBuilder.<String,JsonRecord,DefaultJobExecutionParameters>jobDefinition()
                .name(jobName)
                .idKey(idKey)
                .connectableObservable(new FileObserver().createObserver(FileUtil.getClassPathInputStream(fileName)))
                .itemReader(new JsonRecordItemReader(idKey))
                .itemProcessor(new JsonRecordItemProcessor())
                .itemWriter(new JsonRecordItemWriter())
                .build();
    }
}
