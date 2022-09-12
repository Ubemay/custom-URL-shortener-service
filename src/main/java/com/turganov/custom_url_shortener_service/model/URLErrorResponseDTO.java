package com.turganov.custom_url_shortener_service.model;

import lombok.Data;

@Data
public class URLErrorResponseDTO {

    private String status;

    private String error;

    public URLErrorResponseDTO() {
    }

    public URLErrorResponseDTO(String status, String error) {
        this.status = status;
        this.error = error;
    }
}
