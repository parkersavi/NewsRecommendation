package com.ibm.api.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

/**
 * Created by parth on 5/1/19.
 */
public class NewsHeadline {
    private static final Logger log = LoggerFactory.getLogger(NewsHeadline.class);

    private int id;
    private String title;
    private String newsAbstract;
    private String language;
    private String publicationDate;
    private String author;
    private String location;
    private List<String> tags;

    public NewsHeadline(int id, String title, String newsAbstract, String language, String publicationDate, String author, String location, List<String> tags) {
        this.id = id;
        this.title = title;
        this.newsAbstract = newsAbstract;
        this.language = language;
        this.publicationDate = publicationDate;
        this.author = author;
        this.location = location;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Logger getLog() {
        return log;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsAbstract() {
        return newsAbstract;
    }

    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return  id+"\t"+
                title + "\t" +
                newsAbstract + "\t" +
                language + "\t" +
                publicationDate + "\t" +
                author + "\t" +
                location+"\t"+
                String.join(",", tags);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof NewsHeadline))
            return false;

        NewsHeadline newsHeadline = (NewsHeadline) o;

        return this.id == newsHeadline.id;

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
