package com.ibm;

import com.ibm.api.requests.Employee;
import com.ibm.api.requests.NewsHeadline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by parth on 5/2/19.
 */
public class DataLoader implements ResourceLoaderAware {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private ResourceLoader resourceLoader;



    public List<Employee> readEmployeesFromFile() {
        log.info("Loading employee list ");
        String fileName = Constants.EMPLOYEES_FILENAME;
        List<Employee> employeeList = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:"+fileName).getInputStream()
                )).lines()){

            employeeList = stream.map(line -> {
                String[] values = line.split("\t");
                return new Employee(
                        Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        Arrays.asList(values[5].split(",")));

            }).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Done Loading "+employeeList.size());
        return employeeList;
    }


    public List<NewsHeadline> readNewsHeadlinesFromFile() {
        log.info("Loading News Headlines");
        List<NewsHeadline> newsHeadlinesList = new ArrayList<>();
        String fileName = Constants.NEWS_HEADLINES_FILENAME;
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        resourceLoader.getResource("classpath:"+fileName).getInputStream()
                )).lines()){

            newsHeadlinesList = stream.map(line -> {
                String[] values = line.split("\t");
                return new NewsHeadline(
                        Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        values[4],
                        values[5],
                        values[6],
                        Arrays.asList(values[7].split(",")));

            }).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Done Loading "+newsHeadlinesList.size());
        return newsHeadlinesList;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
            this.resourceLoader = resourceLoader;
    }
}
