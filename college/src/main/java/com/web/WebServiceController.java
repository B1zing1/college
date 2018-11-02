package com.web;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;
import cn.com.webxml.WeatherWSSoap;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class WebServiceController {

    @Resource
    private MobileCodeWSSoap mobileCodeWSSoap;

    //webservice查询手机号
    @RequestMapping("/checkPhone")
    public void checkPhone(String phoneNum, HttpServletResponse response) {

        /*System.out.println("===============" + phoneNum);*/
        /*MobileCodeWS mobileCodeWS = new MobileCodeWS();
        MobileCodeWSSoap port = mobileCodeWS.getPort(MobileCodeWSSoap.class);
        String mobileCodeInfo = port.getMobileCodeInfo(phoneNum, null);
        System.out.println(mobileCodeInfo);*/
        String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo(phoneNum, null);
        System.out.println(mobileCodeInfo);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(mobileCodeInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Resource
    private cn.com.webxml.WeatherWSSoap WeatherWSSoap;
    //webservice查询天气
    @RequestMapping("/checkWeather")
    public void checkWeather(String cityName, HttpServletResponse response) {

        System.out.println("============" + cityName);
        ArrayOfString weather = WeatherWSSoap.getWeather(cityName, null);
        List<String> string = weather.getString();
        String weath = string.get(7);
        String s = cityName + weath;
        System.out.println(s);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.print(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
