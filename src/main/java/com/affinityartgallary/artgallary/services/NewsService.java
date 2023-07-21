package com.affinityartgallary.artgallary.services;

import com.affinityartgallary.artgallary.data.model.News;
import com.affinityartgallary.artgallary.dto.request.CreateNewsRequest;
import com.affinityartgallary.artgallary.dto.request.UpdateNewsRequest;

public interface NewsService {
    News createNews(CreateNewsRequest newsRequest);

    News getNewsById(String newsId);
    News updateNews(String newsId, UpdateNewsRequest updateNewsRequest);

    void removeNews(String newsId);
}
