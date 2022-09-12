package com.turganov.custom_url_shortener_service.service;

import com.turganov.custom_url_shortener_service.model.URL;
import com.turganov.custom_url_shortener_service.model.URLdto;
import org.springframework.stereotype.Service;

@Service
public interface URLService {

    public URL generateShortLink(URLdto urLdto);

    public URL persistShortLink(URL url);

    public URL getEncodedURL(String url);

    public void deleteShortLink(URL url);

}
