package com.software.flatsscraper.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("flats")
public class Flat {
    @Id
    private Long id;

    private String siteId;
    private String location;
    private double price;
    private int area;
    private int rooms;
    private LocalDate uploadDate;
    private String url;
    private String imageUrl;

    public static final class Builder {
        private String siteId;
        private String location;
        private double price;
        private int area;
        private int rooms;
        private LocalDate uploadDate;
        private String url;
        private String imageUrl;

        public Builder siteId(String siteId) {
            this.siteId = siteId;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder area(int area) {
            this.area = area;
            return this;
        }

        public Builder rooms(int rooms) {
            this.rooms = rooms;
            return this;
        }

        public Builder uploadDate(LocalDate uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Flat build() {
            Flat flat = new Flat();
            flat.siteId = this.siteId;
            flat.location = this.location;
            flat.price = this.price;
            flat.area = this.area;
            flat.rooms = this.rooms;
            flat.uploadDate = this.uploadDate;
            flat.url = this.url;
            flat.imageUrl = this.imageUrl;
            return flat;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", siteId=" + siteId +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", rooms=" + rooms +
                ", uploadDate=" + uploadDate +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
