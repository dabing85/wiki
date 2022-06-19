package com.dabing.wiki.controller;

import com.dabing.wiki.req.EbookQueryReq;
import com.dabing.wiki.req.EbookSaveReq;
import com.dabing.wiki.resp.CommonResp;
import com.dabing.wiki.resp.EbookQueryResp;
import com.dabing.wiki.resp.PageResp;
import com.dabing.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<PageResp<EbookQueryResp>> list(EbookQueryReq req){
        PageResp<EbookQueryResp> pageResp = ebookService.list(req);
        return new CommonResp<>(true,"",pageResp);
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req){
        int res = ebookService.save(req);
        CommonResp commonResp=new CommonResp();
        commonResp.setDate(res);
        return commonResp;
    }
}
