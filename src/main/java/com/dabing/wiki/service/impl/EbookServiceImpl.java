package com.dabing.wiki.service.impl;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.entities.EbookExample;
import com.dabing.wiki.mapper.EbookMapper;
import com.dabing.wiki.req.EbookReq;
import com.dabing.wiki.resp.EbookResp;
import com.dabing.wiki.service.EbookService;
import com.dabing.wiki.util.CopyUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
        if(!ObjectUtils.isEmpty(req.getName())){  //如果传入的参数name不为空才进行name模糊查询
            criteria.andNameLike("%"+req.getName()+"%");
        }
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        /*List<EbookResp> ebookRespList = new ArrayList<>();
        //将Ebook转成EbookResp
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook,ebookResp);  //用工具类，实现从ebook的值复制到ebookResp
            ebookRespList.add(ebookResp);
        }*/
        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
        return respList;
    }
}
