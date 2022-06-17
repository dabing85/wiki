package com.dabing.wiki.controller;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.entities.EbookExample;
import com.dabing.wiki.req.EbookReq;
import com.dabing.wiki.resp.CommonResp;
import com.dabing.wiki.resp.EbookResp;
import com.dabing.wiki.resp.PageResp;
import com.dabing.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<PageResp<EbookResp>> list(EbookReq req){
        PageResp<EbookResp> pageResp = ebookService.list(req);
        return new CommonResp<>(true,"",pageResp);
    }
}
