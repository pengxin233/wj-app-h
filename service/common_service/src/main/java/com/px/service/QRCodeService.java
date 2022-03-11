package com.px.service;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

public interface QRCodeService {
    BufferedImage GetQRCode(String code);

    void getShearCaptcha(OutputStream stream);
}
