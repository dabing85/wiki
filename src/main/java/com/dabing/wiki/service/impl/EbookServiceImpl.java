package com.dabing.wiki.service.impl;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.entities.EbookExample;
import com.dabing.wiki.mapper.EbookMapper;
import com.dabing.wiki.req.EbookReq;
import com.dabing.wiki.resp.EbookResp;
import com.dabing.wiki.service.EbookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample=new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> ebookRespList = new ArrayList<>();
        //将Ebook转成EbookResp
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook,ebookResp);  //用工具类，实现从ebook的值复制到ebookResp
            ebookRespList.add(ebookResp);
        }
        return ebookRespList;
    }
}
