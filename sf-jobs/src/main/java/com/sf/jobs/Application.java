package com.sf.jobs;

import com.sf.job.definition.JobCollectorDefinition;
import com.sf.jobs.spring.context.JobsConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by adityasofat on 29/02/2016.
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JobsConfig.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(JobCollectorDefinition.class);
        System.out.println(Arrays.toString(beanNamesForType));
    }
}
