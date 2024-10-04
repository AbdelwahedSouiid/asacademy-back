package com.beesidk.projet.repository;

import com.beesidk.projet.entity.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SearchHistoryRepository extends MongoRepository<SearchHistory, Long> {
}
