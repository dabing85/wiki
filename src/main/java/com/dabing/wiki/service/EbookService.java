package com.dabing.wiki.service;

import com.dabing.wiki.entities.Ebook;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EbookService {
    List<Ebook> list();
}
