package com.ibm.api.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by parth on 5/2/19.
 */
public class NewsHeadlineToMatchedEmployees {
    private static final Logger log = LoggerFactory.getLogger(NewsHeadlineToMatchedEmployees.class);
    private NewsHeadline newsHeadline;
    private List<Integer> employeeIds;


    public NewsHeadline getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(NewsHeadline newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Integer> employeeIds) {
        this.employeeIds = employeeIds;
    }
}
