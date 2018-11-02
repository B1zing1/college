package com.service.impl;

import com.bean.Books;
import com.dao.BooksMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BooksService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BooksServiceImpl implements BooksService {

    @Resource
    private BooksMapper booksMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer bookid) {
        return booksMapper.deleteByPrimaryKey(bookid);
    }

    @Override
    public int insert(Books record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Books record) {
        return booksMapper.insertSelective(record);
    }

    @Override
    public Books selectByPrimaryKey(Integer bookid) {
        return booksMapper.selectByPrimaryKey(bookid);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Books record) {
        return booksMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Books record) {
        return 0;
    }

    @Override
    public PageInfo findAllBook(int pageindex, int size, int[] single) {

        PageHelper.startPage(pageindex, size);
        Map map = new HashMap();
        map.put("bookids", single);
        List<Books> booksList = booksMapper.findAllBooks(map);
        PageInfo pageInfo = new PageInfo(booksList);
        return pageInfo;
    }

    @Override
    public int findCountById(int bookid) {
        Map map = new HashMap();
        map.put("bookid", bookid);
        return booksMapper.findCountById(map);
    }

    @Override
    public int deleteBooks(int[] single) {
        Map map = new HashMap();
        map.put("bookids", single);
        return booksMapper.deleteBooks(map);
    }
}
