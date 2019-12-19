package com.baiyanbing.cme.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baiyanbing.cme.domain.Category;
import com.baiyanbing.cme.domain.FileRow;
import com.baiyanbing.cme.domain.FileSource;
import com.baiyanbing.cme.domain.Subtitle;
import com.baiyanbing.cme.service.CategoryService;
import com.baiyanbing.cme.service.FileRowService;
import com.baiyanbing.cme.service.FileSourceService;
import com.baiyanbing.cme.service.SubtitleService;
import com.baiyanbing.cme.utils.FileSourceParser;
import com.baiyanbing.cme.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 11:04
 */
@RestController
@RequestMapping("subtitle")
public class SubtitleController {

    @Autowired
    private SubtitleService subtitleService;


    @GetMapping("")
    public ModelAndView index(){
        return new ModelAndView("subtitle");
    }


    @GetMapping("search")
    public Object search(String content, Integer sourceId,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "30") int size){

        if(StrUtil.isEmpty(content)){
            return Result.fail("搜索内容不能为空");
        }

        LambdaQueryWrapper<Subtitle> queryWrapper = new LambdaQueryWrapper<Subtitle>();
        if(sourceId != null){
        }
        queryWrapper.like(Subtitle::getContent, content)
                .orderByAsc(Subtitle::getSeason, Subtitle::getEpisode, Subtitle::getRow);

        IPage<Subtitle> pageInfo = subtitleService.page(new Page<Subtitle>(page, size), queryWrapper);
        return  Result.ok(pageInfo);
    }

    @GetMapping("list/source")
    public Object listSource(){
        return Result.ok(subtitleService.list());
    }

}
