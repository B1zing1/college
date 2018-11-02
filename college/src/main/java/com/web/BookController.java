package com.web;

import com.bean.Books;
import com.bean.Class;
import com.github.pagehelper.PageInfo;
import com.service.BooksService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.util.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Resource
    private BooksService booksService;
    //查询所有书籍
    @RequestMapping("/book/findAllBooks")
    public String findAllBook(@RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              ModelMap modelMap) {

        PageInfo pageInfo = booksService.findAllBook(pageindex, size, null);
        pageInfo.setPageSize(size);
        modelMap.put("pageInfo", pageInfo);
        return "book/list";
    }
    //主键查询书籍信息
    @RequestMapping("/book/findBookById")
    public String findBookById(int bookid, ModelMap modelMap) {

        Books books = booksService.selectByPrimaryKey(bookid);
        modelMap.put("books", books);
        return "/book/info";
    }
    //主键查询用于修改
    @RequestMapping("/book/findBookToUpdate")
    public String findBookToUpdate(int bookid, ModelMap modelMap) {

        Books books = booksService.selectByPrimaryKey(bookid);
        modelMap.put("books", books);
        return "/book/edit";
    }
    //修改
    @RequestMapping("/book/updateBook")
    public void updateBook(Books books, HttpServletResponse response) {

        int count = booksService.updateByPrimaryKeySelective(books);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('修改成功');location.href='/book/findAllBooks'</script>");
            } else {
                printWriter.write("<script>alert('修改失败');location.href='/book/findBookToUpdate?bookid='"+books.getBookid()+"</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //修改状态
    @RequestMapping("/book/changeState")
    public void changeState(Books books, HttpServletResponse response) {

        if(books.getBookstate().equals("禁用")) {
            books.setBookstate("启用");
        } else if(books.getBookstate().equals("启用")) {
            books.setBookstate("禁用");
        }
        int count = booksService.updateByPrimaryKeySelective(books);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('"+books.getBookstate()+"成功');location.href='/book/findAllBooks'</script>");
            } else {
                printWriter.write("<script>alert('"+books.getBookstate()+"失败');location.href='/book/findAllBooks'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //删除书籍
    @RequestMapping("/book/deleteBook")
    public void deleteBook(int bookid, HttpServletResponse response) {

        int count = booksService.deleteByPrimaryKey(bookid);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('删除成功');location.href='/book/findAllBooks'</script>");
            } else {
                printWriter.write("<script>alert('删除失败');location.href='/book/findAllBooks'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //新增书籍
    @RequestMapping("/book/addBook")
    public void addBook(Books books, HttpServletResponse response) {

        int count = booksService.insertSelective(books);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('新增成功');location.href='/book/findAllBooks'</script>");
            } else {
                printWriter.write("<script>alert('新增失败');location.href='/book/add.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //书籍id查重
    @RequestMapping("/book/checkBookId")
    public void checkBookId(int bookid, HttpServletResponse response) {

        int count = booksService.findCountById(bookid);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.print(false);
            } else {
                printWriter.print(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //批量删除
    @RequestMapping("/book/deleteBooks")
    public void deleteBooks(int[] single, HttpServletResponse response) {

        int count = booksService.deleteBooks(single);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('批量删除成功');location.href='/book/findAllBooks'</script>");
            } else {
                printWriter.write("<script>alert('批量删除失败');location.href='/book/findAllBooks'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导出Excel
    @RequestMapping("/book/toExcel")
    public void toExcel(int[] single, HttpServletResponse response) {

        PageInfo pageInfo = booksService.findAllBook(0, 0, single);
        List<Books> booksList = pageInfo.getList();
        String[] headers = {"书籍编号","书籍名称","状态"};
        ExcelUtil.createHead(headers);
        ExcelUtil.createOtherRow(booksList);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("f:\\books"+date+".xls");
            ExcelUtil.export(out);
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert('导出成功');location.href='/book/findAllBooks'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

















