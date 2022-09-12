package com.turganov.custom_url_shortener_service.model;

import lombok.Data;

@Data
public class URLdto {

    private String URL;

    private String expiratedAt;

    public URLdto() {
    }

    public URLdto(String URL, String expiratedAt) {
        this.URL = URL;
        this.expiratedAt = expiratedAt;
    }
}
