package com.neuedu.service.impl;

import com.google.common.collect.Sets;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import com.sun.tools.classfile.Opcode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse get_category(Integer categoryId) {

        //step1:参数的非空校验
        if(categoryId==null){
            return ServerResponse.createServerResponseByError("参数不能为空");
        }

        //step2:根据categoryId查询类别
        Category category =  categoryMapper.selectByPrimaryKey(categoryId);
        if (category==null){
            return ServerResponse.createServerResponseByError("查询类别不存在");
        }

        //step3:查询子类别
        List<Category> categoryList = categoryMapper.findChlidCategory(categoryId);

        //step4：返回结果
        return ServerResponse.createServerResponseBySuccess(null,categoryList);
    }

    @Override
    public ServerResponse add_category(Integer parentId, String categoryName) {

        //step1:参数校验
        if (StringUtils.isBlank(categoryName)){
            return ServerResponse.createServerResponseByError("类别名称不能为空");
        }

        //step2:添加节点
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(1);
        int result =  categoryMapper.insert(category);
        //step3:返回结果
        if(result>0){
            return ServerResponse.createServerResponseBySuccess("添加成功");
        }
        return ServerResponse.createServerResponseByError("添加失败");
    }

    @Override
    public ServerResponse set_category_name(Integer categoryId, String categoryName) {


        //step1:参数的非空校验
        if (categoryId==null||categoryId.equals("")){
            return ServerResponse.createServerResponseByError("类别id不能为空");
        }
        if(categoryName==null||categoryName.equals("")){
            return ServerResponse.createServerResponseByError("类别名称不能为空");
        }
        //step2:根据categoryId查询
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category==null){
            return ServerResponse.createServerResponseByError("要修改的类别不存在");
        }
        //step3:修改
        category.setName(categoryName);
        int result = categoryMapper.updateByPrimaryKey(category);
        if (result>0){
            return ServerResponse.createServerResponseBySuccess("修改成功");
        }
        //step4:返回结果
        return ServerResponse.createServerResponseByError("修改失败");
    }

    @Override
    public ServerResponse get_deep_category(Integer categoryId) {

        //step1:参数的非空检验
        if (categoryId==null){
            return ServerResponse.createServerResponseByError("类别id不能为空");
        }
        //step2:查询
        Set<Category> categorySet = Sets.newHashSet();
        categorySet = findAllChildCategory(categorySet,categoryId);

        Set<Integer> integerSet = Sets.newHashSet();
        Iterator<Category> categoryIterator =  categorySet.iterator();
        while (categoryIterator.hasNext()){
            Category category = categoryIterator.next();
            integerSet.add(category.getId());
        }

        return ServerResponse.createServerResponseBySuccess(null,integerSet);
    }
    private Set<Category> findAllChildCategory(Set<Category> categorySet ,Integer categoryId){
        //查找本节点
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category!=null){
            categorySet.add(category);//id
        }

        //查找categoryId下的子节点(平级)
        List<Category> categoryList = categoryMapper.findChlidCategory(categoryId);
        if (categoryList!=null&&categoryList.size()>0){
            for (Category category1:categoryList){
                findAllChildCategory(categorySet,category1.getId());
            }
        }
        return categorySet;
    }
}
