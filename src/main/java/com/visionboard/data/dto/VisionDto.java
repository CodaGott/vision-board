package com.visionboard.data.dto;

import com.visionboard.data.model.Category;

import java.time.LocalDateTime;
import java.util.List;

public class VisionDto {
    private String title;
    private String vision;
    private LocalDateTime createdOn;
    private LocalDateTime lastUpdated;
    private List<Category> categories;
}
