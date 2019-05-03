package com.ibm.data;

import com.ibm.tools.AppConstants;
import com.ibm.api.requests.NewsHeadline;
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
public class NewsHeadlinesGenerator {
    private static final Logger log = LoggerFactory.getLogger(NewsHeadlinesGenerator.class);

    private static final int NUM_NEWS_HEADLINES = AppConstants.NUM_NEWS_HEADLINES;

    private static final Random rand = new Random();

    private static final List<String> TITLE = Arrays.asList("2019 Annual Shareholders Meeting", "The Importance of Leadership for PwD");
    private static final List<String> ABSTRACT = Arrays.asList("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
    private static final List<String> LANGUAGE = Arrays.asList("English");
    private static final List<String> PUBLICATION_DATE = Arrays.asList("2019-03-01","2019-03-02","2019-03-03","2019-03-04","2019-03-05","2019-03-06","2019-03-07","2019-03-08","2019-03-09","2019-03-10","2019-03-11","2019-03-12","2019-03-13","2019-03-14","2019-03-15","2019-03-16","2019-03-17","2019-03-18","2019-03-19","2019-03-20","2019-03-21","2019-03-22","2019-03-23","2019-03-24","2019-03-25","2019-03-26","2019-03-27","2019-03-28","2019-03-29","2019-03-30","2019-03-31","2019-04-01","2019-04-02","2019-04-03","2019-04-04","2019-04-05","2019-04-06","2019-04-07","2019-04-08","2019-04-09","2019-04-10","2019-04-11","2019-04-12","2019-04-13","2019-04-14","2019-04-15","2019-04-16","2019-04-17","2019-04-18","2019-04-19","2019-04-20","2019-04-21","2019-04-22","2019-04-23","2019-04-24","2019-04-25","2019-04-26","2019-04-27","2019-04-28","2019-04-29","2019-04-30");
    private static final List<String> AUTHOR = Arrays.asList("Kadin Mejia", "Haylee Esparza", "Kelsie Price");
    private static final List<String> LOCATION = Arrays.asList("USA","Brazil","Canada","Germany","Mexico","India","China","South Africa","Netherlands","Italy");

    public static void main(String[] args) {

        log.info("Generating News Headlines!");
        generateNewsHeadlines();
        log.info("Finished Generating " + NUM_NEWS_HEADLINES + " News Headlines!");
    }


    public static void generateNewsHeadlines() {

        try (BufferedWriter newsHeadlinesWriter = new BufferedWriter(new FileWriter(AppConstants.NEWS_HEADLINES_FILENAME))) {
            for (int i = 0; i < NUM_NEWS_HEADLINES; i++) {
                NewsHeadline newsHeadline =
                        new NewsHeadline
                                (       i,
                                        TITLE.get(rand.nextInt(TITLE.size())),
                                        ABSTRACT.get(rand.nextInt(ABSTRACT.size())),
                                        LANGUAGE.get(rand.nextInt(LANGUAGE.size())),
                                        PUBLICATION_DATE.get(rand.nextInt(PUBLICATION_DATE.size())),
                                        AUTHOR.get(rand.nextInt(AUTHOR.size())),
                                        LOCATION.get(rand.nextInt(LOCATION.size())),
                                        Util.getRandomNewsTags()
                                );
                newsHeadlinesWriter.write(newsHeadline.toString());
                newsHeadlinesWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
