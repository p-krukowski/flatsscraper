package com.software.flatsscraper.services;

import com.software.flatsscraper.models.Flat;
import com.software.flatsscraper.repositories.FlatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatService {

    private FlatRepository flatRepository;

    public FlatService(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    public List<Flat> saveAll(List<Flat> flats) {
        return (List<Flat>) flatRepository.saveAll(flats);
    }

    public List<String> findAllSiteIds() {
        return flatRepository.findAllSiteIds();
    }
}
