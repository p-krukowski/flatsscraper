package com.software.flatsscraper.models;


import org.springframework.data.annotation.Id;

import java.time.LocalDate;


public class Flat {
    @Id
    private Long id;

    private String location;
    private double price;
    private int area;
    private int rooms;
    private LocalDate uploadDate;
    private String url;
    private String imageUrl;
}
