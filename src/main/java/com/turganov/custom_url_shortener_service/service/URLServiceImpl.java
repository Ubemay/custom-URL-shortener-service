package com.turganov.custom_url_shortener_service.service;

import com.google.common.hash.Hashing;
import com.turganov.custom_url_shortener_service.model.URL;
import com.turganov.custom_url_shortener_service.model.URLdto;
import com.turganov.custom_url_shortener_service.repository.URLRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class URLServiceImpl implements URLService{

    @Autowired
    private URLRepository urlRepository;

    @Override
    public URL generateShortLink(URLdto urLdto) {

        if (StringUtils.isNotEmpty(urLdto.getURL())) {
            String encodedURL = encodeURL(urLdto.getURL());

            URL URLToPersist = new URL();

            URLToPersist.setCreatedAt(LocalDateTime.now());
            URLToPersist.setOriginalURL(urLdto.getURL());
            URLToPersist.setShortLink(encodedURL);
            URLToPersist.setExpiratedAt(getExpirationTime(urLdto.getExpiratedAt(), URLToPersist.getCreatedAt()));

            URL URLToRet = persistShortLink(URLToPersist);

            if(URLToRet != null) {
                return URLToRet;
            }

            return null;
        }

        return null;
    }

    private LocalDateTime getExpirationTime(String expiratedAt, LocalDateTime createdAt) {

        if(StringUtils.isBlank(expiratedAt)) {
            return createdAt.plusSeconds(180);
        }
        LocalDateTime expirationDate = LocalDateTime.parse(expiratedAt);
        return expirationDate;
    }

    public String encodeURL(String url) {
        String encodeURL = "";

        LocalDateTime time = LocalDateTime.now();

        encodeURL = Hashing.murmur3_32_fixed().hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();

        return encodeURL;
    }

    @Override
    public URL persistShortLink(URL url) {

        URL URLToRet = urlRepository.save(url);

        return URLToRet;
    }

    @Override
    public URL getEncodedURL(String url) {

        URL URLToRet = urlRepository.findByShortLink(url);

        return URLToRet;
    }

    @Override
    public void deleteShortLink(URL url) {
        urlRepository.delete(url);
    }
}
