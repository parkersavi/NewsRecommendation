package com.ibm.api.resources;

import com.ibm.Application;
import com.ibm.api.requests.Employee;
import com.ibm.api.requests.NewsHeadlineToMatchedEmployees;
import com.ibm.tools.AppConstants;
import com.ibm.tools.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by parth on 5/3/19.
 */
@RestController
public class EmployeeResource {
    private static final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    @RequestMapping("/employee")
    public Employee news(@RequestParam(value = "id") int employeeId) {
        if(employeeId<0 || employeeId>=10000){
                throw new IllegalArgumentException("Employee Id must be between 0 and "+ (AppConstants.NUM_EMPLOYEES-1));
            }
        return Application.employeesList.get(employeeId);
    }
}
