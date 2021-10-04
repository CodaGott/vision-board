package com.visionboard.data.repository;

import com.visionboard.data.model.Category;
import com.visionboard.data.model.Vision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VisionRepository extends MongoRepository<Vision, String> {
    List<Vision> findByTitle(String title);
    List<Vision> findByCategories(Category category);

}
