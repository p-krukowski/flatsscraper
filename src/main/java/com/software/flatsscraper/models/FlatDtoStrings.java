package com.software.flatsscraper.models;

public class FlatDtoStrings {

    private String siteId;
    private String location;
    private String price;
    private String area;
    private String rooms;
    private String uploadDate;
    private String url;
    private String imageUrl;

    public static final class Builder {
        private String siteId;
        private String location;
        private String price;
        private String area;
        private String rooms;
        private String uploadDate;
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

        public Builder price(String price) {
            this.price = price;
            return this;
        }

        public Builder area(String area) {
            this.area = area;
            return this;
        }

        public Builder rooms(String rooms) {
            this.rooms = rooms;
            return this;
        }

        public Builder uploadDate(String uploadDate) {
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

        public FlatDtoStrings build() {
            FlatDtoStrings flatDto = new FlatDtoStrings();
            flatDto.siteId = this.siteId;
            flatDto.location = this.location;
            flatDto.price = this.price;
            flatDto.area = this.area;
            flatDto.rooms = this.rooms;
            flatDto.uploadDate = this.uploadDate;
            flatDto.url = this.url;
            flatDto.imageUrl = this.imageUrl;
            return flatDto;
        }
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
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
        return "FlatDtoStrings{" +
                "siteId='" + siteId + '\'' +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                ", area='" + area + '\'' +
                ", rooms='" + rooms + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
