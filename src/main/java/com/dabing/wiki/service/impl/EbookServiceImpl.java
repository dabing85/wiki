package com.dabing.wiki.service.impl;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.mapper.EbookMapper;
import com.dabing.wiki.service.EbookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
