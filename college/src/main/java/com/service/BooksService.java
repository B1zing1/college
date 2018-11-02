package com.service;

import com.bean.Books;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface BooksService {
    int deleteByPrimaryKey(Integer bookid);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);
    //查询所有书籍
    PageInfo findAllBook(int pageindex, int size, int[] single);
    //id查数量
    int findCountById(int bookid);
    //批量删除
    int deleteBooks(int[] single);
}