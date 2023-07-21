package com.affinityartgallary.artgallary;

import com.affinityartgallary.artgallary.data.model.News;
import com.affinityartgallary.artgallary.dto.request.CreateNewsRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateNewsRequest;
import com.affinityartgallary.artgallary.services.NewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class NewsServiceTest {
    @Autowired
    NewsService newsService;
    UpdateNewsRequest updateNewsRequest;
    CreateNewsRequest newsRequest;
    @BeforeEach
    void setUp(){
        newsRequest = new CreateNewsRequest();
        newsRequest.setTitle("first news headline");
        newsRequest.setBody("this is news body");
        newsRequest.setDate(new Date(2023,7,19));
        newsRequest.setImageUrl("www.cloudinary.com");

        updateNewsRequest = new UpdateNewsRequest();
        updateNewsRequest.setTitle("this is an updated title");
        updateNewsRequest.setBody("this is news body");
        updateNewsRequest.setDate(new Date(2023,7,19));
        updateNewsRequest.setImageUrl("www.cloudinary.com");


    }
    @Test
    void testThatNewsCanBeCreated(){
        News news = newsService.createNews(newsRequest);
        assertEquals(news.getTitle(),"first news headline");
    }
    @Test
    void testThatNewsCanBeRetrievedById(){
        News news = newsService.createNews(newsRequest);
        assertEquals(news.getTitle(),"first news headline");
        News foundNews = newsService.getNewsById(news.getId());
        assertEquals(news.getId(),foundNews.getId());
    }
    @Test
    void testThatNewsCanBeUpdated(){
        News news = newsService.createNews(newsRequest);
        assertEquals(news.getTitle(),"first news headline");
        News updatedNews = newsService.updateNews(news.getId(), updateNewsRequest);
        assertEquals(updatedNews.getTitle(),"this is an updated title");

    }
    @Test
    void testThatNewsCanBeDeleted(){
        News news = newsService.createNews(newsRequest);
        String newsId = news.getId();
        newsService.removeNews(newsId);
        assertNull(newsService.getNewsById(news.getId()));
    }
}
