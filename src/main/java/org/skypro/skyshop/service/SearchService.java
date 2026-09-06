package org.skypro.skyshop.service;

import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }


}
