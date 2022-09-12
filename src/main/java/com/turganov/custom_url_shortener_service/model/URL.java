package com.turganov.custom_url_shortener_service.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
@Data
public class URL {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private String originalURL;

    private String shortLink;

    private LocalDateTime createdAt;

    private LocalDateTime expiratedAt;

    public URL() {
    }

    public URL(Long id, String originalURL, String shortLink, LocalDateTime createdAt, LocalDateTime expiratedAt) {
        this.id = id;
        this.originalURL = originalURL;
        this.shortLink = shortLink;
        this.createdAt = createdAt;
        this.expiratedAt = expiratedAt;
    }
}
