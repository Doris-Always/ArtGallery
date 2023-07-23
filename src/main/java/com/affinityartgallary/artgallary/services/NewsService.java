package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.News;
import com.affinityartgallary.artgallary.dto.request.CreateNewsRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateNewsRequest;

import java.io.IOException;

public interface NewsService {
    News createNews(CreateNewsRequest newsRequest) throws IOException;

    News getNewsById(String newsId);
    News updateNews(String newsId, UpdateNewsRequest updateNewsRequest) throws IOException;

    void removeNews(String newsId);
}
