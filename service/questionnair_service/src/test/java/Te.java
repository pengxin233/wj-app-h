import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Te {
    public static void main(String[] args) {
        System.out.println(change("MyFirstName"));

    }


    public static String change(String str){
        boolean isBig = false; //判断前一个是否为大写

        StringBuilder sb = new StringBuilder();

        for (int i=0;i<str.length();i++){
            char c = str.charAt(i);
            //判断当前字符是否大写
            boolean upperCase = Character.isUpperCase(c);

            if (upperCase){
                //当前是大写，上一个小写
                if (i>0 && Character.isLowerCase(str.charAt(i-1))){
                    sb.append('_');
                }else if (i!=0 && i<str.length()-1 && Character.isLowerCase(str.charAt(i+1))){
                    //当前是大写，下一个小写
                    sb.append('_');
                }

                sb.append(Character.toLowerCase(c));
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
