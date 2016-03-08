package com.sf.jobx.domain;

import java.util.Objects;

/**
 * Created by adityasofat on 20/02/2016.
 */
public class JobName implements Comparable<JobName>  {

    private final String group;
    private final String name;
    private final String fullName;

    public JobName(String group, String name) {
        this.group = group;
        this.name = name;
        this.fullName = group.concat(name);
    }

    public JobName(String fullName){
        this.group = fullName.substring(0,2);
        this.name = fullName.substring(3);
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "JobName{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobName jobName = (JobName) o;
        return Objects.equals(group, jobName.group) &&
                Objects.equals(name, jobName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, name);
    }

    @Override
    public int compareTo(JobName jobName) {
        return fullName.compareTo(jobName.fullName);
    }
}
