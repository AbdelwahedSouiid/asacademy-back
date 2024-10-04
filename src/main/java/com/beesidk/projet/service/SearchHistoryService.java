package com.beesidk.projet.service;

import com.beesidk.projet.entity.SearchHistory;
import com.beesidk.projet.interfaces.IService;
import com.beesidk.projet.repository.SearchHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchHistoryService implements IService<SearchHistory> {
    SearchHistoryRepository searchHistoryRepository;

    @Override
    public List<SearchHistory> retrieveAll() {
        return List.of();
    }

    @Override
    public SearchHistory retrieve(String id) {
        return null;
    }

    @Override
    public SearchHistory add(SearchHistory searchHistory) {

        return searchHistoryRepository.save(searchHistory);
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public SearchHistory modify(SearchHistory searchHistory) {
        return null;
    }
}
