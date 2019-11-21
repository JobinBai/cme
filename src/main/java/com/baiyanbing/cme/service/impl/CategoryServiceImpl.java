package com.baiyanbing.cme.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baiyanbing.cme.mapper.CategoryMapper;
import com.baiyanbing.cme.domain.Category;
import com.baiyanbing.cme.service.CategoryService;
/**
* 
* @author baiyanbing
* @createAt 2019-11-18 13:59
*/

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

}
