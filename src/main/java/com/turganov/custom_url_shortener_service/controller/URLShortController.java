package com.turganov.custom_url_shortener_service.controller;


import com.turganov.custom_url_shortener_service.model.URL;
import com.turganov.custom_url_shortener_service.model.URLErrorResponseDTO;
import com.turganov.custom_url_shortener_service.model.URLResponseDTO;
import com.turganov.custom_url_shortener_service.model.URLdto;
import com.turganov.custom_url_shortener_service.service.URLService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class URLShortController {

    @Autowired
    private URLService urlService;


    @PostMapping("/generate")
    public ResponseEntity<?> generateShortLink(@RequestBody URLdto urLdto) {
        URL urlToRet = urlService.generateShortLink(urLdto);


        if(urlToRet != null) {
            URLResponseDTO urlResponseDTO = new URLResponseDTO();
            urlResponseDTO.setOriginalURL(urlToRet.getOriginalURL());
            urlResponseDTO.setExpiratedAt(urlToRet.getExpiratedAt());
            urlResponseDTO.setShortLink(urlToRet.getShortLink());
            return new ResponseEntity<URLResponseDTO>(urlResponseDTO, HttpStatus.OK);

        }

        URLErrorResponseDTO urlErrorResponseDTO = new URLErrorResponseDTO();
        urlErrorResponseDTO.setStatus("404");
        urlErrorResponseDTO.setError("There was an error processing your request. Please, try again");
        return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginal(@PathVariable String shortLink, HttpServletResponse httpServletResponse) throws IOException {

        if (StringUtils.isEmpty(shortLink)) {
            URLErrorResponseDTO urlErrorResponseDTO = new URLErrorResponseDTO();
            urlErrorResponseDTO.setError("400");
            urlErrorResponseDTO.setStatus("Invalid URL");

            return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.OK);
        }

        URL urlToRent = urlService.getEncodedURL(shortLink);

        if (urlToRent == null) {
            URLErrorResponseDTO urlErrorResponseDTO = new URLErrorResponseDTO();
            urlErrorResponseDTO.setError("400");
            urlErrorResponseDTO.setStatus("URL does not exist");

            return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.OK);
        }

        if(urlToRent.getExpiratedAt().isBefore(LocalDateTime.now())) {

            URLErrorResponseDTO urlErrorResponseDTO = new URLErrorResponseDTO();
            urlErrorResponseDTO.setError("200");
            urlErrorResponseDTO.setStatus("URL is expired. Please try generating another one");

            return new ResponseEntity<URLErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.OK);

        }

        httpServletResponse.sendRedirect(urlToRent.getOriginalURL());
        return null;

    }
}
