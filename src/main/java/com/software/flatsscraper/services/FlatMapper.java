package com.software.flatsscraper.services;

import com.software.flatsscraper.models.Flat;
import com.software.flatsscraper.models.FlatDtoStrings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FlatMapper {

    private static final Logger logger = LoggerFactory.getLogger(FlatMapper.class);

    private FlatMapper() {
    }

    public static Flat mapDtoToEntity(FlatDtoStrings flatDto) {
        return new Flat.Builder()
                .siteId(flatDto.getSiteId())
                .location(flatDto.getLocation())
                .imageUrl(flatDto.getImageUrl())
                .url(flatDto.getUrl())
                .price(parsePriceToDouble(flatDto.getPrice()))
                .area(parseAreaToInt(flatDto.getArea()))
                .rooms(parseRoomsToInt(flatDto.getRooms()))
                .uploadDate(mapDate(flatDto.getUploadDate()))
                .build();
    }

    private static int parseRoomsToInt(String rooms) {
        try {
            return rooms.isEmpty() ? 0 : Integer.parseInt(rooms);
        } catch (NumberFormatException e) {
            logger.error("Invalid rooms format: {}", rooms);
            return 0;
        }
    }

    private static int parseAreaToInt(String area) {
        try {
            return area.isEmpty() ? 0 : Integer.parseInt(area);
        } catch (NumberFormatException e) {
            logger.error("Invalid area format: {}", area);
            return 0;
        }
    }

    private static double parsePriceToDouble(String price) {
        try {
            return price.isEmpty() ? 0 : Double.parseDouble(price);
        } catch (NumberFormatException e) {
            logger.error("Invalid price format: {}", price);
            return 0;
        }
    }

    private static LocalDate mapDate(String uploadDate) {
        LocalDate localDate;
        if (uploadDate.equals("dzisiaj")) {
            localDate = LocalDate.now();
        } else if (uploadDate.equals("wczoraj")) {
            localDate = LocalDate.now().minusDays(1);
        } else {
            localDate = LocalDate.parse(uploadDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return localDate;
    }
}
