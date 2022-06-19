package com.dabing.wiki.service;

import com.dabing.wiki.req.EbookQueryReq;
import com.dabing.wiki.req.EbookSaveReq;
import com.dabing.wiki.resp.EbookQueryResp;
import com.dabing.wiki.resp.PageResp;

public interface EbookService {
    PageResp<EbookQueryResp> list(EbookQueryReq ebookReq);

    int save(EbookSaveReq ebookSaveReq);
}
