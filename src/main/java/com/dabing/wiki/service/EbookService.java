package com.dabing.wiki.service;

import com.dabing.wiki.req.EbookReq;
import com.dabing.wiki.resp.EbookResp;
import com.dabing.wiki.resp.PageResp;

public interface EbookService {
    PageResp<EbookResp> list(EbookReq ebookReq);
}
