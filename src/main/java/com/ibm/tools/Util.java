package com.ibm.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by parth on 5/2/19.
 */
public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);
    private static Random rand = new Random();

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static List<String> getRandomNewsTags() {
        int numTags = rand.nextInt(2)+1; //we don't want zero hence adding 1 (we will get either 1 or 2)
        List<String> newsTags = new ArrayList<>();
        for (int i = 0; i < numTags; i++) {
            String interest = Constants.NEWS_TAGS.get(rand.nextInt(Constants.NEWS_TAGS.size()));
           //no duplicates
            if(!newsTags.toString().contains(interest)) {
                newsTags.add(interest);
            }
        }
        return newsTags;
    }
}
