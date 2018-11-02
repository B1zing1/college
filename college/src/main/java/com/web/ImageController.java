package com.web;

import com.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class ImageController {

    @RequestMapping("/createImage")
    public void createImage(HttpServletResponse response) {

        try {
            ImageUtil.createCode();
            BufferedImage image = ImageUtil.createImage();
            //以流的形式返回给客户端
            response.setContentType("image/jpeg");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //将图片转换成字节流
            ImageIO.write(image, "jpeg", byteArrayOutputStream);
            //得到输出流，返回给客户端
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
