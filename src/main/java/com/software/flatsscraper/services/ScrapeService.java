package com.software.flatsscraper.services;

import com.software.flatsscraper.models.Flat;
import com.software.flatsscraper.models.FlatDtoStrings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScrapeService {

    FlatService flatService;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String MORIZON_URL = "https://www.morizon.pl/mieszkania/najnowsze/wroclaw/";

    public ScrapeService(FlatService flatService) {
        this.flatService = flatService;
    }

    protected List<Flat> fetchFlats(LocalDate limitingDate) {
        List<Flat> allFlats = fetchFlatsFromPages(limitingDate, findLastPageNumber());
        return saveFlatsToDb(filterFlatsFromDb(allFlats));
    }

    private List<Flat> filterFlatsFromDb(List<Flat> allFlats) {
        List<String> flatsSiteIdFromDb = flatService.findAllSiteIds();
        return allFlats.stream()
                .filter(flat -> !flatsSiteIdFromDb.contains(flat.getSiteId()))
                .collect(Collectors.toList());
    }

    private int findLastPageNumber() {
        try {
            return scrapeLastPageNumber(fetchHtmlFromUrl(MORIZON_URL));
        } catch (NullPointerException e) {
            logger.error("Cannot fetch page number from html");
            return 1;
        }
    }

    private int scrapeLastPageNumber(Document document) {
        return parsePageNumberToInt(document.select("#contentPage > div.contentBox >" +
                " div > div > div > footer > div > nav > div > ul > li:nth-child(13) > a").text());
    }

    private int parsePageNumberToInt(String pageNumber) {
        try {
            return pageNumber.isEmpty() ? 1 : Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            logger.error("Invalid page number format: {}", pageNumber);
            return 1;
        }
    }

    private List<Flat> fetchFlatsFromPages(LocalDate limitingDate, int lastPageNumber) {
        List<Flat> allFlats = new ArrayList<>();
        for (int i = 1; i <= lastPageNumber; i++) {
            List<Flat> pageFlats = fetchFlatsFromPage(i);
            if (filterFlatsAfterDate(pageFlats, limitingDate).size() != pageFlats.size()) {
                allFlats.addAll(filterFlatsAfterDate(pageFlats, limitingDate));
                break;
            }
            allFlats.addAll(pageFlats);
        }
        return allFlats;
    }

    private List<Flat> filterFlatsAfterDate(List<Flat> flats, LocalDate date) {
        return flats.stream()
                .filter(flat -> flat.getUploadDate().isAfter(date))
                .collect(Collectors.toList());
    }

    private List<Flat> fetchFlatsFromPage(int page) {
        try {
            return scrapePageFlats(fetchHtmlFromUrl(MORIZON_URL + "?page=" + page));
        } catch (NullPointerException e) {
            logger.error("Cannot fetch flats from html");
            return new ArrayList<>();
        }
    }

    private Document fetchHtmlFromUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.error("Cannot fetch html from the url");
            return null;
        }
    }

    private List<Flat> scrapePageFlats(Document document) {
         return document.select("#contentPage > div.contentBox > div > div > div > section")
                .select("div[data-id]")
                .stream()
                .filter(element ->
                    !element.select("h2.single-result__title").text().equals("")
                            && !element.select("p.single-result__category").text().equals(""))
                .map(this::scrapeFlatInfoFromElement)
                .collect(Collectors.toList());
    }

    private Flat scrapeFlatInfoFromElement(Element element) {
        FlatDtoStrings flatDto = new FlatDtoStrings.Builder()
                .siteId(element.getElementsByAttribute("data-id").attr("data-id"))
                .location(element.select("h2.single-result__title").text())
                .price(element.getElementsByAttributeValue("itemprop", "price").attr("content"))
                .uploadDate(element
                        .select("span.single-result__category.single-result__category--date").text())
                .rooms(element.selectFirst("div.info-description.single-result__info")
                        .selectFirst("b").text())
                .area(element.select("li:nth-child(2) > b").text())
                .url(element.getElementsByAttributeValue("itemprop", "url").attr("href"))
                .imageUrl(element.getElementsByAttributeValue("itemprop", "image").attr("content"))
                .build();
        return FlatMapper.mapDtoToEntity(flatDto);
    }

    private List<Flat> saveFlatsToDb(List<Flat> flats) {
        return flatService.saveAll(flats);
    }
}
