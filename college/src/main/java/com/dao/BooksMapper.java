package com.dao;

import com.bean.Books;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface BooksMapper {
    int deleteByPrimaryKey(Integer bookid);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);
    //查询所有书籍
    List<Books> findAllBooks(Map map);
    //id查数量
    int findCountById(Map map);
    //批量删除
    int deleteBooks(Map map);
}