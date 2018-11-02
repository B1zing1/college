package com.web;

import com.bean.Information;
import com.github.pagehelper.PageInfo;
import com.service.InfoService;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class InfoController {

    @Resource
    private InfoService infoService;
    //查询所有资料
    @RequestMapping("/book/findAllInfo")
    public String findAllInfo(String informationname, String infotype, @RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                              @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        PageInfo pageInfo = infoService.findAllInfo(informationname, infotype, pageindex, size);
        pageInfo.setPageSize(size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("informationname", informationname);
        modelMap.put("infotype", infotype);
        return "book/list-ziliao";
    }
    //主键查询资料信息
    @RequestMapping("/book/findInfoById")
    public String findInfoById(Information information, ModelMap modelMap) {

        Information information1 = infoService.findInfoById(information);
        modelMap.put("information", information1);
        return "book/info-ziliao";
    }
    //修改资料信息
    @RequestMapping("/book/toupload")
    public void upload(MultipartFile myfile, Information information, HttpServletRequest request,
                       HttpServletResponse response){

        try {
            PrintWriter printWriter = response.getWriter();
            if(myfile.getOriginalFilename().length() > 0) {
                String path = request.getRealPath("\\upload");
                String fileName = myfile.getOriginalFilename();
                //将上传文件写入服务器上指定文件，MultipartFile转存后，无法再操作
                myfile.transferTo(new File(path + "\\" + fileName));
                information.setFilelocation("\\upload\\" + fileName);
                //截取文件格式
                String[] strings = fileName.split("\\.");
                String filetype = strings[strings.length - 1];
                information.setFiletype(filetype);
            }
            int count = infoService.updateByPrimaryKeySelective(information);
            if(count > 0) {
                printWriter.write("<script>alert('更新成功');location.href='/book/findAllInfo';</script>");
            } else {
                printWriter.write("<script>alert('更新失败');location.href='/book/findAInfoById';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //主键查询用于修改
    @RequestMapping("/book/findInfoToUpdate")
    public String findInfoToUpdate(Information information, ModelMap modelMap) {

        Information information1 = infoService.findInfoById(information);
        modelMap.put("information", information1);
        return "book/edit-ziliao";
    }
    //上传资料
    @RequestMapping("/book/addInfo")
    public void addInfo(MultipartFile myfile, Information information, HttpServletRequest request,
                       HttpServletResponse response){

        try {
            PrintWriter printWriter = response.getWriter();
            if(myfile.getOriginalFilename().length() > 0) {
                String path = request.getRealPath("\\upload");
                String fileName = myfile.getOriginalFilename();
                //将上传文件写入服务器上指定文件，MultipartFile转存后，无法再操作
                myfile.transferTo(new File(path + "\\" + fileName));
                information.setFilelocation("\\upload\\" + fileName);
                //截取文件格式
                String[] strings = fileName.split("\\.");
                String filetype = strings[strings.length - 1];
                information.setFiletype(filetype);
            }
            int count = infoService.insertSelective(information);
            if(count > 0) {
                printWriter.write("<script>alert('资料上传成功');location.href='/book/findAllInfo';</script>");
            } else {
                printWriter.write("<script>alert('资料上传失败');location.href=/book/add.jsp;</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //资料id查重
    @RequestMapping("/book/checkInfoId")
    public void checkInfoId(int informationid, HttpServletResponse response) {

        PageInfo pageInfo = infoService.findAllInfo(null, null, 0, 0);
        List<Information> infoList = pageInfo.getList();
        try {
            boolean b = false;
            PrintWriter printWriter = response.getWriter();
            for(Information information : infoList) {
                if(information.getInformationid() != informationid) {
                    b = true;
                } else {
                    b = false;
                    break;
                }
            }
            printWriter.print(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询所有资料用于下载
    @RequestMapping("/book/findAllInfoToDownLoad")
    public String findAllInfoToDownLoad(String informationname, String infotype, @RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                                        @RequestParam(value = "size", defaultValue = "5") int size, ModelMap modelMap) {

        PageInfo pageInfo = infoService.findAllInfo(informationname, infotype, pageindex, size);
        List<Information> list = pageInfo.getList();
        System.out.println(list);
        pageInfo.setPageSize(size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("informationname", informationname);
        modelMap.put("infotype", infotype);
        return "study/StudentMaterial";
    }
    //资料下载
    @RequestMapping("/book/downLoad")
    public ResponseEntity<byte[]> download(String fileName, HttpServletRequest request) throws IOException {

        String realPath = request.getRealPath("\\" + fileName);
        //解决中文乱码名称问题
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        int firstIndex = fileName.indexOf("\\");
        int lastIndex = fileName.indexOf(".");
        String fn = fileName.substring(firstIndex+1, lastIndex);
        //创建文件
        File file = new File(realPath + "\\" + fn);
        //设置头信息
        HttpHeaders httpHeaders = new HttpHeaders();
        //指定作为附件下载的文件名
        httpHeaders.setContentDispositionFormData("attachment", fn);
        //响应信息为流信息
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
        return responseEntity;
    }
}
