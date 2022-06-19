package com.dabing.wiki.service.impl;

import com.dabing.wiki.entities.Ebook;
import com.dabing.wiki.entities.EbookExample;
import com.dabing.wiki.mapper.EbookMapper;
import com.dabing.wiki.req.EbookQueryReq;
import com.dabing.wiki.req.EbookSaveReq;
import com.dabing.wiki.resp.EbookQueryResp;
import com.dabing.wiki.resp.PageResp;
import com.dabing.wiki.service.EbookService;
import com.dabing.wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookServiceImpl implements EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookServiceImpl.class);

    @Resource
    private EbookMapper ebookMapper;

    @Override
    public PageResp<EbookQueryResp> list(EbookQueryReq req) {
        EbookExample ebookExample=new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){  //如果传入的参数name不为空才进行name模糊查询
            criteria.andNameLike("%"+req.getName()+"%");
        }
        PageHelper.startPage(req.getPage(),req.getSize()); //光这一句就能实现分页的效果了
        //将页面和行数改成了动态的了
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        //一些分页的信息
        PageInfo<Ebook> pageInfo=new PageInfo<>(ebookList);
        LOG.info("总记录数:{}",pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());

        /*List<EbookResp> ebookRespList = new ArrayList<>();
        //将Ebook转成EbookResp
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook,ebookResp);  //用工具类，实现从ebook的值复制到ebookResp
            ebookRespList.add(ebookResp);
        }*/
        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        PageResp<EbookQueryResp> pageResp=new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);
        return pageResp;
    }

    /**
     * 如果传入id有值为更新操作，如果无值为新增操作
     * @param ebookSaveReq
     * @return
     */
    @Override
    public int save(EbookSaveReq ebookSaveReq) {
        int res=0;
        Ebook ebook=CopyUtil.copy(ebookSaveReq,Ebook.class);
        if(ObjectUtils.isEmpty(ebookSaveReq.getId())){
            //新增
            res=ebookMapper.insert(ebook);
        }else{
            //更新
            res=ebookMapper.updateByPrimaryKey(ebook);
        }
        return res;
    }
}
