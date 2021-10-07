package com.visionboard.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document
@Data
public class User {
    private String userId;
    private String name;
    private String age;
    private String email;
    private String password;
    private Role roles;
    @DBRef
    private List<Vision> visions;
    private Boolean active;

    public void activate(){
        this.active = true;
    }

    public void addVision(Vision... vision){
        if (visions == null){
            this.visions = new ArrayList<>();
            visions.addAll(Arrays.asList(vision));
        }
    }

    public void removeVision(Vision vision){
        if (visions == null){
            this.visions = new ArrayList<>();
        }
        this.visions.remove(vision);
    }

}
