package com.example.fulldashboardededdneddy.data;

import java.sql.Timestamp;

public class AnnouncementDataClass {

    private String title;
    private String description;
    private String imageUrl;

    private Long timestamp;

    public AnnouncementDataClass(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AnnouncementDataClass(String dataTitle, String dataDesc, String dataImage, Long dataTime) {
        this.title = dataTitle;
        this.description = dataDesc;
        this.imageUrl = dataImage;
        this.timestamp = dataTime;
    }
    public AnnouncementDataClass(){

    }

}

