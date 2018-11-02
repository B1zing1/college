package com.util;

import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class DoPageUtil extends SimpleTagSupport {
    //标签属性：请求地址
    private String url;
    //标签属性：分页所需参数
    private PageInfo pageInfo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
    //自定义标签要做的事
    @Override
    public void doTag() throws IOException {


        if(url.contains("?")) {
            url = url + "&";
        } else {
            url = url + "?";
        }

        JspWriter jspWriter = this.getJspContext().getOut();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<a href='"+url+"pageindex=1&size="+pageInfo.getPageSize()+"'>首页</a>&nbsp;");
        stringBuffer.append("<a href='"+url+"pageindex="+(pageInfo.getPrePage()==0?1:pageInfo.getPrePage())+"&size="+pageInfo.getPageSize()+"'>上一页</a>&nbsp;");
        for(int page=1; page<=pageInfo.getPages(); page++) {
            stringBuffer.append("<a href='"+url+"pageindex="+page+"&size="+pageInfo.getPageSize()+"'>"+page+"&nbsp;</a>");
        }
        stringBuffer.append("<a href='"+url+"pageindex="+(pageInfo.getNextPage()==0?pageInfo.getPages():pageInfo.getNextPage())+"&size="+pageInfo.getPageSize()+"'>下一页</a>&nbsp;")
        .append("<a href='"+url+"pageindex="+pageInfo.getPages()+"&size="+pageInfo.getPageSize()+"'>尾页</a>&nbsp;")
        .append("(共"+pageInfo.getTotal()+"条)")
        .append("每页显示<select name='num'>" +
                "<option value='5'"+(pageInfo.getPageSize()==5?"selected":"")+">5</option>" +
                "<option value='10'"+(pageInfo.getPageSize()==10?"selected":"")+">10</option>" +
                "<option value='15'"+(pageInfo.getPageSize()==15?"selected":"")+">15</option>" +
                "</select> "+pageInfo.getPageNum()+"/"+pageInfo.getPages()+"");

        jspWriter.print(stringBuffer.toString());
    }
}














