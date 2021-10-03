package com.visionboard.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Document
@Data
public class Vision {
    private String visionId;
    private String title;
    private String vision;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdated;
    private List<Category> categories;


    public void addToCategory(Category... category){
        if (categories == null){
            this.categories = new ArrayList<>();
            categories.addAll(Arrays.asList(category));
        }
    }


}
