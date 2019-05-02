package com.ibm.data;

import com.ibm.tools.AppConstants;
import com.ibm.api.requests.Employee;
import com.ibm.tools.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by parth on 5/1/19.
 */
public class EmployeesGenerator {
    private static final Logger log = LoggerFactory.getLogger(EmployeesGenerator.class);

    private static final int NUM_EMPLOYEES = 10000;

    private static final Random rand = new Random();

    private static final List<String> LOCATIONS = Arrays.asList("USA","Brazil","Canada","Germany","Mexico","India","China","South Africa","Netherlands","Italy");
    private static final List<String> JOB_ROLES = Arrays.asList("Engineer","Offering Manager","Researcher");
    private static final List<String> DEPARTMENT = Arrays.asList("WCE","GBS","CIO");
    private static final List<String> EMPLOYEE_NAMES = Arrays.asList("Parth","Jon","Sam");

    public static void main(String[] args) {
        log.info("Generating Employee Records!");
        generateEmployees();
        log.info("Finished Generating "+ NUM_EMPLOYEES +" Employee Records!");
    }


    public static void generateEmployees() {

        try(BufferedWriter employeesWriter = new BufferedWriter(new FileWriter(AppConstants.EMPLOYEES_FILENAME))) {
            for (int i = 0; i < NUM_EMPLOYEES; i++) {
                int employeeId = i;
                //                TODO ASSSUMING THAT ALL EMPLOYEES HAVE INTERESTS
                String uniqueEmployeeName = EMPLOYEE_NAMES.get(rand.nextInt(EMPLOYEE_NAMES.size())) + "_"+employeeId;
                Employee employeesRecord =
                        new Employee
                                (employeeId,
                                uniqueEmployeeName,
                                LOCATIONS.get(rand.nextInt(EMPLOYEE_NAMES.size())),
                                JOB_ROLES.get(rand.nextInt(JOB_ROLES.size())),
                                DEPARTMENT.get(rand.nextInt(DEPARTMENT.size())),
                                        Util.getRandomNewsTags()
                                );
                employeesWriter.write(employeesRecord.toString());
                employeesWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
