package com.ibm.api.resources;

import com.ibm.api.requests.Employee;
import com.ibm.api.requests.NewsHeadline;
import com.ibm.api.requests.NewsHeadlineToMatchedEmployees;
import com.ibm.Application;
import com.ibm.tools.Util;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by parth on 5/1/19.
 */
@RestController
public class NewsRecommendationResource {


    @RequestMapping("/news")
    public List<NewsHeadlineToMatchedEmployees> getNews(@RequestParam(value = "publicationDate", defaultValue = "2019-04-30") String publicationDate) {

        return getMatches(Application.newsHeadlinesList, Util.parseDate(publicationDate), Application.employeesList);

    }



    /* take all newsHeadlines and match the publication date with the date provided by the user */
    public static List<NewsHeadlineToMatchedEmployees> getMatches(List<NewsHeadline> newsHeadlines, Date publicationDate, List<Employee> employees) {

        List<NewsHeadlineToMatchedEmployees> newsHeadlineToMatchedEmployeesList = new ArrayList<>();
        List<NewsHeadline> articlesMatchingPublicationDate = getArticlesMatchingPublicationDate(newsHeadlines, publicationDate);

        //we go through the news articles (which are already filtered by publication date) and then we find all employees
        //that match the news articles.
        for (NewsHeadline newsHeadline : articlesMatchingPublicationDate) {
            List<Integer> matchedEmployeesIds = new ArrayList<>();

            for (Employee employee : employees) {

                if (isEmployeesInterestsMatchNewsTags(employee,newsHeadline) && isEmployeesLocationMatchNewsLocation(employee,newsHeadline)) {
                    matchedEmployeesIds.add(employee.getId());
                }
            }
            NewsHeadlineToMatchedEmployees newsHeadlineToMatchedEmployees = new NewsHeadlineToMatchedEmployees();
            newsHeadlineToMatchedEmployees.setEmployeeIds(matchedEmployeesIds);
            newsHeadlineToMatchedEmployees.setNewsHeadline(newsHeadline);
            newsHeadlineToMatchedEmployeesList.add(newsHeadlineToMatchedEmployees);
        }

        return newsHeadlineToMatchedEmployeesList;

    }

    private static boolean isEmployeesLocationMatchNewsLocation(Employee employee, NewsHeadline newsHeadline) {
        return (employee.getLocation().equals(newsHeadline.getLocation()));
    }


    public static boolean isEmployeesInterestsMatchNewsTags(Employee employee, NewsHeadline newsHeadline) {
        List<String> employeeInterests = new ArrayList<>(employee.getInterests());
        List<String> newsHeadlinesTags = new ArrayList<>(newsHeadline.getTags());

        return employeeInterests.stream().anyMatch(s -> newsHeadlinesTags.contains(s));

    }


    private static List<NewsHeadline> getArticlesMatchingPublicationDate(List<NewsHeadline> newsHeadlines, Date publicationDate) {
        Date currentDate = new Date();
        boolean invalidPublicationDate = publicationDate.compareTo(currentDate) > 0;

        if (invalidPublicationDate) {
            return Collections.emptyList();
        }
        return newsHeadlines.stream()
                .filter(newsHeadline -> (Util.parseDate(newsHeadline.getPublicationDate()).compareTo(publicationDate) == 0))
                .collect(Collectors.toList());

    }

}
