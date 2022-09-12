package com.turganov.custom_url_shortener_service.repository;

import com.turganov.custom_url_shortener_service.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    public URL findByShortLink(String shorLink);



}
