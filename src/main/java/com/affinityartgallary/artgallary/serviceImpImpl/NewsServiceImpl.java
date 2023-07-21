package com.affinityartgallary.artgallary.serviceImpImpl;

import com.affinityartgallary.artgallary.data.model.ArtWork;
import com.affinityartgallary.artgallary.data.model.News;
import com.affinityartgallary.artgallary.data.repository.NewsRepository;
import com.affinityartgallary.artgallary.dto.request.CreateNewsRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateNewsRequest;
import com.affinityartgallary.artgallary.exception.ArtWorkNotFoundException;
import com.affinityartgallary.artgallary.exception.NewsAlreadyExistException;
import com.affinityartgallary.artgallary.exception.NewsNotFoundException;
import com.affinityartgallary.artgallary.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;
    @Override
    public News createNews(CreateNewsRequest newsRequest) {
       Optional<News> existingNews =  newsRepository.findByTitle(newsRequest.getTitle());
       if (existingNews.isPresent()){
           throw new NewsAlreadyExistException("News already exist");
       }
        News news = News.builder()
                .title(newsRequest.getTitle())
                .body(newsRequest.getBody())
                .imageUrl(newsRequest.getImageUrl())
                .date(newsRequest.getDate())
                .build();
      return newsRepository.save(news);
    }

    @Override
    public News getNewsById(String newsId) {
       return newsRepository.findById(newsId).orElseThrow(()->new NewsNotFoundException("news not found"));
    }

    @Override
    public News updateNews(String newsId, UpdateNewsRequest updateNewsRequest) {
        News existingNews = newsRepository.findById(newsId).orElseThrow(()->new NewsNotFoundException("news not found"));
        update(updateNewsRequest, existingNews);
        News updatedNews = newsRepository.save(existingNews);
        return updatedNews;
    }

    @Override
    public void removeNews(String newsId) {
        newsRepository.deleteById(newsId);
    }

    private void update(UpdateNewsRequest updateNewsRequest,News news){
        news.setTitle(updateNewsRequest.getTitle() != null ? updateNewsRequest.getTitle() : news.getTitle());
        news.setBody(updateNewsRequest.getBody() != null ? updateNewsRequest.getBody() : news.getBody());
        news.setDate(updateNewsRequest.getDate() != null ? updateNewsRequest.getDate() : news.getDate());
        news.setImageUrl(updateNewsRequest.getImageUrl() != null ? updateNewsRequest.getImageUrl() : news.getImageUrl());
    }
}
