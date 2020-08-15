package com.software.flatsscraper.services;

import com.software.flatsscraper.models.Flat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MorizonFlatScraper {

    ScrapeService scrapeService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MorizonFlatScraper(ScrapeService scrapeService) {
        this.scrapeService = scrapeService;
    }

    public List<Flat> fetchFlatsAfterDate(LocalDate limitingDate) {
        if (limitingDate.isBefore(LocalDate.now())) {
            List<Flat> flats = scrapeService.fetchFlats(limitingDate);
            logger.info("Saved {} flats to database", flats.size());
            return flats;
        } else {
            throw new DateTimeException("Invalid date: Date has to be from past.");
        }
    }
}
