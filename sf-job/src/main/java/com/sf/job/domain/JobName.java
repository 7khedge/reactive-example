package com.sf.job.domain;

/**
 * Created by adityasofat on 20/02/2016.
 */
public enum JobName {
    SNS_ApplicationInstance("SNS","ApplicationInstance")
    ;

    private final String group;
    private final String name;

    JobName(String group, String name) {
        this.group = group;
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }
}
