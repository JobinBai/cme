package com.baiyanbing.cme.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.word.WordUtil;
import com.baiyanbing.cme.domain.Category;
import com.baiyanbing.cme.domain.FileRow;
import com.baiyanbing.cme.domain.FileSource;
import com.baiyanbing.cme.service.CategoryService;
import com.baiyanbing.cme.service.FileRowService;
import com.baiyanbing.cme.service.FileSourceService;
import com.baiyanbing.cme.utils.FileSourceParser;
import com.baiyanbing.cme.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author baiyanbing
 * @createAt 2019-11-18 11:04
 */
@RestController
@RequestMapping("cme")
public class CmeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileSourceService filesSourceService;

    @Autowired
    private FileRowService fileRowService;

    @PostMapping("addCategory")
    public Object addCategory(String name){

        if(StrUtil.isEmpty(name)){
            return Result.fail("分类名称不能为空");
        }

        int count = categoryService.lambdaQuery().eq(Category::getName, name).count();
        if(count > 0){
            return Result.fail("分类已存在");
        }

        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
        return  Result.ok(category);
    }

    @GetMapping("search")
    public Object search(String content,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "20") int size){

        if(StrUtil.isEmpty(content)){
            return Result.fail("搜索内容不能为空");
        }

        LambdaQueryWrapper<FileRow> queryWrapper = new LambdaQueryWrapper<FileRow>()
                .like(FileRow::getContent, content)
                .orderByDesc(FileRow::getId);

        IPage<FileRow> fileRowPage = fileRowService.page(new Page<FileRow>(page, size), queryWrapper);
        return  Result.ok(fileRowPage);
    }


    @PostMapping("addFile")
    public Object addFile(MultipartFile file, @RequestParam(defaultValue = "0") int categoryId){

        if(ObjectUtil.isEmpty(file)){
            return Result.fail("上传文件不能为空");
        }

        String fileName = file.getOriginalFilename();
        int count = filesSourceService.lambdaQuery().eq(FileSource::getName, fileName).count();
        if(count > 0){
            return Result.fail("文件已存在");
        }

        FileSource fileSource = new FileSource();
        fileSource.setName(fileName);
        fileSource.setCategoryId(categoryId);
        fileSource.setType(FileUtil.extName(fileName));
        filesSourceService.save(fileSource);


        try{
            InputStream in = file.getInputStream();
            List<String> lines = FileSourceParser.parse(in, fileSource.getType());

            if(CollectionUtil.isNotEmpty(lines)){

                List<FileRow> fileRows = new ArrayList<>();

                for(int i =0; i < lines.size(); i++){
                    if(StrUtil.isBlank(lines.get(i))){
                        continue;
                    }

                    FileRow fileRow = new FileRow();
                    fileRow.setFileSourceId(fileSource.getId());
                    fileRow.setCategoryId(categoryId);
                    fileRow.setRowNo(i + 1);
                    fileRow.setContent(StrUtil.trimToEmpty(lines.get(i)));
                    fileRow.setCreatedAt(new Date());

                    fileRows.add(fileRow);
                    if(fileRows.size() == 10000){
                        fileRowService.saveBatch(fileRows);
                        fileRows = new ArrayList<>();
                        System.out.println("---" + i);
                        continue;
                    }

                    if(i + 1 == lines.size()){
                        fileRowService.saveBatch(fileRows);
                        fileRows = new ArrayList<>();
                        System.out.println("compeleted !!!");
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return  Result.ok(fileSource);
    }

}
