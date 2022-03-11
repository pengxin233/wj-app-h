package com.px.controller;

import com.px.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/commonService/QRCode")
public class QRCodeController{
    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("getQRCode")
    public void getQRCode(String code , HttpServletResponse response , Map<String,String> map){
        try {
            // 设置响应流信息
            response.setContentType("image/jpg");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            OutputStream stream = response.getOutputStream();

            //获取一个二维码图片
            BufferedImage bi = qrCodeService.GetQRCode(code);

            //以流的形式输出到前端
            ImageIO.write(bi, "jpg", stream);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("getShearCaptcha")
    public void getLineCaptcha(HttpServletResponse response , Map<String,String> map){
        try {
            // 设置响应流信息
            response.setContentType("image/jpg");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            OutputStream stream = response.getOutputStream();

            qrCodeService.getShearCaptcha(stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
