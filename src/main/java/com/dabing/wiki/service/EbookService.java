package com.dabing.wiki.service;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.req.EbookReq;
import com.dabing.wiki.resp.EbookResp;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EbookService {
    List<EbookResp> list(EbookReq ebookReq);
}
