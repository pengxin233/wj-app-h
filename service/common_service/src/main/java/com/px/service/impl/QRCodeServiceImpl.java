package com.px.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.px.service.QRCodeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @author pengxin01
 */
@Service
@Log4j2
public class QRCodeServiceImpl implements QRCodeService {
    @Override
    public BufferedImage GetQRCode(String code) {
        BufferedImage bi = null;
        try{
            //调用接口生成二维码
            //获取一个二维码图片
            //code只是我前端传递的一个数据，这块可以根据项目具体情况设计，此处可以是url也可以是别的一些需要设置的内容
            bi = QrCodeUtil.generate(code,200,200);

        } catch (Exception e) {
            log.error("获取二维码异常", e);
        }
        return bi;
    }

    @Override
    public void getShearCaptcha(OutputStream stream) {
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);

        String code = shearCaptcha.getCode();
        System.out.println(code);
        shearCaptcha.write(stream);
    }
}
