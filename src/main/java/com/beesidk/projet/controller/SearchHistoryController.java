package com.beesidk.projet.controller;


import com.beesidk.projet.entity.SearchHistory;
import com.beesidk.projet.service.SearchHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/search_history")
public class SearchHistoryController {

    SearchHistoryService searchHistoryService;

    @PostMapping("/search-history")
    public ResponseEntity<SearchHistory> saveSearchHistory(@RequestBody SearchHistory searchHistory) {
        searchHistory.setSearchDate(LocalDateTime.now()); // Ajoute l'horodatage
        SearchHistory savedHistory = searchHistoryService.add(searchHistory);
        return ResponseEntity.ok(savedHistory);
    }
}
