package com.ibm.data;

import com.ibm.api.requests.Employee;
import com.ibm.api.requests.NewsHeadline;
import com.ibm.tools.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by parth on 5/2/19.
 */
public class DataLoader{
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private ResourceLoader resourceLoader;



    public List<Employee> readEmployeesFromFile() {
        log.info("Loading employee list ");
        String fileName = AppConstants.EMPLOYEES_FILENAME;
        List<Employee> employeeList = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        new PathMatchingResourcePatternResolver(this.getClass().getClassLoader()).getResource("classpath:"+fileName).getInputStream()
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
        log.info("Done Loading "+employeeList.size()+" employees");
        return employeeList;
    }


    public List<NewsHeadline> readNewsHeadlinesFromFile() {
        log.info("Loading News Headlines");
        List<NewsHeadline> newsHeadlinesList = new ArrayList<>();
        String fileName = AppConstants.NEWS_HEADLINES_FILENAME;
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        new PathMatchingResourcePatternResolver(this.getClass().getClassLoader()).getResource("classpath:"+fileName).getInputStream()
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
        log.info("Done Loading "+newsHeadlinesList.size()+" news headlines");
        return newsHeadlinesList;
    }

}
