package com.ibm; /**
 * Created by parth on 5/1/19.
 */

import com.ibm.api.requests.Employee;
import com.ibm.api.requests.NewsHeadline;
import com.ibm.data.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@SpringBootApplication
public class Application {

    @Autowired
    private static ResourceLoader resourceLoader;
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static List<Employee> employeesList;
    public static List<NewsHeadline> newsHeadlinesList;

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Application.class, args);
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        DataLoader dataLoader = (DataLoader) context.getBean("customResourceLoader");

        employeesList = dataLoader.readEmployeesFromFile();
        newsHeadlinesList = dataLoader.readNewsHeadlinesFromFile();
    }





}