package com.turganov.custom_url_shortener_service.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class URLResponseDTO {

    private String originalURL;

    private String shortLink;

    private LocalDateTime expiratedAt;

    public URLResponseDTO() {
    }

    public URLResponseDTO(String originalURL, String shortLink, LocalDateTime expiratedAt) {
        this.originalURL = originalURL;
        this.shortLink = shortLink;
        this.expiratedAt = expiratedAt;
    }
}
