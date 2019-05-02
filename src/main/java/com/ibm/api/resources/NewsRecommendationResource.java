package com.ibm.api.resources;

import com.ibm.Application;
import com.ibm.api.requests.Employee;
import com.ibm.api.requests.NewsHeadline;
import com.ibm.api.requests.NewsHeadlineToMatchedEmployees;
import com.ibm.tools.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by parth on 5/1/19.
 */
@RestController
public class NewsRecommendationResource {
    private static final Logger log = LoggerFactory.getLogger(NewsRecommendationResource.class);

    @RequestMapping("/news")
    public List<NewsHeadlineToMatchedEmployees> getNews(
            @RequestParam(value = "publicationDate", defaultValue = "2019-04-30") String publicationDate) {

        return getMatches(Application.newsHeadlinesList, Util.parseDate(publicationDate), Application.employeesList);
    }


    /*  */
    private static List<NewsHeadlineToMatchedEmployees> getMatches(List<NewsHeadline> newsHeadlines, Date publicationDate, List<Employee> employees) {

        List<NewsHeadlineToMatchedEmployees> newsHeadlineToMatchedEmployeesList = new ArrayList<>();
        List<NewsHeadline> articlesMatchingPublicationDate = getArticlesMatchingPublicationDate(newsHeadlines, publicationDate);

        for (NewsHeadline newsHeadline : articlesMatchingPublicationDate) {
            List<Integer> matchedEmployeesIds = new ArrayList<>();
            for (Employee employee : employees) {

                if (doesEmployeesInterestsMatchNewsTags(employee,newsHeadline) &&
                        doesEmployeesLocationMatchNewsLocation(employee,newsHeadline)) {
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

    // match user's location with news headline location
    private static boolean doesEmployeesLocationMatchNewsLocation(Employee employee, NewsHeadline newsHeadline) {
        return (employee.getLocation().equals(newsHeadline.getLocation()));
    }

    // match user's interests to news headline tags
    private static boolean doesEmployeesInterestsMatchNewsTags(Employee employee, NewsHeadline newsHeadline) {
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

        List<NewsHeadline> newsHeadlinesByPublicationDate = newsHeadlines.stream()
                .filter(newsHeadline -> (Util.parseDate(newsHeadline.getPublicationDate()).compareTo(publicationDate) == 0))
                .collect(Collectors.toList());
        log.info(newsHeadlinesByPublicationDate.size()+" News Headlines match "+publicationDate);

        return newsHeadlines;
    }

}
