package com.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {
    //1.定义变量保存生成后的验证码
    static String code = "";
    //2.生成验证码
    public static String createCode() {
        code = "";
        String choiceCode = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 4; i++) {
            int index = (int) (Math.random()*62);
            char b = choiceCode.charAt(index);
            code = code + b;
        }
        return code;
    }
    //得到已生成的验证码
    public static String getCode() {
        return code;
    }
    //3.生成图片
    public static BufferedImage createImage() {
        BufferedImage bufferedImage = new BufferedImage(70, 28, BufferedImage.TYPE_INT_RGB);
        //得到画布
        Graphics graphics = bufferedImage.getGraphics();
        //添加背景颜色
        graphics.setColor(Color.magenta);
        graphics.fillRect(0, 0, 70, 28);
        //添加干扰线
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color color = new Color(red, green, blue);
            graphics.setColor(color);
            int x1 = random.nextInt(101);
            int y1=random.nextInt(31);
            int x2=random.nextInt(101);
            int y2=random.nextInt(31);
            graphics.drawOval(x1,y1,x2,y2);
        }
        //添加文字
        graphics.setColor(Color.white);
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        String string = getCode();
        //将文字填充到画板
        graphics.drawString(string, 10, 23);
        //关闭画布
        graphics.dispose();
        return bufferedImage;
    }
}
