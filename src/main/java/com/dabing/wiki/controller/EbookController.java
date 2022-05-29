package com.dabing.wiki.controller;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.entities.EbookExample;
import com.dabing.wiki.resp.CommonResp;
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
    public CommonResp<List<Ebook> > list(){
        List<Ebook> list = ebookService.list();
        return new CommonResp<List<Ebook> >(true,"",list);
    }
}
