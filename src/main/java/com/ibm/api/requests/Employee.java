package com.ibm.api.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by parth on 5/1/19.
 */
public class Employee {
    private static final Logger log = LoggerFactory.getLogger(Employee.class);


    private int id;
    private String name;
    private String location;
    private String jobRole;
    private String department;
    private List<String> interests;

    public Employee(int id, String name, String location, String jobRole, String department, List<String> interests){
        this.id = id;
        this.name = name;
        this.location = location;
        this.jobRole = jobRole;
        this.department = department;
        this.interests = interests;
        this.location = location;
    }

    public static Logger getLog() {
        return log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }



    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return
         id+"\t"+
         name+"\t"+
         location+"\t"+
         jobRole+"\t"+
         department+"\t"+
                 String.join(",", interests);
    }
}
