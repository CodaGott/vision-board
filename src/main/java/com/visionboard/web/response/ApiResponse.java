package com.visionboard.web.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
public class ApiResponse {
    private boolean success;
    private String message;
}
