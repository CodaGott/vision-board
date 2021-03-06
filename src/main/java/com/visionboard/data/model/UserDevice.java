package com.visionboard.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserDevice {

    @Id
    private String id;

    @DBRef
    private User user;

    private String deviceType;

    private String deviceId;

    @DBRef
    private RefreshToken refreshToken;

    private Boolean isRefreshActive;
}
